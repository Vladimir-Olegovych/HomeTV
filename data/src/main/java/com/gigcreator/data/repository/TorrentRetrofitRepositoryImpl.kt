package com.gigcreator.data.repository

import com.gigcreator.domain.models.SourceSearch
import com.gigcreator.data.network.TorrentRetrofit
import com.gigcreator.domain.models.DataResult
import com.gigcreator.domain.models.TorrentInstance
import com.gigcreator.domain.repository.TorrentRetrofitRepository
import kotlinx.coroutines.delay

internal class TorrentRetrofitRepositoryImpl(private val torrentRetrofit: TorrentRetrofit): TorrentRetrofitRepository {
    override suspend fun torrent(magnetUri: String): TorrentInstance? {
        return requestTo(8) { torrentRetrofit.torrent(magnetUri) }
    }

    override suspend fun search(query: String, page: Int): Array<SourceSearch>? {
        return requestTo(8) { torrentRetrofit.search(query, page) }
    }

    override suspend fun info(sourceId: String, entryId: String): DataResult? {
        return requestTo(1) { torrentRetrofit.info(sourceId, entryId) }
    }

    private suspend fun <T> requestTo(
        maxAttempts: Int,
        request: suspend () -> T?,
    ): T? {
        for (i in 0 until maxAttempts){
            try {
                return request.invoke()
            } catch (e: Throwable){
                println(e.message)
                delay(DELAY)
            }
        }
        return null
    }

    companion object {
        const val DELAY = 100L
    }
}