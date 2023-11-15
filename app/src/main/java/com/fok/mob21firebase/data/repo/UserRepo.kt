package com.fok.mob21firebase.data.repo

import com.fok.mob21firebase.data.Model.User


interface UserRepo {
    suspend fun addUser(id: String, user: User)

    suspend fun getUser(id: String): User?

    suspend fun updateUser(id: String, user: User)
}