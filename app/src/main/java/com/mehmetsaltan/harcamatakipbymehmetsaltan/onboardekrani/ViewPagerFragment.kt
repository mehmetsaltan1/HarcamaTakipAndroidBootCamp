package com.mehmetsaltan.harcamatakipbymehmetsaltan.onboardekrani

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mehmetsaltan.harcamatakipbymehmetsaltan.R
import com.mehmetsaltan.harcamatakipbymehmetsaltan.onboardekrani.ekranlar.o_ikinciEkran
import com.mehmetsaltan.harcamatakipbymehmetsaltan.onboardekrani.ekranlar.o_ilkEkran
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentlistesi= arrayListOf<Fragment>(
            o_ilkEkran(),
            o_ikinciEkran()
        )
        val adapter=ViewPagerAdapter(
            fragmentlistesi,
            requireActivity().supportFragmentManager,
        lifecycle
        )
        view.viewPager.adapter=adapter
        return view
    }


}