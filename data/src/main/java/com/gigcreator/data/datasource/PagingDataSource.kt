package com.gigcreator.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gigcreator.data.network.TorrentRetrofit
import com.gigcreator.domain.pagining.PageItem

internal class PagingDataSource(
    private val query: String,
    private val torrentRetrofit: TorrentRetrofit
): PagingSource<Int, PageItem>() {

    override fun getRefreshKey(state: PagingState<Int, PageItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PageItem> {
        return try {
            val page = params.key?: 0
            val sourceSearchArray = torrentRetrofit.search(query, page)
            val searchData = ArrayList<PageItem>()

            if (query.isNotEmpty())
            for (sourceSearch in sourceSearchArray) {
                for(result in sourceSearch.results) {
                    try {
                        val item = PageItem(
                            sourceId = sourceSearch.sourceId,
                            sourceName = sourceSearch.sourceName,
                            searchResult = result,
                            dataResult = torrentRetrofit.info(sourceSearch.sourceId, result.id)
                        )
                        searchData.add(item)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }

            val nextKey = if(searchData.isEmpty()) null else page.plus(1)
            val prevKey = if (page == 0) null else page.minus(1)

            LoadResult.Page(
                data = searchData.toList(),
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}