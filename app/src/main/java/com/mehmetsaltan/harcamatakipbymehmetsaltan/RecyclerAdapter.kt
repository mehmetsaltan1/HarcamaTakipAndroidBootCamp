package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mehmetsaltan.harcamatakipbymehmetsaltan.databinding.RecyclerviewCardTasarimiBinding
import kotlinx.android.synthetic.main.recyclerview_card_tasarimi.view.*
import java.math.RoundingMode

class MyAdapter(): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var harcama = emptyList<Harcama>()

  class MyViewHolder(val binding: RecyclerviewCardTasarimiBinding): RecyclerView.ViewHolder(binding.root)
 {
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerviewCardTasarimiBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return harcama.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.gKalem.text = harcama[position].harcamaAciklama
        holder.binding.gIcon.load(harcama[position].harcamaIcon)
        var gFiyat=holder.binding.gFiyat
        var harcamaFiyat=harcama[position].harcamaFiyat
        when (tiklananbuton) {
            1 -> {
                gFiyat.text=harcamaFiyat.toString() +" ₺"
            }
            2 -> {
                gFiyat.text=(harcamaFiyat/g_usdTry).toBigDecimal().setScale(0,RoundingMode.UP).toString()+" $"
            }
            3 -> {
               gFiyat.text=(harcamaFiyat/ g_gbpTry).toBigDecimal().setScale(1,RoundingMode.UP).toInt().toString() +" £"
            }
            4 -> {
                gFiyat.text=(harcamaFiyat/ g_eurTry).toBigDecimal().setScale(1,RoundingMode.UP).toInt().toString() +" €"
            }
            else -> {
                gFiyat.text=harcamaFiyat.toString()+" ₺"
                 //TobigDecimal methodunu kullanmamın nedeni double gelen bir veri ile ınt olan bir veriyi çarpma işlemine tabi tuttuktan sonra tekrar integer a -
                // parse ettiğimde yaşadığım çok küçük bir veri kaybından dolayı harcamamı eklediğim cinsindeki fiyattan bir miktar az olarak gözükmesiydi -
                // bu method sayesinde bu veri kaybını engelledim.
            }
        }

        val secilenharcama=harcama[position]
        holder.binding.cardRecycler.setOnClickListener {//Recyclerview öğemizin içinde bulunan itemlerimize tıklanma özelliği katıp navigationun -
                                                         // özelliklerinden haydalanıp argüment sayesinde harcama öğemizi detay sayfasına gönderdik
          val aksiyon=AnaHarcamaEkraniDirections.actionAnaHarcamaEkraniToHarcamaDetay(secilenharcama)
            holder.itemView.findNavController().navigate(aksiyon)
        }
    }


    fun setData(harcama: List<Harcama>){
        this.harcama = harcama
        notifyDataSetChanged()
    }
}