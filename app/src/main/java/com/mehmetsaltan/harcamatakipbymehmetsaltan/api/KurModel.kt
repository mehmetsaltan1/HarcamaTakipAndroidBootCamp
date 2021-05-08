package com.mehmetsaltan.harcamatakipbymehmetsaltan.api


import com.google.gson.annotations.SerializedName


data class KurModel(
    @SerializedName("EUR_TRY")
    val eURTRY: Double,
    @SerializedName("GBP_TRY")
    val gBPTRY: Double,
    @SerializedName("USD_TRY")
    val uSDTRY: Double
)