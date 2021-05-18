package com.leomurca.tompero.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.leomurca.tompero.db.RecipeRepository
import com.leomurca.tompero.models.RecipeEntity

class AddRecipeViewModel(app: Application, private val repository: RecipeRepository) :
    AndroidViewModel(app) {
    suspend fun save(recipe: RecipeEntity) {
        repository.insertRecipe(recipe)
    }
}