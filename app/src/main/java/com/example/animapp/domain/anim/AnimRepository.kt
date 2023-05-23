package com.example.animapp.domain.anim

interface AnimRepository {

    suspend fun getAnimById(id: Int): AnimInfo

    suspend fun getAnimList(): AnimListInfo
}
