package com.example.animapp.domain.anim.model

import com.google.gson.annotations.SerializedName

data class AnimListInfo(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    val listAnim: List<AnimInfo>?,
)
