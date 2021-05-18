package com.leomurca.tompero.di

import com.leomurca.tompero.db.AppDatabase
import com.leomurca.tompero.db.RecipeRepository
import com.leomurca.tompero.db.RecipeRepositoryImpl
import com.leomurca.tompero.ui.AddRecipeViewModel
import com.leomurca.tompero.ui.recipes.RecipesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.buildDatabase(androidContext()) }
    single { get<AppDatabase>().recipeDao() }
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    viewModel { RecipesViewModel(get()) }
    viewModel { AddRecipeViewModel(get(), get()) }
}