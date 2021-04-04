package id.com.android.weatherfinder.model.weather

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Weather(
    @SerializedName("id")    var id: Int?,
    @SerializedName("main")    var main: String?,
    @SerializedName("description")    var description: String?,
    @SerializedName("icon")    var icon: String?
) : Parcelable