package com.example.animapp.data.anim.mapper

import com.example.animapp.data.anim.datasource.remote.response.AnimResponse
import com.example.animapp.data.anim.datasource.remote.response.Data
import com.example.animapp.domain.anim.model.AnimInfo

fun AnimResponse.toAnimInfo(): AnimInfo = dataMapper(data)

fun dataMapper(data: Data?): AnimInfo = AnimInfo(
    episodes = data?.episodes,
    genres = data?.genres?.let {it.map {genre ->  genre.name}} ?: listOf(""),
    imageUrl = data?.images?.jpg?.imageUrl,
    malId = data?.malId,
    status = data?.status,
    synopsis = data?.synopsis,
    title = data?.title,
    titleEnglish = data?.titleEnglish,
    type = data?.type,
    url = data?.url,
    largeImageUrl = data?.images?.jpg?.largeImageUrl,
    year = data?.year,
)
