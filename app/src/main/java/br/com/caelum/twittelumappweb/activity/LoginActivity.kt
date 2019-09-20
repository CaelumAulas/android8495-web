package br.com.caelum.twittelumappweb.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel : UsuarioViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(UsuarioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_entrar.setOnClickListener { viewModel.loga(usuarioDaTela()) }
        login_criar.setOnClickListener { viewModel.cria(usuarioDaTela()) }

        viewModel.estaLogado().observe(this, Observer {
            if(it == true){
                val vaiPraMainActivity = Intent(this, MainActivity::class.java)
                startActivity(vaiPraMainActivity)
                finish()
            }
        })

        viewModel.erro().observe(this, Observer {
            Toast.makeText(this, "Erro ao logar, tente novamente mais tarde", Toast.LENGTH_LONG).show()
            Log.i("erro", "$it")
        })
    }

    private fun usuarioDaTela(): Usuario {
        val nome = login_campoNome.text.toString()
        val senha = login_campoSenha.text.toString()
        val username = login_campoUsername.text.toString()

        return Usuario(nome = nome, senha = senha, username = username)
    }


}