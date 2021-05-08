package com.mehmetsaltan.harcamatakipbymehmetsaltan.onboardekrani

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    list:ArrayList<Fragment>,
    fm:FragmentManager,lifecycle: Lifecycle
):FragmentStateAdapter(fm,lifecycle){
    val fragmentlistesi=list
    override fun getItemCount(): Int {
       return fragmentlistesi.size
    }

    override fun createFragment(position: Int): Fragment {
       return fragmentlistesi[position]
    }
}