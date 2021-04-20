package com.example.movieslist_mvp

interface MainContract {
    interface View{
        fun showErroToast(erro: String)
    }

    interface Presenter{
        fun login(userName: String, password: String)
    }
}