package com.example.foodapp.dependencyInjection

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication:Application() {
}