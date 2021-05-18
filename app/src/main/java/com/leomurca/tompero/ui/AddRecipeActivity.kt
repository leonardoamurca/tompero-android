package com.leomurca.tompero.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.leomurca.tompero.R
import com.leomurca.tompero.models.RecipeEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AddRecipeActivity : AppCompatActivity() {

    private val viewModel: AddRecipeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            viewModel.viewModelScope.launch {
                val builder = AlertDialog.Builder(this@AddRecipeActivity).apply {
                    setMessage("Loading...")
                    setCancelable(false)
                }
                val dialogCreated = builder.create()
                dialogCreated.show()

                val recipe = RecipeEntity(
                    title = findViewById<EditText>(R.id.textTitle).text.toString(),
                    author = "Aluízio",
                    description = findViewById<EditText>(R.id.description).text.toString(),
                    ingredients = findViewById<EditText>(R.id.ingredients).text.toString(),
                    directions = findViewById<EditText>(R.id.directions).text.toString(),
                )

                viewModel.save(recipe)
                delay(2000L)
                dialogCreated.dismiss()
                finish()
            }
        }
    }
}