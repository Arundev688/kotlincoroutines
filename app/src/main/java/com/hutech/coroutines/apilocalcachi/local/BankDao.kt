package com.hutech.coroutines.apilocalcachi.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hutech.coroutines.apilocalcachi.bank
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDao {

    @Query("SELECT * FROM banks")
    fun getAllBanks() : Flow<List<bank>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanks(banks: List<bank>)

    @Query("DELETE FROM banks")
    suspend fun deleteAllBanks()



}