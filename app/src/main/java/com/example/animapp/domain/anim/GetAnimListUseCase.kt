package com.example.animapp.domain.anim

class GetAnimListUseCase(
    private val animRepository: AnimRepository
) {
    suspend operator fun invoke(): AnimListInfo = animRepository.getAnimList()
}
