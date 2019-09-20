package br.com.caelum.twittelumappweb.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.webservice.TweetWebClient

class TweetRepository(private val service: TweetWebClient) {

    private val listaMLD = MutableLiveData<List<Tweet>>()
    private val erroMLD = MutableLiveData<Throwable>()

    private val sucesso = { tweets: List<Tweet> ->
        listaMLD.postValue(tweets)
    }

    private val falha = { erro: Throwable ->
        erroMLD.value = erro
    }

    fun salva(tweet: Tweet) = service.salva(tweet, sucesso, falha)

    fun buscaTweets() {
        service.lista(sucesso, falha)
    }

    fun lista() : LiveData<List<Tweet>> = listaMLD
    fun erro() : LiveData<Throwable> = erroMLD

}