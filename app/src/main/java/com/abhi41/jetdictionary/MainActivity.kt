package com.abhi41.jetdictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhi41.jetdictionary.presentation.WordInfoItem
import com.abhi41.jetdictionary.presentation.WordInfoViewModel
import com.abhi41.jetdictionary.ui.theme.JetDictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetDictionaryTheme {
                // A surface container using the 'background' color from the theme
                val viewModel: WordInfoViewModel = hiltViewModel()
                val state = viewModel.wordInfoState.value
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when (event) {
                            is WordInfoViewModel.UiEvent.ShowSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState
                ) {

                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {

                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = viewModel.searchQuery.value,
                                onValueChange = { updatedValue ->
                                    viewModel.onSearch(updatedValue)
                                },
                                //onValueChange = viewModel::onSearch,// we can also write like this
                                placeholder = {
                                    Text(text = "Search...")
                                }
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(state.wordInfoItems.size) { i ->
                                    val wordInfo = state.wordInfoItems[i]
                                    if (i > 0) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    WordInfoItem(wordInfo = wordInfo)
                                    if (i < state.wordInfoItems.size - 1) {
                                        Divider()
                                    }
                                }
                            }
                        }
                          if(state.isLoading){
                              CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                          }
                    }//box

                }

            }
        }
    }
}

