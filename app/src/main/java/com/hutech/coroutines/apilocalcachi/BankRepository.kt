package com.hutech.coroutines.apilocalcachi

import androidx.room.withTransaction
import com.hutech.coroutines.apilocalcachi.local.BankDatabase
import com.hutech.coroutines.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class BankRepository @Inject constructor(
    private val api: BankApi,
    private val db: BankDatabase
) {
    private val bankDao = db.bankDao()

    fun getBanks() = networkBoundResource(
        query = {
            bankDao.getAllBanks()
        },
        fetch = {
            delay(3000)
            api.getBanks()
        },
        saveFetchResult = {banks->
            db.withTransaction {
                bankDao.deleteAllBanks()
                bankDao.insertBanks(banks)
            }
        }
    )
}