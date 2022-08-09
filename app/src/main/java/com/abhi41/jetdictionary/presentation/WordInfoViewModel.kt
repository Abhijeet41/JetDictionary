package com.abhi41.jetdictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi41.jetdictionary.domain.use_case.GetWordInfoCase
import com.abhi41.jetdictionary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfoCase: GetWordInfoCase
) : ViewModel() {

    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery: State<String> = _searchQuery

    private val _wordInfoState = mutableStateOf(WordInfoState())
    val wordInfoState: State<WordInfoState> = _wordInfoState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfoCase(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _wordInfoState.value = _wordInfoState.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _wordInfoState.value = _wordInfoState.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    result.message ?: "Unknown Error"
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _wordInfoState.value = _wordInfoState.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }

                }.launchIn(this)
        }
    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
    }

}