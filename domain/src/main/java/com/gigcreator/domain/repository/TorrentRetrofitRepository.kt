package com.gigcreator.domain.repository

import androidx.paging.PagingData
import com.gigcreator.domain.models.DataResult
import com.gigcreator.domain.models.SourceSearch
import com.gigcreator.domain.models.TorrentInstance
import com.gigcreator.domain.pagining.PageItem
import kotlinx.coroutines.flow.Flow

interface TorrentRetrofitRepository {
    suspend fun torrent(magnetUri: String): TorrentInstance

    suspend fun search(query: String, page: Int): Array<SourceSearch>

    suspend fun info(sourceId: String, entryId: String): DataResult

    fun searchPaging(query: String): Flow<PagingData<PageItem>>
}