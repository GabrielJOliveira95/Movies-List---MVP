package com.example.movieslist_mvp.ui.login

interface MainContract {
    interface View{
        fun showErroToast(erro: String)
        fun goToMoviesScreen()
    }

    interface Presenter{
        fun login(userName: String, password: String)
    }
}