package br.com.caelum.twittelumappweb.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InicializadorDoRetrofit {
    fun cria() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://twittelum-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}