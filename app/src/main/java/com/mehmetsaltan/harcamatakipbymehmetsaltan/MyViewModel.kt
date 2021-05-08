package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mehmetsaltan.harcamatakipbymehmetsaltan.veritabani.MyRepository
import com.mehmetsaltan.harcamatakipbymehmetsaltan.veritabani.VeriTabanim
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {

    private val dao = VeriTabanim.getDatabase(application).myDao()
    private val repository = MyRepository(dao)

    val harcamaOku: LiveData<List<Harcama>> = repository.harcamaOku

    fun harcamaEkle(harcama: Harcama){
        viewModelScope.launch(Dispatchers.IO) {
            repository.harcamaEkle(harcama)
        }
    }
    fun harcamaSil(harcama: Harcama){
        viewModelScope.launch(Dispatchers.IO){
            repository.harcamaSil(harcama)
        }
    }

}