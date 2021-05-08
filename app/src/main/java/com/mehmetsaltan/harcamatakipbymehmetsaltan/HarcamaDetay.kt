package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.android.synthetic.main.fragment_harcama_detay.*
import kotlinx.android.synthetic.main.fragment_harcama_detay.view.*
import java.math.RoundingMode


class HarcamaDetay : Fragment() {

    private lateinit var myViewModel: MyViewModel
    private val args by navArgs<HarcamaDetayArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view= inflater.inflate(R.layout.fragment_harcama_detay, container, false)
        view.detay_gelenkalem.setText(args.gelenHarcama.harcamaAciklama) //Burada navigationun özellilerinden olan argüment gönderme işlevini kullanarak tıklanan harcamayı detay sayfama aktardım
                                                                         //Fakat bunu yapabilmem için Harcama sınıfımı Parcelable yapmam gerektiğini de aktarmam lazım.
        var fiyat=args.gelenHarcama.harcamaFiyat
        var gFiyat=view.detay_gelenfiyat
        when (tiklananbuton) {
            1 -> {
                gFiyat.setText(fiyat.toString()+" ₺")
            }
            2 -> {
                gFiyat.setText(((fiyat/ g_usdTry)).toBigDecimal().setScale(0,
                     RoundingMode.UP).toString()+" $")
            }
            3 -> {
                gFiyat.setText((fiyat/ g_gbpTry).toBigDecimal().setScale(1,
                RoundingMode.UP).toInt().toString()+" £")
            }
            4 -> {
                gFiyat.setText((fiyat/ g_eurTry).toBigDecimal().setScale(1,
                     RoundingMode.UP).toInt().toString()+" €")
            }
            else -> {
                gFiyat.setText(fiyat.toString()+" ₺")
            }
        }
        view.detay_gelenicon.load(args.gelenHarcama.harcamaIcon)
        return view
    }
    fun harcamaSil(){
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        val builder =AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Evet"){_,_ ->
            myViewModel.harcamaSil(args.gelenHarcama)
            Toast.makeText(requireContext(),"Harcama başarılı bir şekilde silindi",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_harcamaDetay_to_anaHarcamaEkrani)
        }
        builder.setNegativeButton("Hayır"){_,_ ->
        }
        builder.setTitle("Sil ${args.gelenHarcama.harcamaAciklama}?")
        builder.setMessage("Harcamayı silmek istediğinden emin misin ?")
        builder.create().show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     btn_sil.setOnClickListener {
         harcamaSil()
     }

        btn_geri.setOnClickListener {
            findNavController().navigate(R.id.action_harcamaDetay_to_anaHarcamaEkrani)
        }

    }


}