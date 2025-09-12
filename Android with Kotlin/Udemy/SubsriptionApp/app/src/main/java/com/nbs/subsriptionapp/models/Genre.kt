package com.nbs.subsriptionapp.models

import com.nbs.subsriptionapp.R

data class Genre(val title: String, val iconId: Int)

val allGenres = listOf(
    Genre("Horror", R.drawable.icon_horror),
    Genre("Comedy", R.drawable.icon_comedy),
    Genre("Romantic", R.drawable.icon_romantic),
    Genre("Action", R.drawable.icon_action),
    Genre("Drama", R.drawable.icon_drama),
    Genre("SciFi", R.drawable.icon_scifi),
    Genre("Western", R.drawable.icon_western),
    Genre("Thriller", R.drawable.icon_thriller),
    Genre("Animation", R.drawable.icon_animation),
    Genre("Fantasy", R.drawable.icon_fantasy),
    Genre("Documentary", R.drawable.icon_documentary),
    Genre("Musical", R.drawable.icon_musical),
    Genre("Mystery", R.drawable.icon_mystery),
    Genre("Short Films", R.drawable.icon_short)
)

val randomGenres = allGenres.shuffled().take(allGenres.size / 2)
