package id.com.android.weatherfinder.model.weather

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class ModelWeatherArrayForecast(
    var weather: ArrayList<ModelWeatherList>?
)