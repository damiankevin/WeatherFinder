package id.com.android.weatherfinder.holder

import android.content.Context
import android.view.View
import id.com.android.weatherfinder.R
import id.com.android.weatherfinder.adapter.AdapterWeaterContent
import id.com.android.weatherfinder.adapter.AdapterWeatherDetail
import id.com.android.weatherfinder.model.weather.ModelWeatherList
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.holder_list_location.view.*
import kotlinx.android.synthetic.main.holder_weather_day.view.*
import java.text.SimpleDateFormat
import java.util.*

class HolderWeatherDay(itemView : View?, context : Context?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView!!) {

    var context = context
    private lateinit var adapterWeatherContent: AdapterWeaterContent

    companion object {
        const val LAYOUT_RESOURCE: Int  = R.layout.holder_weather_day
    }



    fun bindingContent (content: ArrayList<ModelWeatherList>?) {
        content?.get(0)?.dt_txt.let {
            itemView.view_holder_weather_date.text = convertDay(it.toString())
        }
        initializeCollection()
        content?.let { adapterWeatherContent.collectionWeather?.addAll(it) }
    }

    private fun initializeCollection() {
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
        adapterWeatherContent = AdapterWeaterContent(context)
        itemView?.view_holder_recyclerview_weather?.layoutManager = layoutManager
        itemView?.view_holder_recyclerview_weather?.adapter = adapterWeatherContent


    }

    private fun convertDay(day: String):String {
        var date = day
        var spf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val newDate: Date = spf.parse(date)
        spf = SimpleDateFormat("yyyy MMM dd")
        date = spf.format(newDate)
        return  date
    }


}