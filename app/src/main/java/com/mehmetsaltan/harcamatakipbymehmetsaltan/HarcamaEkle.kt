package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.android.synthetic.main.fragment_harcama_ekle.*
import kotlinx.coroutines.launch
import java.math.RoundingMode

class HarcamaEkle : Fragment() {

    var gidenIconDrawable = R.drawable.digericon //Harcamanın gider türüne göre veri tabanıma bir icon göndermem gerekiyordu bunu bir değişkende tutup radio butona tıklandığında değiştirdim
    private lateinit var myViewModel: MyViewModel
    var harcamaFiyat = 0  //Icon için yaptığımın aynısını harcamanın fiyatı için de yaptım
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_harcama_ekle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        rdb_grup_giderkalemi.setOnCheckedChangeListener { radioGroup, i ->
            var radio: RadioButton = view.findViewById(i)
            when (radio) { //Üstte belirttiğim gibi seçilen Radio butona göre iconun resmini değiştirdim
                rdb_Fatura -> {
                    gidenIconDrawable = R.drawable.faturaicon
                }
                rdb_Kira -> {
                    gidenIconDrawable = R.drawable.kiraicon
                }
                rdb_Diger -> {
                    gidenIconDrawable = R.drawable.digericon
                }
            }
        }
        rdb_grup_gider_cinsi.setOnCheckedChangeListener { radioGroup, i ->
            var radiofiyat: RadioButton = view.findViewById(i)
            var gidHarcamaFiyat=txt_harcama.text.toString().toInt()
            when (radiofiyat) {  //Burada da iconda yaptığım gibi seçilen radio butona göre harcama fiyatını değiştirdim
                rdb_Tl -> {
                    harcamaFiyat = gidHarcamaFiyat
                }
                rdb_Dolar -> {
                    harcamaFiyat = (gidHarcamaFiyat * g_usdTry).toBigDecimal().setScale(1, RoundingMode.UP).toInt()
                }
                rdb_Euro -> {
                   harcamaFiyat = (gidHarcamaFiyat * g_eurTry).toBigDecimal().setScale(1, RoundingMode.UP).toInt()
                }
                rdb_Sterlin -> {
                   harcamaFiyat = (gidHarcamaFiyat * g_gbpTry).toBigDecimal().setScale(1, RoundingMode.UP).toInt()
                }
                //TobigDecimal methodunu kullanmamın nedeni double gelen bir veri ile ınt olan bir veriyi çarpma işlemine tabi tuttuktan sonra tekrar integer a -
                // parse ettiğimde yaşadığım çok küçük bir veri kaybından dolayı harcamamı eklediğim cinsindeki fiyattan bir miktar az olarak gözükmesiydi -
                // bu method sayesinde bu veri kaybını engelledim.
            }
        }

        btn_HarcamaEkle.setOnClickListener {
            if (!txt_aciklama.text.isNullOrEmpty() && !txt_harcama.text.isNullOrEmpty() && rdb_grup_giderkalemi.checkedRadioButtonId != -1 && rdb_grup_gider_cinsi.checkedRadioButtonId != -1) { //Değerlerin boş olmadığını if koşuluyla kontrol ettim
               if (harcamaFiyat==0){
                   Toast.makeText(context, "Harcama bedelsiz olmaz :)", Toast.LENGTH_LONG).show()
               }
                else{
                   lifecycleScope.launch {
                       val harcama = Harcama(txt_aciklama.text.toString(), harcamaFiyat, getBitmap())
                       myViewModel.harcamaEkle(harcama)
                   }
                   findNavController().navigate(R.id.action_harcamaEkle_to_anaHarcamaEkrani) //Harcama başarılı olursa  ana harcama ekranına döndürdüm.
               }

            } else {
                Toast.makeText(context, "Lütfen bütün alanları doldurun", Toast.LENGTH_LONG).show()
            }
        }
    }
    private suspend fun getBitmap(): Bitmap { //Uygulamada en çok işime yarayan özellik Gelen resmi Bitmap e dönüştüren fonksiyonum Fonksiyonumda kullandığım özellikler internette araştırarak bulduğum coil kütüphanesine ait
        val loading = ImageLoader(this.requireContext())
        val request = ImageRequest.Builder(this.requireContext())
            .data(gidenIconDrawable)
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

}




