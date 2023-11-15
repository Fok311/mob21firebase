package com.fok.mob21firebase.data.repo

import com.fok.mob21firebase.data.Model.Todo
import kotlinx.coroutines.flow.Flow

interface TodosRepo {
    fun getAllTodos(): Flow<List<Todo>>
    suspend fun insert(todo: Todo)
    suspend fun getTodo(id: String): Todo?
    suspend fun update(id: String, todo: Todo)
    suspend fun delete(id: String)
    suspend fun greet(): String
}