package br.com.caelum.twittelumappweb.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.webservice.UsuarioWebClient

class UsuarioRepository(private val service: UsuarioWebClient) {

    private var usuarioMLD = MutableLiveData<Usuario>()
    private var erroMLD = MutableLiveData<Throwable>()
    private var estaLogado = MutableLiveData<Boolean>()

    fun usuarioLogado() : LiveData<Usuario> = usuarioMLD
    fun estaLogado() : LiveData<Boolean> = estaLogado
    fun erro(): LiveData<Throwable> = erroMLD

    private val sucesso =
            { usuario: Usuario ->
                usuarioMLD.value = usuario
                estaLogado.value = true
            }

    private val falha =
            { erro: Throwable ->
                erroMLD.value = erro
                estaLogado.value = false
            }

    fun cria(usuario: Usuario) = service.cria(usuario, sucesso, falha)

    fun loga(usuario: Usuario) = service.loga(usuario, sucesso, falha)

    fun desloga() {
        usuarioMLD.value = Usuario("", "", "")
        estaLogado.value = false
    }

}
