package id.com.android.weatherfinder.feature.viewlayer

import id.com.android.weatherfinder.model.weather.ModelWeatherList


interface ViewWeather : ViewBase {
    fun showDataWeather(
        arrayDay1: ArrayList<ModelWeatherList>,
        arrayDay2: ArrayList<ModelWeatherList>,
        arrayDay3: ArrayList<ModelWeatherList>
    )
    fun showFailedLoad()
}