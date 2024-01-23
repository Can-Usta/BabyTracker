package com.okation.aivideocreator.ui.symptom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.okation.aivideocreator.data.SymptomDataSource
import com.okation.aivideocreator.data.entities.Symptoms
import com.okation.aivideocreator.data.model.SymptomsDetail
import com.okation.aivideocreator.data.repository.DaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SymptomsViewModel @Inject constructor(private val repository: DaoRepository) : ViewModel(){

    private val dataSource = SymptomDataSource()
    var _symptomsDetail = MutableLiveData(dataSource.loadSymptoms())
    var symptomsDetail: LiveData<List<SymptomsDetail>> = _symptomsDetail


    fun setSymptomSelectStatus(item: SymptomsDetail) {
        _symptomsDetail.value = symptomsDetail.value?.map {
            if (item == it) it.copy(isSelected = it.isSelected.not())
            else it
        }
    }

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun saveSymptomsToDb(time: String, symptomName: String, note: String,symptomDate : String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val symptoms = Symptoms(symptomTime = time, symptomName = symptomName, symptomsNote = note, symptomDate = symptomDate)
                repository.insertSymptoms(symptoms)
            }
        }
    }

    private val _symptoms = selectedDate.switchMap {switchedSymptoms->
        if (switchedSymptoms !=null){
            repository.getSymptomsByDate(switchedSymptoms)
        }else{
            MutableLiveData()
        }
    }
    val symptoms : LiveData<List<Symptoms>> = _symptoms

    fun clearSymptomsSelection() {
        val currentSymptoms = _symptomsDetail.value ?: return
        val updatedSymptoms = currentSymptoms.map { it.copy(isSelected = false) }
        _symptomsDetail.value = updatedSymptoms
    }


}