package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.SearchView
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_lista.*

class PesquisaFragment : Fragment() {

    private var todosOsTweets : List<Tweet> = listOf()

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        viewModel.lista().observe(this, Observer { tweets ->
            tweets?.let { todosOsTweets = it }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_pesquisa_fragment, menu)
        val menuBuscar = menu.findItem(R.id.menu_buscar)
        val searchView = menuBuscar.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(texto: String?): Boolean = false
            override fun onQueryTextChange(texto: String): Boolean {


                val tweets = if (texto.isNullOrBlank()) {
                    listOf<Tweet>()
                } else {
                    filtra(todosOsTweets, texto)
                }

                lista.adapter = TweetAdapter(tweets)

                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    fun filtra(tweets: List<Tweet>, texto: String): List<Tweet> {
        return tweets.filter { it.mensagem.contains(texto, true) }
    }
}