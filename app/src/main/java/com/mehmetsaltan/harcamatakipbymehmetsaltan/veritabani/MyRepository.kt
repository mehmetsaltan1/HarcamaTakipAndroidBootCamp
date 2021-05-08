package com.mehmetsaltan.harcamatakipbymehmetsaltan.veritabani

import androidx.lifecycle.LiveData
import com.mehmetsaltan.harcamatakipbymehmetsaltan.Harcama

class MyRepository(private val myDao: MyDao) {

    val harcamaOku: LiveData<List<Harcama>> = myDao.harcamaOku()

    suspend fun harcamaEkle(harcama: Harcama){
        myDao.harcamaEkle(harcama)
    }
    suspend fun harcamaSil(harcama: Harcama){
        myDao.harcamaSil(harcama)
    }

}