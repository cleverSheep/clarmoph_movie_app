package com.clarmoph.clarmophmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.airbnb.epoxy.EpoxyRecyclerView
import com.clarmoph.clarmophmovies.epoxy.helpers.carouselNoSnapBuilder
import com.clarmoph.clarmophmovies.R
import com.clarmoph.clarmophmovies.epoxy.controllers.MainController
import com.clarmoph.clarmophmovies.epoxy.models.CarouselItemViewModel_
import com.clarmoph.clarmophmovies.epoxy.models.carouselItemView
import com.clarmoph.clarmophmovies.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.qualifiedName
    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var recyclerView: EpoxyRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        val controller = MainController()
        recyclerView.setController(controller)

        viewModel.getAnimeForYou().observe(this, Observer { animeForYou ->
            controller.setData(animeForYou)
        })

    }
}
