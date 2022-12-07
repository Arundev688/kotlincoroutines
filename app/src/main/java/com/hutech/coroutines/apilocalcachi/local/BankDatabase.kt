package com.hutech.coroutines.apilocalcachi.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hutech.coroutines.apilocalcachi.bank

@Database(entities = [bank::class], version = 1)
abstract class BankDatabase : RoomDatabase() {
      abstract fun bankDao(): BankDao
}