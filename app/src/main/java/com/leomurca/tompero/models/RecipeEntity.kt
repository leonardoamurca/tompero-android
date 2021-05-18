package com.leomurca.tompero.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    val title: String,
    val description: String,
    val author: String,
    val ingredients: String,
    val directions: String,
    val imagePath: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}