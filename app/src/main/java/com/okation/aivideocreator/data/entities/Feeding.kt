package com.okation.aivideocreator.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feeding")
data class Feeding (
    @PrimaryKey(autoGenerate = true)
    var feedingId : Int = 0,

    var feedingTime : String,

    var feedingAmount : String,

    var feedingNote : String,

    var feedingDate : String
)