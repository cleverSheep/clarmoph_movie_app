package com.clarmoph.clarmophmovies.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clarmoph.clarmophmovies.model.ui.AnimeForYou
import com.clarmoph.clarmophmovies.repository.MainRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel @ViewModelInject constructor(private val mainRepo: MainRepo) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val animeForYou = MutableLiveData<AnimeForYou>()
    private val TAG = MainActivityViewModel::class.qualifiedName

    init {
        fetchAnimeForYou()
    }

    private fun fetchAnimeForYou() {
        disposable.add(
            mainRepo.getAnimeForYou().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AnimeForYou>() {
                    override fun onSuccess(t: AnimeForYou) {
                        animeForYou.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Error loading anime titles")
                    }

                })
        )
    }

    fun getAnimeForYou(): LiveData<AnimeForYou> = animeForYou

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}