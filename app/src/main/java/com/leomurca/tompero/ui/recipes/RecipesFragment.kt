package com.leomurca.tompero.ui.recipes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leomurca.tompero.R
import com.leomurca.tompero.ui.RecipeActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RecipesFragment : Fragment() {

    private val recipesViewModel: RecipesViewModel by inject()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeAdapter = RecipeAdapter { recipe ->
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("recipe", recipe)
            startActivity(intent)
        }

        view.findViewById<RecyclerView>(R.id.recipesRecyclerView).let {
            it.layoutManager = LinearLayoutManager(
                this@RecipesFragment.activity,
                LinearLayoutManager.VERTICAL,
                false
            )
            it.adapter = recipeAdapter
        }

        recipesViewModel.recipes.observe(viewLifecycleOwner, {
            recipeAdapter.setRecipes(it)
        })
    }

    override fun onResume() {
        super.onResume()

        recipesViewModel.viewModelScope.launch {
            recipesViewModel.init()
        }
    }
}