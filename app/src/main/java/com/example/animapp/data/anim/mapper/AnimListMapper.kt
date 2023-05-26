package com.example.animapp.data.anim.mapper

import com.example.animapp.data.anim.datasource.remote.response.AnimListResponse
import com.example.animapp.domain.anim.model.AnimListInfo

fun AnimListResponse.toAnimListInfo() = AnimListInfo(
    currentPage = pagination?.currentPage ?: 0,
    hasNextPage = pagination?.hasNextPage ?: false,
    listAnim = data?.map { dataMapper(it) }
)
