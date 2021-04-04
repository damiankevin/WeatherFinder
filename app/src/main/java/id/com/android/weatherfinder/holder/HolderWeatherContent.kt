package id.com.android.weatherfinder.holder

import android.content.Context
import android.view.View
import id.com.android.weatherfinder.R
import id.com.android.weatherfinder.feature.InterfaceContentCollection
import id.com.android.weatherfinder.model.weather.ModelWeatherList
import id.com.android.weatherfinder.tools.UtilConstant
import id.com.android.weatherfinder.tools.UtilImage
import kotlinx.android.synthetic.main.holder_list_location.view.*
import kotlinx.android.synthetic.main.holder_weather_content.view.*
import java.text.SimpleDateFormat
import java.util.*

class HolderWeatherContent(itemView : View?, context : Context?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView!!) {

    companion object {
        const val LAYOUT_RESOURCE: Int  = R.layout.holder_weather_content
    }

    var context = context


    fun bindingContent (content: ModelWeatherList?) {

        content?.dt_txt?.let {
            itemView.view_holder_weather_content_time?.text = convertTime(it.trim())
        }


        content?.main?.temp?.let {
            itemView.view_holder_weather_content_temperature?.text = convertKelvinToCelcius(it)
        }

        content?.main?.humidity?.let {
            itemView.view_holder_weather_content_humidity?.text = "Humidity = "+it.toString()
        }
        content?.wind?.let {
            itemView.view_holder_weather_content_wind?.text = it.speed.toString()+"km/h  | "+it.deg.toString()+"°"
        }
        UtilImage.loadImageWithoutPlaceholder(itemView.view_holder_weather_content_logo,  UtilConstant.URL_DEFAULT+UtilConstant.PATH_IMAGE+content?.weather?.get(0)?.icon+".png", context)

    }

    private fun convertTime(day: String):String {
        var date = day
        var spf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val newDate: Date = spf.parse(date)
        spf = SimpleDateFormat("hh:mm")
        date = spf.format(newDate)
        return  date
    }

    private fun convertKelvinToCelcius(temp: Double?): String? {
        var celcius= (temp!! -273.15).toInt()
        return "$celcius°C"
    }




}