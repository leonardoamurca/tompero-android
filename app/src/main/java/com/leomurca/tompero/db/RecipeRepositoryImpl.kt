package com.leomurca.tompero.db

import com.leomurca.tompero.models.Recipe
import com.leomurca.tompero.models.RecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(private val dao: RecipeDao) : RecipeRepository {
    override suspend fun getRecipes(): List<RecipeEntity> {
        return withContext(Dispatchers.IO) {
            dao.getRecipes()
        }
    }

    override suspend fun insertRecipe(recipe: RecipeEntity) {
        withContext(Dispatchers.IO) {
            dao.insertRecipe(recipe)
        }
    }
}