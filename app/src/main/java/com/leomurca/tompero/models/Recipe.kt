package com.leomurca.tompero.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val title: String,
    val description: String,
    val author: String,
    val ingredients: List<String>,
    val directions: String,
    val imagePath: String? = null
) : Parcelable