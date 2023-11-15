package com.fok.mob21firebase.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fok.mob21firebase.data.Model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        const val DB_NAME = "todo_database"
    }
}