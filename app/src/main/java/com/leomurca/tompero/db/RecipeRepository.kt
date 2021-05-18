package com.leomurca.tompero.db

import com.leomurca.tompero.models.RecipeEntity

interface RecipeRepository {
    suspend fun getRecipes(): List<RecipeEntity>
    suspend fun insertRecipe(recipe: RecipeEntity)
}