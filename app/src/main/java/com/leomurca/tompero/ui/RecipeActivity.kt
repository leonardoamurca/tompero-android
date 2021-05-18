package com.leomurca.tompero.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leomurca.tompero.R
import com.leomurca.tompero.models.Recipe
import com.leomurca.tompero.ui.recipes.IngredientsAdapter

class RecipeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recipe)

    intent.extras?.let { bundle ->
      val recipe = bundle.getParcelable<Recipe>("recipe")
      findViewById<TextView>(R.id.recipeTitle).text = recipe?.title
      findViewById<TextView>(R.id.description).text = recipe?.description
      findViewById<RecyclerView>(R.id.recyclerViewIngredients).let {
        it.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        it.adapter = IngredientsAdapter(recipe!!.ingredients)
      }
      findViewById<TextView>(R.id.directions).text = recipe?.directions
    }
  }
}