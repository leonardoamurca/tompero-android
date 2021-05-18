package com.leomurca.tompero.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.leomurca.tompero.R
import com.leomurca.tompero.models.Recipe

class RecipeAdapter(
    private val onClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private var recipes: List<Recipe> = emptyList<Recipe>()

    fun setRecipes(recipeList: List<Recipe>) {
        recipes = recipeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recipe,
            parent,
            false
        )

        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position], onClick)
    }

    override fun getItemCount() = recipes.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(recipe: Recipe, onClick: (Recipe) -> Unit) {
            itemView.findViewById<CardView>(R.id.recipeCard).setOnClickListener { onClick(recipe) }
            itemView.findViewById<TextView>(R.id.title).text = recipe.title
            itemView.findViewById<TextView>(R.id.description).text = recipe.description
        }
    }
}