package id.com.android.weatherfinder.feature.viewlayer

import id.com.android.weatherfinder.model.weather.ModelWeatherList


interface ViewHome : ViewBase {
    fun showDataWeather(
        content: ModelWeatherList?
    )
    fun showFailedLoad()
}