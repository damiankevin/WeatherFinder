package id.com.android.weatherfinder.controller

import id.com.android.weatherfinder.model.weather.ModelWeather
import id.com.android.weatherfinder.model.weather.ModelWeatherList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ControllerEndpoint {

    @GET("data/2.5/forecast")
    suspend fun getWeather5Days(
        @Query("appid") appid: String?,
        @Query("id") id: String?
    ): Response<ModelWeather>

    @GET("data/2.5/weather")
    suspend fun getWeatherDetail(
        @Query("appid") appid: String?,
        @Query("id") id: String?
    ): Response<ModelWeatherList>

}