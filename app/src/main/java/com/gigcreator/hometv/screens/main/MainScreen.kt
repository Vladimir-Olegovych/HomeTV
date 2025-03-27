package com.gigcreator.hometv.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.gigcreator.hometv.screens.main.cards.SearchCard
import com.gigcreator.hometv.screens.main.mvvm.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel){
    //Resources
    val keyboardController = LocalSoftwareKeyboardController.current
    //Search
    val textData = remember { mutableStateOf("") }
    val data = viewModel.searchPaging(textData.value).collectAsLazyPagingItems()

    Column(
        modifier = Modifier.background(Color.Gray).fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, start = 8.dp),
            value = textData.value,
            onValueChange = { textData.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            ),
            label = { Text("Search") }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(5.dp),
        ) {
            items(data.itemCount) { index ->
                val item = data[index] ?: return@items
                SearchCard(item) { }
            }
        }
    }
}