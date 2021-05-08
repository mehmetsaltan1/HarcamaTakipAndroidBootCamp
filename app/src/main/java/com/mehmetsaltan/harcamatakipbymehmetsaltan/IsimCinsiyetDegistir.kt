package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.mehmetsaltan.harcamatakipbymehmetsaltan.databinding.FragmentIsimCinsiyetDegistirBinding
import kotlinx.android.synthetic.main.fragment_isim_cinsiyet_degistir.*

class IsimCinsiyetDegistir : Fragment(R.layout.fragment_isim_cinsiyet_degistir) {
    private lateinit var isim:String
    private lateinit var cinsiyet:String
    lateinit var binding: FragmentIsimCinsiyetDegistirBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      binding=FragmentIsimCinsiyetDegistirBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     binding.radioGroupCins.setOnCheckedChangeListener { radioGroup, i ->
            var  radio:RadioButton=view.findViewById(i)
            isim=binding.txtIsim.text.toString()
            cinsiyet =radio.text.toString()
            IsimCinsiyetBelirle()
        }


        btn_KaydetCins_isim.setOnClickListener {
            if (binding.txtIsim.text.isNullOrEmpty() || binding.radioGroupCins.checkedRadioButtonId==-1 ) {
                Toast.makeText(context,"İsim veya Cinsiyet Boş Olamaz",Toast.LENGTH_LONG).show()
                }
            else{
                    cinsiyetSayfaBitir()
                    findNavController().navigate(R.id.action_isimCinsiyetDegistir_to_anaHarcamaEkrani)
                }
        }



    }
    private fun cinsiyetSayfaBitir(){
        val sharedpref2=requireActivity().getSharedPreferences("İsimCins", Context.MODE_PRIVATE)
        val editor=sharedpref2.edit()
        editor.putBoolean("Bitti",true)
        editor.apply()
    }
    fun IsimCinsiyetBelirle(){
        val isimcinspref=requireActivity().getSharedPreferences("IsimCinsBelirle",Context.MODE_PRIVATE)
        val isimcinseditor=isimcinspref.edit()
        isimcinseditor.putString("isim",isim)
        isimcinseditor.putString("cins",cinsiyet)
        isimcinseditor.apply()
    }


}