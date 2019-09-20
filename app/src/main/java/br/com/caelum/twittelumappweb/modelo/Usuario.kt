package br.com.caelum.twittelumappweb.modelo

data class Usuario(
        val nome: String,
        val username: String,
        val senha: String,
        val id: Int? = 0,
        val foto: String? = null
)