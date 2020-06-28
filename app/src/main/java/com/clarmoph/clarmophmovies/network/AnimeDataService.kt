package com.clarmoph.clarmophmovies.network

import com.clarmoph.clarmophmovies.model.network.AnimeComplete
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class AnimeDataService {
    interface AnimeDataService {
        @GET("anime")
        fun getAllAnimeTitles(): Single<AnimeComplete>

        @GET("anime?sort=popularityRank")
        fun getAllPopularAnimeTitles(): Single<AnimeComplete>

        @GET("anime?filter[categories]=romance&sort=popularityRank")
        fun getTopRomanceTitles(): Single<AnimeComplete>
    }

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/edge/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val service: AnimeDataService = retrofit.create(AnimeDataService::class.java)
    }
}



