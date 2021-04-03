package id.com.android.weatherfinder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import id.com.android.weatherfinder.feature.InterfaceContentCollection
import id.com.android.weatherfinder.feature.userlayer.fragment.FragmentSearch
import id.com.android.weatherfinder.holder.HolderListLocation
import id.com.android.weatherfinder.holder.HolderNoLocation
import id.com.android.weatherfinder.model.ModelDB

class AdapterListLocation(context: Context?) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_NO_LOCATION: Int = 0
        const val ITEM_TYPE_CONTENT: Int = 1
    }

    var collectionLocation: ArrayList<ModelDB>? = ArrayList()
    private var itemContext: Context? = context
    var context: Context? = context
    var interfaceContentCollection: InterfaceContentCollection? = null

    override fun getItemViewType(position: Int): Int {
        return if (itemCount == 0) {
            ITEM_TYPE_NO_LOCATION
        }
        else{
            ITEM_TYPE_CONTENT
        }

    }

    override fun getItemCount(): Int {
        return collectionLocation?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(itemContext)
        when (viewType) {
            ITEM_TYPE_CONTENT -> {
                val itemList = layoutInflater.inflate(HolderListLocation.LAYOUT_RESOURCE, parent, false)
                return HolderListLocation(itemList, itemContext,interfaceContentCollection)

            }
            else -> {
                val itemViewFooterStatus = layoutInflater.inflate(HolderNoLocation.LAYOUT_RESOURCE, parent, false)
                return HolderNoLocation(itemViewFooterStatus)
            }
        }
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        when (holder?.itemViewType) {
            ITEM_TYPE_CONTENT -> {
                (holder as? HolderListLocation)?.bindingContent(collectionLocation?.get(position))
            }
            ITEM_TYPE_NO_LOCATION -> {
                (holder as? HolderNoLocation)?.bindingContent()
            }
        }
    }


}