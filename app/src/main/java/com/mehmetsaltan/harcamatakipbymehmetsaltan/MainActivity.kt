package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.mehmetsaltan.harcamatakipbymehmetsaltan.api.KurModel
import com.mehmetsaltan.harcamatakipbymehmetsaltan.api.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var euroTry = 10.0
var gbpTry = 11.0
var usdTry=8.0
//Global değişken yapmamın nedeni bütün sınıflarımdan kolaylıkla erişebilmek
class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://free.currconv.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        veriCek()
        //Verilerimi mainactivityde çağırmamın nedeni her uygulama açılışında internet var ise verileri çeker yoksa
        //kaydedilen veriler üzerinden çalışmaya devam eder ve en önemlisi
        //bunu sadece çalışırken yapmasından ötürü sürekli apiye istek atık apiyi yormaz.
    }

    fun veriCek() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val usdKaydet=this.getSharedPreferences("Doviz", Context.MODE_PRIVATE)
        val editor=usdKaydet.edit()
        val service = retrofit.create(Service::class.java)
        val call = service.getCurrencyDolar()
        val call2 = service.getCurrencyEuro()
        val call3 = service.getCurrencySterlin()

        call.enqueue(object : Callback<KurModel> {
            override fun onFailure(call: Call<KurModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<KurModel>, response: Response<KurModel>) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        usdTry = it.uSDTRY
                        editor.putLong("dolarKaydet", usdTry.toBits())  //Sharedprefrences ta double veri kaydedilmediği için bit olarak kaydedip verileri aldığım -
                        editor.apply()                                        //bölümde bitten double a dönüştürdüm aynısını diğer para birimleri için de yaptım

                    }
                }
            }
        })

        call2.enqueue(object : Callback<KurModel> {
            override fun onFailure(call2: Call<KurModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call2: Call<KurModel>, response: Response<KurModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        euroTry = it.eURTRY
                        editor.putLong("euroKaydet", euroTry.toBits())
                        editor.apply()

                    }
                }
            }
        })
        call3.enqueue(object : Callback<KurModel> {
            override fun onFailure(call3: Call<KurModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call3: Call<KurModel>, response: Response<KurModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        gbpTry = it.gBPTRY
                        editor.putLong("sterlinKaydet", gbpTry.toBits())
                        editor.apply()

                    }
                }
            }
        })

    }
}