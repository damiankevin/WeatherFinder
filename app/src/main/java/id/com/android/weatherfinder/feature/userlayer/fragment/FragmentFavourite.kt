package id.com.android.weatherfinder.feature.userlayer.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.com.android.weatherfinder.R
import id.com.android.weatherfinder.adapter.AdapterListLocation
import id.com.android.weatherfinder.feature.ActivityBase
import id.com.android.weatherfinder.feature.InterfaceContentCollection
import id.com.android.weatherfinder.feature.presenterlayer.PresenterFavourite
import id.com.android.weatherfinder.feature.userlayer.activity.ui.WeatherActivity
import id.com.android.weatherfinder.feature.viewlayer.ViewSearch
import id.com.android.weatherfinder.model.ModelDB
import id.com.android.weatherfinder.tools.UtilConstant
import kotlinx.android.synthetic.main.fragment_favourite.*
import javax.inject.Inject

class FragmentFavourite : FragmentBase(), ViewSearch, InterfaceContentCollection {

    companion object {
        var LAYOUT_RESOURCE : Int           = R.layout.fragment_favourite
    }

    @Inject
    lateinit var presenterFavourite            : PresenterFavourite
    private lateinit var adapterListLocation: AdapterListLocation

    private fun initializePresenter() {
        (activity as ActivityBase).componentActivity?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(LAYOUT_RESOURCE, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenterFavourite.attachView(this)
        initializeCollection()
        presenterFavourite.viewCreated(context)
    }


    override fun onDestroy() {
        presenterFavourite.detachView()
        super.onDestroy()
    }

    override fun showSuccessLoadData(results: ArrayList<ModelDB>) {
        adapterListLocation.noContent = false
        adapterListLocation.collectionLocation?.clear()
        adapterListLocation.collectionLocation?.addAll(results)
        adapterListLocation.notifyDataSetChanged()
    }

    override fun showEmpty() {
        adapterListLocation.noContent = true
        adapterListLocation.collectionLocation?.clear()
        adapterListLocation.notifyDataSetChanged()
    }

    override fun onContentFavourite(content: ModelDB, position: Int) {
        presenterFavourite.contentFavourite(content)
        content.hasFavourite = true
        adapterListLocation.notifyItemChanged(position)
        Toast.makeText(context, R.string.SIGN_SAVED_CONTENT, Toast.LENGTH_SHORT).show()
    }

    override fun onContentUnFavourite(content: ModelDB, position: Int) {
        presenterFavourite.contentFavourite(content)
        content.hasFavourite = false
        adapterListLocation.notifyItemChanged(position)
        Toast.makeText(context, R.string.SIGN_UNSAVED_CONTENT, Toast.LENGTH_SHORT).show()
    }

    override fun onContentSelected(content: ModelDB) {
        val intent          = Intent(activity, WeatherActivity::class.java)
        intent.putExtra(UtilConstant.WEATHER_ID,content.id.toString())
        intent.putExtra(UtilConstant.WEATHER_NAME,content.name)
        startActivity(intent)
    }

    private fun initializeCollection() {
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        adapterListLocation = AdapterListLocation(activity)
        adapterListLocation.interfaceContentCollection = this
        view_recyclerview_location_favourite?.layoutManager = layoutManager
        view_recyclerview_location_favourite?.adapter = adapterListLocation
    }

}