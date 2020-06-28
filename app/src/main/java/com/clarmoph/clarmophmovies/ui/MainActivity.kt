package com.clarmoph.clarmophmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.clarmoph.clarmophmovies.R
import com.clarmoph.clarmophmovies.model.ui.AnimeForYou
import com.clarmoph.clarmophmovies.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.qualifiedName
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getAnimeForYou().observe(this, Observer<AnimeForYou> { animeForYou ->

            animeForYou.allAnimeTitles.forEach {
                Log.d(TAG, "Anime title: ${it.attributes.canonicalTitle}")
            }
            animeForYou.popularAnimeTitles.forEach {
                Log.d(TAG, "Popular anime title: ${it.attributes.canonicalTitle}")
            }
            animeForYou.topRomanceTitles.forEach {
                Log.d(TAG, "Top romance anime title: ${it.attributes.canonicalTitle}")
            }
        })

    }
}
