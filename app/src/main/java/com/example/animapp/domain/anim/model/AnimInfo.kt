package com.example.animapp.domain.anim.model

import com.google.gson.annotations.SerializedName

data class AnimInfo(
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("genres")
    val genres: List<String?>?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("mal_id")
    val malId: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("title_english")
    val titleEnglish: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("large_image_url")
    val largeImageUrl: String?,
    @SerializedName("year")
    val year: Int?
)
