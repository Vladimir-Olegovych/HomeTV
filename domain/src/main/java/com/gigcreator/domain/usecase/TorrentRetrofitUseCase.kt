package com.gigcreator.domain.usecase

import androidx.paging.PagingData
import com.gigcreator.domain.models.DataResult
import com.gigcreator.domain.models.SourceSearch
import com.gigcreator.domain.models.TorrentInstance
import com.gigcreator.domain.pagining.PageItem
import com.gigcreator.domain.repository.TorrentRetrofitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TorrentRetrofitUseCase @Inject constructor(
    private val torrentRetrofitRepository: TorrentRetrofitRepository
) {
    suspend fun torrent(magnetUri: String): TorrentInstance =
        torrentRetrofitRepository.torrent(magnetUri)

    suspend fun search(query: String, page: Int): Array<SourceSearch> =
        torrentRetrofitRepository.search(query, page)

    suspend fun info(sourceId: String, entryId: String): DataResult =
        torrentRetrofitRepository.info(sourceId, entryId)

    fun searchPaging(query: String): Flow<PagingData<PageItem>> =
        torrentRetrofitRepository.searchPaging(query)
}