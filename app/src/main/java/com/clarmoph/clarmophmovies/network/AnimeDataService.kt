package com.clarmoph.clarmophmovies.network

import com.clarmoph.clarmophmovies.model.AnimeComplete
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class AnimeDataService() {
    interface AnimeDataService {
        @GET("episodes")
        fun getAllEpisodes(): Call<AnimeComplete>
    }

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/edge/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AnimeDataService::class.java)
    }
}


