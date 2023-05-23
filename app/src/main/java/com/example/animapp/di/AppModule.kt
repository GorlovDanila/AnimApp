package com.example.animapp.di

import com.example.animapp.presentation.presenters.DetailsViewModel
import com.example.animapp.presentation.presenters.MainViewModel
import com.example.animapp.utils.AndroidResourceProvider
import com.example.animapp.utils.ResourceProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<ResourceProvider> {
        AndroidResourceProvider(get())
    }
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}
