package com.leomurca.tompero.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leomurca.tompero.db.RecipeRepository
import com.leomurca.tompero.models.Recipe

class RecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipes = MutableLiveData(emptyList<Recipe>())
    val recipes: LiveData<List<Recipe>> get() = _recipes

    suspend fun init() {
        _recipes.value = recipeRepository.getRecipes().map {
            Recipe(
                it.title,
                it.description,
                it.author,
                it.ingredients.split(",").map { ing -> ing.trim() },
                it.directions
            )
        }
    }
}