package br.com.caelum.twittelumappweb.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragment.ListaFragment
import br.com.caelum.twittelumappweb.fragment.MapsFragment
import br.com.caelum.twittelumappweb.fragment.PesquisaFragment
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel : UsuarioViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(UsuarioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configuraBottomNavigationListener()
        novo_tweet.setOnClickListener {
            startActivity(Intent(this, TweetActivity::class.java))
        }
        bottonNavigation.selectedItemId = R.id.menu_list
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sair -> {
                viewModel.desloga()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraBottomNavigationListener() {
        bottonNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_list -> {
                    exibeFragment(ListaFragment())
                    setTitle("Lista")
                }
                R.id.menu_search -> {
                    exibeFragment(PesquisaFragment())
                    setTitle("Pesquisa")
                }
                R.id.menu_maps -> {
                    exibeFragment(MapsFragment())
                    setTitle("Mapão")
                }
            }
            true
        }
    }


    fun exibeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit()
    }

}
