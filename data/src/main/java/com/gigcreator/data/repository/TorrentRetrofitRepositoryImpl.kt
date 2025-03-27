package com.gigcreator.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gigcreator.data.datasource.PagingDataSource
import com.gigcreator.data.network.TorrentRetrofit
import com.gigcreator.domain.models.DataResult
import com.gigcreator.domain.models.SourceSearch
import com.gigcreator.domain.models.TorrentInstance
import com.gigcreator.domain.pagining.PageItem
import com.gigcreator.domain.repository.TorrentRetrofitRepository
import kotlinx.coroutines.flow.Flow

internal class TorrentRetrofitRepositoryImpl(private val torrentRetrofit: TorrentRetrofit): TorrentRetrofitRepository {
    override suspend fun torrent(magnetUri: String): TorrentInstance {
        return torrentRetrofit.torrent(magnetUri)
    }

    override suspend fun search(query: String, page: Int): Array<SourceSearch> {
        return torrentRetrofit.search(query, page)
    }

    override suspend fun info(sourceId: String, entryId: String): DataResult {
        return torrentRetrofit.info(sourceId, entryId)
    }

    override fun searchPaging(query: String): Flow<PagingData<PageItem>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { PagingDataSource(query, torrentRetrofit) }
        ).flow
    }
}