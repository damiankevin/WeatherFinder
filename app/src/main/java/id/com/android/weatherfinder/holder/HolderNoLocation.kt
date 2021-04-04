package id.com.android.weatherfinder.holder

import android.view.View
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.holder_no_location.*
import kotlinx.android.synthetic.main.holder_no_location.view.*

class HolderNoLocation(itemView : View?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView!!) {

    companion object {
        const val LAYOUT_RESOURCE: Int  = id.com.android.weatherfinder.R.layout.holder_no_location
        const val CLASS_TAG : String    = "Holder List Location"
    }

    fun bindingContent () {
        itemView?.        view_holder_no_location.visibility = View.VISIBLE
    }

}