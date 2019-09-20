package br.com.caelum.twittelumappweb

import android.content.Context
import android.location.Location
import com.google.android.gms.location.*

class GPS(context: Context) : LocationCallback() {

    private var localizacao : Location? = null
    private val client: FusedLocationProviderClient
            = LocationServices.getFusedLocationProviderClient(context)

    override fun onLocationResult(result: LocationResult?) {
        localizacao = result?.lastLocation
    }

    fun fazBusca() {
        val requisicaoLocalizacao = LocationRequest()

        requisicaoLocalizacao.apply {
            interval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            smallestDisplacement = 10.0F
        }

        client.requestLocationUpdates(requisicaoLocalizacao, this, null)
    }

    fun cancela(){
        client.removeLocationUpdates(this)
    }

    fun pegaCoordenadas() : Pair<Double, Double> {
        val latitude = localizacao?.latitude ?: 0.0
        val longitude = localizacao?.longitude ?: 0.0

        return Pair(latitude, longitude)
    }
}