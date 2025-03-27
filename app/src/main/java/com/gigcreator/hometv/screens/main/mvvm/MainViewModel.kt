package com.gigcreator.hometv.screens.main.mvvm

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
    fun searchPaging(query: String) = torrentUseCase.searchPaging(query)
    fun search(query: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = torrentUseCase.search(query, page)
            println(data[0].results.size)
        }
    }
}