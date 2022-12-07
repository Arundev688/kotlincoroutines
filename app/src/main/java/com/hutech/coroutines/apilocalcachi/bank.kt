package com.hutech.coroutines.apilocalcachi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banks")
data class bank(
    @PrimaryKey val bank_name: String,
    val account_number: String,
    val routing_number: String)