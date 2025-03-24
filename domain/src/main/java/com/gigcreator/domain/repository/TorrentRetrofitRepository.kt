package com.gigcreator.domain.repository

import com.gigcreator.domain.models.SourceSearch
import com.gigcreator.domain.models.TorrentInstance
import com.gigcreator.domain.models.DataResult

interface TorrentRetrofitRepository {
    suspend fun torrent(magnetUri: String): TorrentInstance?

    suspend fun search(query: String, page: Int): Array<SourceSearch>?

    suspend fun info(sourceId: String, entryId: String): DataResult?
}