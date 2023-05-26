package com.example.animapp.domain.anim.remote

import com.example.animapp.domain.anim.AnimRepository
import com.example.animapp.domain.anim.model.AnimInfo

class GetAnimByIdUseCase(
    private val animRepository: AnimRepository
) {
    suspend operator fun invoke(id: Int): AnimInfo = animRepository.getAnimById(id)
}
