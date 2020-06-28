package com.clarmoph.clarmophmovies.epoxy.controllers;

import com.airbnb.epoxy.TypedEpoxyController;
import com.clarmoph.clarmophmovies.epoxy.models.CarouselItemViewModel_;
import com.clarmoph.clarmophmovies.epoxy.views.CarouselNoSnapModel_;
import com.clarmoph.clarmophmovies.model.network.AnimeData;
import com.clarmoph.clarmophmovies.model.ui.AnimeForYou;

import java.util.ArrayList;

public class MainController extends TypedEpoxyController<AnimeForYou> {
    @Override
    protected void buildModels(AnimeForYou data) {
        ArrayList<CarouselItemViewModel_> models = new ArrayList<>();
        for (AnimeData item : data.getAllAnimeTitles()) {
            models.add(new CarouselItemViewModel_()
                    .id("item:" + item.getId())
                    .title(item.getAttributes().getCanonicalTitle()));
        }
        new CarouselNoSnapModel_()
                .id("carousel")
                .models(models)
                .addTo(this);
    }
}
