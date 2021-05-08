package com.mehmetsaltan.harcamatakipbymehmetsaltan.onboardekrani.ekranlar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.mehmetsaltan.harcamatakipbymehmetsaltan.R
import kotlinx.android.synthetic.main.fragment_o_ilk_ekran.view.*

class o_ilkEkran : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_o_ilk_ekran, container, false)
        val viewPager=activity?.findViewById<ViewPager2>(R.id.viewPager)
        view.gecbutonu.setOnClickListener {
            viewPager?.currentItem=1
        }
        return view
    }

   
}