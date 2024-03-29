package com.fok.mob21firebase.core.di

import android.content.Context
import androidx.room.Room
import com.fok.mob21firebase.core.FIREBASE_REALTIME
import com.fok.mob21firebase.core.service.AuthService
import com.fok.mob21firebase.core.service.StorageService
import com.fok.mob21firebase.data.db.TodoDatabase
import com.fok.mob21firebase.data.repo.TodosRepo
import com.fok.mob21firebase.data.repo.TodosRepoFireStoreImpl
//import com.fok.mob21firebase.data.repo.TodosRepoRealtimeImpl
//import com.fok.mob21firebase.data.repo.TodosRepoRoomImpl
import com.fok.mob21firebase.data.repo.UserRepo
import com.fok.mob21firebase.data.repo.UserRepoImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAuth(@ApplicationContext context: Context): AuthService {
        return AuthService()
    }

    @Provides
    @Singleton
    fun provideStorageService(): StorageService {
        return StorageService()
    }

    @Provides
    @Singleton
    fun provideFirebaseRealtimeRef(): DatabaseReference {
        return FirebaseDatabase.getInstance().getReference("todos")
    }

    @Provides
    @Singleton
    fun provideFirebaseTodosCollection(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideTodosRepoFirestore(db: FirebaseFirestore): TodosRepo {
        return TodosRepoFireStoreImpl()
    }

    @Provides
    @Singleton
    fun provideUserRepoFirestore(db: FirebaseFirestore): UserRepo {
        return UserRepoImpl(db.collection("users"))
    }


//    @Provides
//    @Singleton
//    fun provideTodosRepoRealtime(db: DatabaseReference): TodosRepo {
//        return TodosRepoRealtimeImpl(db)
//    }

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            TodoDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
//    @Provides
//    @Singleton
//    fun provideTodosRepoRoom(db: TodoDatabase): TodosRepo {
//        return TodosRepoRoomImpl(db.todoDao())
//    }
}