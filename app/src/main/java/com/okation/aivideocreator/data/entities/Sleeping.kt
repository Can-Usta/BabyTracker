package com.okation.aivideocreator.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleeping")
data class Sleeping(
    @PrimaryKey(autoGenerate = true)
    var sleepingId : Int = 0,

    var sleepingTime : String,

    var wokeUpTime : String,

    var sleepingNote : String,

    var sleepingDate : String
)
