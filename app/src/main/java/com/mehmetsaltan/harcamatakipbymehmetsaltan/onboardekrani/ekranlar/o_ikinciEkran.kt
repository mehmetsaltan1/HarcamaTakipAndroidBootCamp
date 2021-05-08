package com.mehmetsaltan.harcamatakipbymehmetsaltan.onboardekrani.ekranlar

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mehmetsaltan.harcamatakipbymehmetsaltan.R
import kotlinx.android.synthetic.main.fragment_o_ikinci_ekran.view.*
import kotlinx.android.synthetic.main.fragment_o_ilk_ekran.view.*

class o_ikinciEkran : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_o_ikinci_ekran, container, false)

        view.gecbutonu2.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_isimCinsiyetDegistir2)
            onBoardBitir()
        }
        return view
    }
private fun onBoardBitir(){
    val sharedpref=requireActivity().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
    val editor=sharedpref.edit()
    editor.putBoolean("Bitti",true)
    editor.apply()
}

}