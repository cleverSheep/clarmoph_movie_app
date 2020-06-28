package com.clarmoph.clarmophmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.clarmoph.clarmophmovies.model.AnimeComplete
import com.clarmoph.clarmophmovies.network.AnimeDataService.Companion.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val call = service.getAllEpisodes()
        call.enqueue(object : Callback<AnimeComplete> {
            override fun onFailure(call: Call<AnimeComplete>, t: Throwable) {
                Log.d(TAG, "${t.message}")
            }

            override fun onResponse(call: Call<AnimeComplete>, response: Response<AnimeComplete>) {
                if (response.isSuccessful) {
                    Log.d(TAG, response.body()!!.data[2].attributes.canonicalTitle.toString())
                }
            }

        })
    }
}
