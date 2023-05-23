package com.example.animapp.domain.anim

class GetAnimByIdUseCase(
    private val animRepository: AnimRepository
) {
    suspend operator fun invoke(id: Int): AnimInfo = animRepository.getAnimById(id)
}
