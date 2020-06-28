package com.clarmoph.clarmophmovies.repository

import android.util.Log
import com.clarmoph.clarmophmovies.model.AnimeComplete
import com.clarmoph.clarmophmovies.model.AnimeData
import com.clarmoph.clarmophmovies.model.ui.AnimeForYou
import com.clarmoph.clarmophmovies.network.AnimeDataService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepo @Inject constructor() {

    private val TAG = MainRepo::class.qualifiedName

    private fun getAllAnimeTitles(): Single<AnimeComplete> {
        return AnimeDataService.service.getAllAnimeTitles()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getPopularAnimeTitles(): Single<AnimeComplete> {
        return AnimeDataService.service.getAllPopularAnimeTitles()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getTopRomanceTitles(): Single<AnimeComplete> {
        return AnimeDataService.service.getTopRomanceTitles()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAnimeForYou(): Single<AnimeForYou> {
        return Single.zip(
            getAllAnimeTitles(),
            getPopularAnimeTitles(),
            getTopRomanceTitles(),
            Function3 { animeTitles, popularAnime, topRomanceTitles ->
                buildAnimeForYou(
                    AnimeForYou(
                        animeTitles.data,
                        popularAnime.data,
                        topRomanceTitles.data
                    )
                )
            })
    }

    private fun buildAnimeForYou(animeForYou: AnimeForYou): AnimeForYou =
        AnimeForYou(
            animeForYou.allAnimeTitles,
            animeForYou.popularAnimeTitles,
            animeForYou.topRomanceTitles
        )
}