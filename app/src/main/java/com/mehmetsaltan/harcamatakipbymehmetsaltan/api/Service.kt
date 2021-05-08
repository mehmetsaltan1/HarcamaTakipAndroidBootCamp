package com.mehmetsaltan.harcamatakipbymehmetsaltan.api

import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET

interface Service {
    @GET("/api/v7/convert?q=TRY_USD,USD_TRY&compact=ultra&apiKey=8e3eac073e09cb20294e")
    fun getCurrencyDolar() : Call<KurModel>
    @GET("/api/v7/convert?q=TRY_EUR,EUR_TRY&compact=ultra&apiKey=8e3eac073e09cb20294e")
    fun getCurrencyEuro() : Call<KurModel>
    @GET("/api/v7/convert?q=TRY_GBP,GBP_TRY&compact=ultra&apiKey=8e3eac073e09cb20294e")
    fun getCurrencySterlin() : Call<KurModel>

}
