package com.okation.aivideocreator.ui.sleeping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.entities.Sleeping
import com.okation.aivideocreator.data.repository.DaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SleepingViewModel @Inject constructor(private val repository: DaoRepository) : ViewModel(){

    private val _selectedDate = MutableLiveData<String?>(null)
    private val selectedDate: LiveData<String?> = _selectedDate

    fun setSelectedDate(date: String?) {
        _selectedDate.value = date
    }

    fun saveSleepingToDB(sleepingTime : String, wokeUpTime : String, sleepingNote : String, sleepingDate : String ){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val newSleeping = Sleeping(sleepingTime = sleepingTime, wokeUpTime = wokeUpTime, sleepingNote = sleepingNote, sleepingDate = sleepingDate)
                repository.insertSleep(newSleeping)
            }
        }
    }

    private val _sleeping = selectedDate.switchMap {switchedSleeping->
        if (switchedSleeping != null){
            repository.getSleepingByDate(switchedSleeping)
        }else{
            MutableLiveData()
        }

    }
    val sleeping: LiveData<List<Sleeping>> = _sleeping




}