package id.com.android.weatherfinder.model.weather

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ModelWeather(
    @SerializedName("cod")    var cod: String?,
    @SerializedName("message") var message: Int?,
    @SerializedName("cnt") var cnt: Int?,
    @SerializedName("list") var list: ArrayList<ModelWeatherList>?
) : Parcelable