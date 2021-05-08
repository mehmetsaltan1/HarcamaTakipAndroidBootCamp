package com.mehmetsaltan.harcamatakipbymehmetsaltan

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tl_table")
data class Harcama(
    val harcamaAciklama: String,
    val harcamaFiyat: Int,
    val harcamaIcon: Bitmap
): Parcelable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}