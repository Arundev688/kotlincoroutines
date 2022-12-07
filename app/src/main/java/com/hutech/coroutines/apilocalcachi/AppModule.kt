package com.hutech.coroutines.apilocalcachi

import android.app.Application
import androidx.room.Room
import com.hutech.coroutines.apilocalcachi.local.BankDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(BankApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun proviceBankApi(retrofit: Retrofit):BankApi =
        retrofit.create(BankApi::class.java)


    @Provides
    @Singleton
    fun provideDatabase(app:Application) : BankDatabase =
        Room.databaseBuilder(app,BankDatabase::class.java,"bank_database")
            .build()


}