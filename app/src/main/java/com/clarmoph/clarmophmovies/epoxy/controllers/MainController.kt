package com.clarmoph.clarmophmovies.epoxy.controllers

import com.airbnb.epoxy.TypedEpoxyController
import com.clarmoph.clarmophmovies.epoxy.models.CarouselItemViewModel_
import com.clarmoph.clarmophmovies.epoxy.models.HeaderItemViewModel_
import com.clarmoph.clarmophmovies.epoxy.views.CarouselNoSnapModel_
import com.clarmoph.clarmophmovies.model.network.AnimeData
import com.clarmoph.clarmophmovies.model.ui.AnimeForYou
import java.util.*

class MainController : TypedEpoxyController<AnimeForYou>() {
    override fun buildModels(data: AnimeForYou) {
        createDataCarousel(data.allAnimeTitles)
        createDataCarousel(data.popularAnimeTitles)
        createDataCarousel(data.topRomanceTitles)
    }

    private fun createDataCarousel(itemPair: Pair<String, List<AnimeData>>) {
        val models = ArrayList<CarouselItemViewModel_>()
        for (item in itemPair.second) {
            models.add(
                CarouselItemViewModel_()
                    .id("item:" + item.id)
                    .title(item.attributes.canonicalTitle)
            )
        }
        HeaderItemViewModel_()
            .id("header")
            .title(itemPair.first)
            .addTo(this)
        CarouselNoSnapModel_()
            .id("carousel-1")
            .models(models)
            .addTo(this)
    }
}