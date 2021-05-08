package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetsaltan.harcamatakipbymehmetsaltan.api.KurModel
import com.mehmetsaltan.harcamatakipbymehmetsaltan.api.Service
import kotlinx.android.synthetic.main.fragment_ana_harcama_ekrani.*
import kotlinx.android.synthetic.main.fragment_ana_harcama_ekrani.view.*
import kotlinx.android.synthetic.main.fragment_harcama_ekle.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.RoundingMode

var tiklananbuton:Int=0 //Global değişken yapmamın nedeni bütün sınıflarımdan kolaylıkla erişebilmek
var g_usdTry:Double=0.0
var g_eurTry:Double=0.0
var g_gbpTry:Double=0.0

class AnaHarcamaEkrani : Fragment()  {

 private lateinit var g_isim :String
    private lateinit var g_cins :String
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Shared prefle daha önce isim cinsiyet sayfamda kaydettiğim verileri getirip ona göre ana sayfadaki texti değiştirdim
        val sharedprefgelenisimcins=requireActivity().getSharedPreferences("IsimCinsBelirle",Context.MODE_PRIVATE)
         g_isim=sharedprefgelenisimcins.getString("isim","").toString()
        g_cins=sharedprefgelenisimcins.getString("cins","").toString()
        dovizGetir()
    }
fun dovizGetir(){
    val gelendoviz=requireActivity().getSharedPreferences("Doviz",Context.MODE_PRIVATE)
    g_usdTry=Double.fromBits(gelendoviz.getLong("dolarKaydet",1))
    g_eurTry=Double.fromBits(gelendoviz.getLong("euroKaydet",2))
    g_gbpTry=Double.fromBits(gelendoviz.getLong("sterlinKaydet",0))
    //Shared preferences ile kaydettiğim bit verilerini double a dönüştürüp getirdim.
}
    fun cinsIsimYaz(){
        when {
            g_cins.equals("Erkek") -> { txt_isim_cinsiyet.text="Merhaba\n$g_isim Bey" }
            g_cins.equals("Kadin") -> { txt_isim_cinsiyet.text="Merhaba\n$g_isim Hanım" }
            g_cins.equals("Belirtmek İstemiyorum") -> { txt_isim_cinsiyet.text="Merhaba\n$g_isim" }
            else -> { txt_isim_cinsiyet.text="Merhaba null" }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_ana_harcama_ekrani, container, false)
return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_isim_cinsiyet.setOnClickListener {
            findNavController().navigate(R.id.action_anaHarcamaEkrani_to_isimCinsiyetDegistir)
        }
        cinsIsimYaz() //Anasayfada bulunan cinsiyete göre isim belirlemesinin fonksiyonunu çağırdım.


        val adapter= MyAdapter()
        var toplam = 0.0  //Burada harcama toplamını 0 a eşitlememin nedeni harcama ekranında eklediğim veri anaharcama ekranı görünümüme eklenip anaharcama ekranına döndüğüm için önceden tuttuğum toplam değeri yerine yeniden toplam değeri hesaplamam
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        recyclerview.layoutManager= LinearLayoutManager(context)
        recyclerview.adapter=adapter
        myViewModel.harcamaOku.observe(this.requireActivity(), {
            adapter.setData(it) //veri tabanından gelen verilerimi recyclerview adapterim sayesinde recycler view ıma ekledim
             toplam=0.0
            for (i in it) {            //Toplam harcamamı for döngüsü yardımıyla buldum
                toplam = (i.harcamaFiyat + toplam)
            }
          view.txt_toplamHarcama.text="Harcamanız : ${toplam.toInt()}" +" ₺"
        })

        btn_TL.setOnClickListener {
            btn_TL.setTextColor(Color.parseColor("#e23694"))
            btn_Euro.setTextColor(Color.BLACK)
            btn_Sterlin.setTextColor(Color.BLACK)
            btn_Dolar.setTextColor(Color.BLACK)
            tiklananbuton =1
            view.txt_toplamHarcama.text="Harcamanız : ${toplam.toInt()}" +" ₺"
            adapter.notifyDataSetChanged()//recyclerview içinde bulunan elemanımın değerinin değiştiğini bildiriyorum.
        }
       btn_Dolar.setOnClickListener {
           btn_Dolar.setTextColor(Color.parseColor("#e23694"))
           btn_TL.setTextColor(Color.BLACK)
           btn_Euro.setTextColor(Color.BLACK)
           btn_Sterlin.setTextColor(Color.BLACK)
           tiklananbuton =2
         view.txt_toplamHarcama.text="Harcamanız : ${(toplam/ g_usdTry).toBigDecimal().setScale(0,RoundingMode.UP)}"+" $"
           adapter.notifyDataSetChanged()//recyclerview içinde bulunan elemanımın değerinin değiştiğini bildiriyorum.
        }

        btn_Sterlin.setOnClickListener {
            btn_TL.setTextColor(Color.BLACK)
            btn_Euro.setTextColor(Color.BLACK)
            btn_Sterlin.setTextColor(Color.parseColor("#e23694"))
            btn_Dolar.setTextColor(Color.BLACK)
            tiklananbuton =3
          view.txt_toplamHarcama.text="Harcamanız : ${(toplam/ g_gbpTry).toBigDecimal().setScale(1,RoundingMode.UP).toInt()}"+" £"
            adapter.notifyDataSetChanged()  //recyclerview içinde bulunan elemanımın değerinin değiştiğini bildiriyorum.
        }
        btn_Euro.setOnClickListener {
            btn_TL.setTextColor(Color.BLACK)
            btn_Euro.setTextColor(Color.parseColor("#e23694"))
            btn_Sterlin.setTextColor(Color.BLACK)
            btn_Dolar.setTextColor(Color.BLACK)
            tiklananbuton =4
           view.txt_toplamHarcama.text="Harcamanız : ${(toplam/ g_eurTry).toBigDecimal().setScale(1,RoundingMode.UP).toInt()}"+" €"
            adapter.notifyDataSetChanged()//recyclerview içinde bulunan elemanımın değerinin değiştiğini bildiriyorum.
        }
        btn_Ekle.setOnClickListener {
            findNavController().navigate(R.id.action_anaHarcamaEkrani_to_harcamaEkle)
        }
    }

}


