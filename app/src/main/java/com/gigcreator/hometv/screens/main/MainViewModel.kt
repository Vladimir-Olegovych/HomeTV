package com.gigcreator.hometv.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigcreator.domain.usecase.TorrentRetrofitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val torrentUseCase: TorrentRetrofitUseCase
): ViewModel() {
    fun search(query: String, page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            torrentUseCase.search(query, page)
        }
    }
}