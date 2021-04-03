package id.com.android.weatherfinder.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "location")
data class ModelDB(
    @PrimaryKey @SerializedName("id") var id: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("country") var country: String?,
    @SerializedName("hasFavourite") var hasFavourite: Boolean?) : Parcelable {

    constructor(source: Parcel) : this(
        source.readValue(Int::class.java.classLoader) as Int,
        source.readString(),
        source.readString(),
        source.readValue(Boolean::class.java.classLoader) as Boolean
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(id)
        writeString(name)
        writeString(country)
        writeValue(id)
        writeValue(hasFavourite)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModelDB> = object : Parcelable.Creator<ModelDB> {
            override fun createFromParcel(source: Parcel): ModelDB = ModelDB(source)
            override fun newArray(size: Int): Array<ModelDB?> = arrayOfNulls(size)
        }
    }
}