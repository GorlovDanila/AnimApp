package com.example.animapp.data.anim.datasource.remote

import com.example.animapp.data.anim.mapper.toAnimInfo
import com.example.animapp.data.anim.mapper.toAnimListInfo
import com.example.animapp.domain.anim.model.AnimInfo
import com.example.animapp.domain.anim.model.AnimListInfo
import com.example.animapp.domain.anim.AnimRepository

class AnimRepositoryImpl(
    private val api: AnimApi
): AnimRepository {
    override suspend fun getAnimById(id: Int): AnimInfo = api.getAnimById(id).toAnimInfo()

    override suspend fun getAnimList(): AnimListInfo = api.getAnimList().toAnimListInfo()
}
