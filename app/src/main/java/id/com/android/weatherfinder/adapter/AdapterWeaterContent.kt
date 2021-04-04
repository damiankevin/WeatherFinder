package id.com.android.weatherfinder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import id.com.android.weatherfinder.holder.HolderWeatherContent
import id.com.android.weatherfinder.model.weather.ModelWeatherList

class AdapterWeaterContent(context: Context?) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_CONTENT: Int = 1
    }

    var collectionWeather: ArrayList<ModelWeatherList>? = ArrayList()
    private var itemContext: Context? = context
    var context: Context? = context

    override fun getItemViewType(position: Int): Int {
        return ITEM_TYPE_CONTENT
    }

    override fun getItemCount(): Int {
        return collectionWeather?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(itemContext)
        val itemList = layoutInflater.inflate(HolderWeatherContent.LAYOUT_RESOURCE, parent, false)
        return HolderWeatherContent(itemList, itemContext)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder as? HolderWeatherContent)?.bindingContent(collectionWeather?.get(position))
    }


}