package com.nbs.tmdbclient

import android.app.Application
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TMDBClientApp : Application()