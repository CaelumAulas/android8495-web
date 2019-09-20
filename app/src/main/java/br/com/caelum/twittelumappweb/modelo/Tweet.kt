package br.com.caelum.twittelumappweb.modelo

import com.google.gson.annotations.SerializedName

data class Tweet(
    val mensagem: String,
    val foto: String?,
    val id: Int = 0,

    @SerializedName("dono")
    val usuario: Usuario
) {

    override fun toString(): String {
        return mensagem
    }

}