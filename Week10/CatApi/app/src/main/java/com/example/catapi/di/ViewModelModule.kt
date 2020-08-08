package com.example.catapi.di

import com.example.catapi.viewmodel.AddCatViewModel
import com.example.catapi.viewmodel.FunnyCatsViewModel
import com.example.catapi.viewmodel.ShowCatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AddCatViewModel(get())
    }

    viewModel {
        FunnyCatsViewModel(get())
    }

    viewModel {
        ShowCatsViewModel(get())
    }
}