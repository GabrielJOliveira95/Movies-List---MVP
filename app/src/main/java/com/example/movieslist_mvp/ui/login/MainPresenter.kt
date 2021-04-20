package com.example.movieslist_mvp

class MainPresenter(private val view: MainContract.View): MainContract.Presenter {
    override fun login(userName: String, password: String) {
        if (userName.isEmpty()){
            view.showErroToast("Preencha o seu nome")
        } else if (password.isEmpty()){
            view.showErroToast("Preencha a sua senha")
        }
    }
}