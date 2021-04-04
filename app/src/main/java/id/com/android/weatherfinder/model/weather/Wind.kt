package id.com.android.weatherfinder.model.weather

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class Wind(
    @SerializedName("speed")    var speed: Double?,
    @SerializedName("deg")    var deg: Int?
) : Parcelable