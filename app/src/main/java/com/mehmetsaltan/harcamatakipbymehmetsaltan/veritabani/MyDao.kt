package com.mehmetsaltan.harcamatakipbymehmetsaltan.veritabani

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mehmetsaltan.harcamatakipbymehmetsaltan.Harcama

@Dao
interface MyDao {

    @Query("SELECT * FROM tl_table ORDER BY id ASC")
    fun harcamaOku(): LiveData<List<Harcama>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun harcamaEkle(harcama: Harcama)
    @Delete
    suspend fun harcamaSil(harcama: Harcama)

}