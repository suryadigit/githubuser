package com.example.projectgithub.favorite


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectgithub.data.local.DbModule
import com.example.projectgithub.data.model.ResponseUserGithub

class FavoriteViewModel(private val dbModule: DbModule) : ViewModel() {



    fun getUserFavorite() = (dbModule.userDao.loadAll())

    class Factory(private val db: DbModule) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
    }
}