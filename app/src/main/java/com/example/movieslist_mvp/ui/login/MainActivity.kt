package com.example.movieslist_mvp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieslist_mvp.databinding.ActivityMainBinding
import com.example.movieslist_mvp.ui.movieslist.MoviesListActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        presenter = MainPresenter(this)
        login()
    }

    private fun login() {
        binding.loginScreemLoginBtn.setOnClickListener {
            val userName = binding.loginScreemUserNameEt.text.toString()
            val password = binding.loginScreemPasswordEt.text.toString()
            presenter.login(userName = userName, password = password)
        }
    }

    override fun showErroToast(erro: String) {
        Toast.makeText(applicationContext, erro, Toast.LENGTH_LONG).show()
    }

    override fun goToMoviesScreen() {
        val intent = Intent(this@MainActivity, MoviesListActivity::class.java)
        startActivity(intent)
    }
}