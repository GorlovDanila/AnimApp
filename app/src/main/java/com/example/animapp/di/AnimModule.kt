package com.example.animapp.di

import com.example.animapp.data.anim.datasource.remote.AnimApi
import com.example.animapp.data.anim.datasource.remote.AnimRepositoryImpl
import com.example.animapp.domain.anim.AnimRepository
import com.example.animapp.domain.anim.remote.GetAnimByIdUseCase
import com.example.animapp.domain.anim.remote.GetAnimListUseCase
import org.koin.dsl.module

val animModule = module {
    single {
        provideAnimRepository(
           animApi = get()
        )
    }
    single {
        provideGetAnimByIdUseCase(
            animRepository = get()
        )
    }
    single {
        provideGetAnimListUseCase(
            animRepository = get()
        )
    }
}

private fun provideAnimRepository(
    animApi: AnimApi
): AnimRepository = AnimRepositoryImpl(animApi)

private fun provideGetAnimByIdUseCase(
    animRepository: AnimRepository
): GetAnimByIdUseCase = GetAnimByIdUseCase(animRepository)

private fun provideGetAnimListUseCase(
    animRepository: AnimRepository
): GetAnimListUseCase = GetAnimListUseCase(animRepository)
