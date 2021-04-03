package id.com.android.weatherfinder.holder

import android.content.Context
import android.view.View
import id.com.android.weatherfinder.feature.`interface`.InterfaceContentCollection
import id.com.android.weatherfinder.model.ModelDB
import kotlinx.android.synthetic.main.holder_list_location.view.*

class HolderNoLocation(itemView : View?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView!!) {

    companion object {
        const val LAYOUT_RESOURCE: Int  = id.com.android.weatherfinder.R.layout.holder_no_location
        const val CLASS_TAG : String    = "Holder List Location"
    }

    fun bindingContent () {



    }



}