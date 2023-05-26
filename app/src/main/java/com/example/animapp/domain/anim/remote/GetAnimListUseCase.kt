package com.example.animapp.domain.anim.remote

import com.example.animapp.domain.anim.AnimRepository
import com.example.animapp.domain.anim.model.AnimListInfo

class GetAnimListUseCase(
    private val animRepository: AnimRepository
) {
    suspend operator fun invoke(): AnimListInfo = animRepository.getAnimList()
}
