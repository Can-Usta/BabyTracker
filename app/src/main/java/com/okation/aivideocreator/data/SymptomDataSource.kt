package com.okation.aivideocreator.data

import com.okation.aivideocreator.R
import com.okation.aivideocreator.data.model.SymptomsDetail

class SymptomDataSource {
    fun loadSymptoms(): List<SymptomsDetail> {
        return listOf(
            SymptomsDetail(R.drawable.img_runny_nose, R.string.runny_nose),
            SymptomsDetail(R.drawable.img_heartburn, R.string.heartburn),
            SymptomsDetail(R.drawable.img_no_apetit, R.string.no_appetite),
            SymptomsDetail(R.drawable.img_rush, R.string.rush),
            SymptomsDetail(R.drawable.img_low_energy, R.string.low_energy),
            SymptomsDetail(R.drawable.img_nausea, R.string.nausea),
            SymptomsDetail(R.drawable.img_cough, R.string.cough),
            SymptomsDetail(R.drawable.img_fever, R.string.fever),
        )
    }
}