package com.fok.mob21firebase.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fok.mob21firebase.data.Model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("select * from todo")
    fun getAllTodos(): Flow<List<Todo>>

    @Query("select * from todo where todoId = :id")
    fun getById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Query("delete from todo where todoId = :id")
    fun delete(id: Int)
}