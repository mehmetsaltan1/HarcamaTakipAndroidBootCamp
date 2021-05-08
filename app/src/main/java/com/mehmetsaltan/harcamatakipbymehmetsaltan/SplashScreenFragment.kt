package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mehmetsaltan.harcamatakipbymehmetsaltan.R


class SplashScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val splashscreengeczaman=4000
        Handler().postDelayed({
             if (onBoardBitir() && cinsiyetSayfaBitir()){
                 findNavController().navigate(R.id.action_splashScreenFragment_to_anaHarcamaEkrani)
             }
            else if (onBoardBitir()==false && cinsiyetSayfaBitir()==false){
                 findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
             }
            else if(onBoardBitir() && cinsiyetSayfaBitir()==false){
                 findNavController().navigate(R.id.action_splashScreenFragment_to_isimCinsiyetDegistir)
             }
            else{
                 findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
             }



        },splashscreengeczaman.toLong())
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }


    private fun onBoardBitir():Boolean {
        val sharedpref=requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
      return  sharedpref.getBoolean("Bitti",false)

    }
    private fun cinsiyetSayfaBitir() : Boolean{
        val sharedpref2=requireActivity().getSharedPreferences("İsimCins", Context.MODE_PRIVATE)
        return sharedpref2.getBoolean("Bitti",false)
    }
}