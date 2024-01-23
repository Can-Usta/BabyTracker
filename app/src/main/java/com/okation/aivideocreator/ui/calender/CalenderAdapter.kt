package com.okation.aivideocreator.ui.calender

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.okation.aivideocreator.ui.calender.alllist.AllListFragment
import com.okation.aivideocreator.ui.feeding.feedinglist.FeedingListFragment
import com.okation.aivideocreator.ui.sleeping.sleepinglist.SleepingListFragment
import com.okation.aivideocreator.ui.symptom.symptomlist.SymptomListFragment

class CalenderAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){

            0 -> AllListFragment()
            1-> FeedingListFragment()
            2 -> SleepingListFragment()
            3-> SymptomListFragment()
            else -> throw IllegalStateException()
        }
    }

}