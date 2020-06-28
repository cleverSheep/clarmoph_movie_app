package com.clarmoph.clarmophmovies.model.ui

import com.clarmoph.clarmophmovies.model.AnimeData

data class AnimeForYou(
    val allAnimeTitles: List<AnimeData>,
    val popularAnimeTitles: List<AnimeData>,
    val topRomanceTitles: List<AnimeData>
)