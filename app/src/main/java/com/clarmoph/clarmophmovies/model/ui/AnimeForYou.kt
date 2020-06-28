package com.clarmoph.clarmophmovies.model.ui

import com.clarmoph.clarmophmovies.model.network.AnimeData

data class AnimeForYou(
    val allAnimeTitles: Pair<String, List<AnimeData>>,
    val popularAnimeTitles: Pair<String, List<AnimeData>>,
    val topRomanceTitles: Pair<String, List<AnimeData>>
)