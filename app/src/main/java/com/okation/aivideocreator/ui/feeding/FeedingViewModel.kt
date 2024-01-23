package com.okation.aivideocreator.ui.feeding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.entities.Feeding
import com.okation.aivideocreator.data.repository.DaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FeedingViewModel @Inject constructor(private val repository: DaoRepository) : ViewModel() {
    private val _selectedDate = MutableLiveData<String>()
    private val selectedDate: LiveData<String> = _selectedDate

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }
    fun saveFeedingToDB(feedingTime : String, feedingAmount : String, feedingNote:String,feedingDate : String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val newFeeding = Feeding(feedingTime = feedingTime, feedingAmount = feedingAmount, feedingNote = feedingNote, feedingDate = feedingDate)
                repository.insertFeeding(newFeeding)
            }
        }
    }

    private val _feeding = selectedDate.switchMap {switchedFeeding->
        if (switchedFeeding!=null){
            repository.getFeedingByDate(switchedFeeding)
        }else{
            MutableLiveData()
        }
    }
    val feeding : LiveData<List<Feeding>> = _feeding
}