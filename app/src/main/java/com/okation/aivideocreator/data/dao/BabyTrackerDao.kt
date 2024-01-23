package com.okation.aivideocreator.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.okation.aivideocreator.data.entities.Feeding
import com.okation.aivideocreator.data.entities.Sleeping
import com.okation.aivideocreator.data.entities.Symptoms


@Dao
interface BabyTrackerDao {

    //Feeding Section
    @Query("Select * From feeding")
    fun getFeedings() : LiveData<List<Feeding>>

    @Query("Select * From feeding Where feedingDate = :selectedDate")
    fun getFeedingByDate(selectedDate : String) : LiveData<List<Feeding>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeeding(feeding: Feeding)



    //Sleeping Section
    @Query("Select * From sleeping")
    fun getSleeping() : LiveData<List<Sleeping>>

    @Query("Select * From sleeping Where sleepingDate = :selectedDate")
    fun getSleepingByDate(selectedDate: String) : LiveData<List<Sleeping>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSleeping(sleeping: Sleeping)




    //Symptoms Section
    @Query("Select * From symptoms")
    fun getSymptom() : LiveData<List<Symptoms>>

    @Query("Select * From symptoms Where symptomDate = :selectedDate")
    fun getSymptomByDate(selectedDate: String) : LiveData<List<Symptoms>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSymptoms(symptoms: Symptoms)



}