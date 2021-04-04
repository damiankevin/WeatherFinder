package id.com.android.weatherfinder.model.weather

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class Main(
    @SerializedName("temp")    var temp: Double?,
    @SerializedName("feels_like")    var feels_like: Double?,
    @SerializedName("temp_min")    var temp_min: Double?,
    @SerializedName("temp_max")    var temp_max: Double?,
    @SerializedName("pressure")    var pressure: Int?,
    @SerializedName("sea_level")    var sea_level: Int?,
    @SerializedName("grnd_level")    var grnd_level: Int?,
    @SerializedName("humidity")    var humidity: Int?,
    @SerializedName("temp_kf")    var temp_kf: Int?
) : Parcelable