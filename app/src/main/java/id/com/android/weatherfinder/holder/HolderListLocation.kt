package id.com.android.weatherfinder.holder

import android.content.Context
import android.view.View
import id.com.android.weatherfinder.R
import id.com.android.weatherfinder.feature.InterfaceContentCollection
import id.com.android.weatherfinder.model.ModelDB
import kotlinx.android.synthetic.main.holder_list_location.view.*

class HolderListLocation(itemView : View?, context : Context?, interfaceContentCollection: InterfaceContentCollection?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView!!) {

    companion object {
        const val LAYOUT_RESOURCE: Int  = R.layout.holder_list_location
    }

    private var interfaceContentCollection              = interfaceContentCollection


    fun bindingContent (content : ModelDB?) {

        content?.name?.let {
            itemView?.view_holder_location_name?.text = it.trim()
        }

        itemView?.setOnClickListener {
            content?.let { interfaceContentCollection?.onContentSelected(it) }
        }

        content?.hasFavourite?.let {
            itemView?.view_holder_love?.setImageResource(if (it) R.drawable.ic_heart_red else R.drawable.ic_heart)
        }

        itemView?.view_holder_love?.setOnClickListener {
            content?.let {
                if (it.hasFavourite == true) { interfaceContentCollection?.onContentUnFavourite(content, adapterPosition) }
                else { interfaceContentCollection?.onContentFavourite(it, adapterPosition) }
            }
        }

    }



}