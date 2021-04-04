package id.com.android.weatherfinder.model.weather

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class ModelWeatherList(
    @SerializedName("dt")    var cod: String?,
    @SerializedName("main") var main: Main?,
    @SerializedName("weather") var weather: ArrayList<Weather>?,
    @SerializedName("clouds") var clouds: Clouds?,
    @SerializedName("wind") var wind: Wind?,
    @SerializedName("visibility") var visibility: Int,
    @SerializedName("pop") var pop: Double?,
    @SerializedName("dt_txt") var dt_txt: String?
) : Parcelable