package com.example.teachmenotes.di

import com.example.teachmenotes.data.NotesRepositoryImpl
import com.example.teachmenotes.data.TasksRepositoryImpl
import com.example.teachmenotes.data.service.ApiService
import com.example.teachmenotes.domain.NotesRepository
import com.example.teachmenotes.domain.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindNotesRepository(
        notesRepositoryImpl: NotesRepositoryImpl
    ): NotesRepository

    @Binds
    abstract fun bindTasksRepository(
       taskRepositoryImpl: TasksRepositoryImpl
    ): TasksRepository

    companion object{
        private const val SP_KEY = "SP_KEY"
        private const val BASE_URL = "https://api.jsonserve.com"


//        @Provides
//        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferencesHelper{
//            return SharedPreferencesHelper(context.getSharedPreferences(SP_KEY,
//                Context.MODE_PRIVATE
//            ))
//        }

        @Provides
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}