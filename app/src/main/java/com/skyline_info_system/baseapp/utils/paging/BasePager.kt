package com.skyline_info_system.baseapp.utils.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig

private const val DEFAULT_PAGE_SIZE = 30
fun <V : Any> createPager(
    totalPages: Int? = null,
    pageSize: Int = DEFAULT_PAGE_SIZE,
    enablePlaceholders: Boolean = false,
    block: suspend (Int) -> ArrayList<V>,
): Pager<Int, V> = Pager(
    config = PagingConfig(enablePlaceholders = enablePlaceholders, pageSize = pageSize),
    pagingSourceFactory = { BasePagingSource(totalPages, block) }
)