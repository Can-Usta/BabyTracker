package com.okation.aivideocreator.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "symptoms")
data class Symptoms(
    @PrimaryKey(autoGenerate = true)
    var symptomId : Int = 0,

    var symptomTime : String,

    var symptomName : String,

    var symptomsNote :String,

    var symptomDate : String
)
