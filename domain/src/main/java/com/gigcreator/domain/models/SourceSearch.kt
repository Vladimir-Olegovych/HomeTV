package com.gigcreator.domain.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.gigcreator.domain.models.SearchResult
import java.io.Serializable


class SourceSearch: Serializable {
    @JsonProperty("results")
    val results: Array<SearchResult> = emptyArray()
    @JsonProperty("source_name")
    val sourceName: String = ""
    @JsonProperty("source_id")
    val sourceId: String = ""
}