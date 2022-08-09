package com.abhi41.jetdictionary.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhi41.jetdictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = wordInfo.word ?: "null",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )

        Text(
            text = wordInfo.phonetic ?: "null",
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = modifier.height(16.dp))
        wordInfo.meanings.forEach { meaning ->
            Text(
                text = meaning.partOfSpeech ?: "null",
                fontWeight = FontWeight.Bold,
            )
            meaning.definition.forEachIndexed { i, defination ->
                Text(text = "${i + 1}. ${defination.definition}")
                Spacer(modifier = modifier.height(8.dp))
                Text(text = "Example is: ${defination.example}")
                Spacer(modifier = modifier.height(8.dp))
            }
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}