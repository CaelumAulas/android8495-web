package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : SupportMapFragment() {

    private val viewModel : TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        getMapAsync { mapa ->

            val tweets = viewModel.lista().value

            tweets?.forEach {
                val markerOptions = MarkerOptions()
                markerOptions.title(it.usuario.nome)
                markerOptions.snippet(it.mensagem)
                val posicao = LatLng(it.latitude, it.longitude)

                markerOptions.position(posicao)
                mapa.addMarker(markerOptions)
            }


        }
    }

}
