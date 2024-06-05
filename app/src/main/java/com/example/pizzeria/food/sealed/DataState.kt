package com.example.pizzeria.food.sealed

import com.example.pizzeria.food.models.Categories

sealed class DataState{
    class Success(val data: MutableList<Categories>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}
