package com.okation.aivideocreator.data.repository


import androidx.lifecycle.LiveData
import com.okation.aivideocreator.data.dao.BabyTrackerDao
import com.okation.aivideocreator.data.entities.Feeding
import com.okation.aivideocreator.data.entities.Sleeping
import com.okation.aivideocreator.data.entities.Symptoms
import javax.inject.Inject

class DaoRepository @Inject constructor(private val datasource : BabyTrackerDao) {
    fun insertFeeding(feeding: Feeding) {
        datasource.insertFeeding(feeding)
    }

    fun getFeedings(): LiveData<List<Feeding>> {
        return datasource.getFeedings()
    }
    fun getFeedingByDate(selectedDate : String) : LiveData<List<Feeding>>{
        return datasource.getFeedingByDate(selectedDate)
    }


    fun insertSleep(sleep: Sleeping) {
        datasource.insertSleeping(sleep)
    }

    fun getSleeps(): LiveData<List<Sleeping>> {
        return datasource.getSleeping()
    }

    fun getSleepingByDate(selectedDate: String): LiveData<List<Sleeping>> {
        return datasource.getSleepingByDate(selectedDate)
    }



    fun insertSymptoms(symptoms: Symptoms) {
        datasource.insertSymptoms(symptoms)
    }

    fun getSymptoms(): LiveData<List<Symptoms>> {
        return datasource.getSymptom()
    }

    fun getSymptomsByDate(selectedDate: String) : LiveData<List<Symptoms>>{
        return datasource.getSymptomByDate(selectedDate)
    }



}