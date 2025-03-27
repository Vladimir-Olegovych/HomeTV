package com.gigcreator.domain.pagining

import com.gigcreator.domain.models.DataResult
import com.gigcreator.domain.models.SearchResult

class PageItem(
    val sourceId: String,
    val sourceName: String,
    val dataResult: DataResult,
    val searchResult: SearchResult
)