package com.example.animapp.domain.anim

import com.example.animapp.domain.anim.model.AnimInfo
import com.example.animapp.domain.anim.model.AnimListInfo

interface AnimRepository {

    suspend fun getAnimById(id: Int): AnimInfo

    suspend fun getAnimList(): AnimListInfo
}
