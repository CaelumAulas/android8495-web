package br.com.caelum.twittelumappweb.webservice

import br.com.caelum.twittelumappweb.modelo.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class TweetWebClient(retrofit: Retrofit) {

    private val service = retrofit.create(TweetService::class.java)

    fun lista(
            sucesso: (tweets: List<Tweet>) -> Unit,
            falha: (erro: Throwable) -> Unit
    ) {
        service.lista().enqueue(object : Callback<List<Tweet>>{
            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                response.body()?.let(sucesso)
            }
        })
    }

    fun salva(tweet: Tweet, sucesso: (List<Tweet>) -> Unit, falha: (Throwable) -> Unit) {
        service.salva(tweet).enqueue(object : Callback<List<Tweet>>{
            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                response.body()?.let(sucesso)
            }
        })
    }


    interface TweetService {

        @GET("tweet")
        fun lista() : Call<List<Tweet>>

        @POST("tweet")
        fun salva(@Body tweet: Tweet) : Call<List<Tweet>>
    }
}