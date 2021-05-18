package com.leomurca.tompero.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.leomurca.tompero.models.Recipe
import com.leomurca.tompero.models.RecipeEntity
import java.util.concurrent.Executors

@Database(entities = [RecipeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "app_database.db")
                .addCallback(populateDatabase(context))
                .build()

        private fun populateDatabase(context: Context): Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Executors.newSingleThreadExecutor().execute {
                    val dao = getInstance(context).recipeDao()
                    dao.insertRecipe(
                        RecipeEntity(
                            title = "Miojo",
                            description = "Uma receita facil de se fazer, principalmente quem mora sozinho.",
                            directions = "Coloque a água no miojo e seja feliz.",
                            author = "Leonardo Murça",
                            ingredients = "1 Macarrao instantaneo, 2L Água",
                        )
                    )

                    dao.insertRecipe(
                        RecipeEntity(
                            title = "Estrogonofe",
                            description = "Uma receita facil de se fazer, principalmente quem mora sozinho.",
                            directions = "Prepare o estrogonofe como quiser",
                            author = "Palmirinha",
                            ingredients = "1 Macarrao instantaneo, 2L Água",
                        )
                    )
                }
            }
        }
    }
}