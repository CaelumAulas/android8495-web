package br.com.caelum.twittelumappweb.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioViewModel(private val repositorio: UsuarioRepository) : ViewModel() {

    fun cria(usuario: Usuario) = repositorio.cria(usuario)

    fun loga(usuario: Usuario) = repositorio.loga(usuario)

    fun desloga() = repositorio.desloga()

    fun usuarioLogado() = repositorio.usuarioLogado()

    fun estaLogado() = repositorio.estaLogado()

    fun erro() = repositorio.erro()
}