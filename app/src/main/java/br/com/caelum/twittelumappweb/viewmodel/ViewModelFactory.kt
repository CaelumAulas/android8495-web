package br.com.caelum.twittelumappweb.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.webservice.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.webservice.TweetWebClient
import br.com.caelum.twittelumappweb.webservice.UsuarioWebClient
import java.lang.RuntimeException

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {


    private val retrofit = InicializadorDoRetrofit.cria()

    private val tweetWebClient = TweetWebClient(retrofit)
    private val tweetViewModel = TweetViewModel(TweetRepository(tweetWebClient))

    private val usuarioServiceClient = UsuarioWebClient(retrofit)
    private val usuarioViewModel = UsuarioViewModel(UsuarioRepository(usuarioServiceClient))

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            when (modelClass) {
                TweetViewModel::class.java -> tweetViewModel as T
                UsuarioViewModel::class.java -> usuarioViewModel as T
                else -> throw RuntimeException("Não achei o $modelClass")
            }

}