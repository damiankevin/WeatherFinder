package id.com.android.weatherfinder.feature.userlayer.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.com.android.weatherfinder.R
import id.com.android.weatherfinder.adapter.AdapterListLocation
import id.com.android.weatherfinder.feature.ActivityBase
import id.com.android.weatherfinder.feature.InterfaceContentCollection
import id.com.android.weatherfinder.feature.presenterlayer.PresenterSearch
import id.com.android.weatherfinder.feature.viewlayer.ViewSearch
import id.com.android.weatherfinder.model.ModelDB
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class FragmentFavourite : FragmentBase(), ViewSearch, InterfaceContentCollection {

    companion object {
        var LAYOUT_RESOURCE : Int           = R.layout.fragment_search
    }

    @Inject
    lateinit var presenterSearch            : PresenterSearch
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
        presenterSearch.attachView(this)
        initializeCollection()
        initializeCollection()
        initializeEditText()
        presenterSearch.viewCreated(context)
    }

    private fun initializeEditText() {
        view_keyword_location.clearFocus()
        view_keyword_location.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                presenterSearch.filterLocation(view_keyword_location.text.toString())
            }
        })
        view_keyword_watchlist_clear.setOnClickListener { view_keyword_location?.text?.clear() }
    }

    override fun onDestroy() {
        presenterSearch.detachView()
        super.onDestroy()
    }

    override fun showSuccessLoadData(results: ArrayList<ModelDB>) {
        adapterListLocation.collectionLocation?.clear()
        adapterListLocation.collectionLocation?.addAll(results)
        adapterListLocation.notifyDataSetChanged()
    }

    override fun showEmptySearchHistory() {

    }

    override fun onContentFavourite(content: ModelDB, position: Int) {
        presenterSearch.contentFavourite(content)
        content.hasFavourite = true
        adapterListLocation.notifyItemChanged(position)
        Toast.makeText(context, R.string.SIGN_SAVED_CONTENT, Toast.LENGTH_SHORT).show()
    }

    override fun onContentUnFavourite(content: ModelDB, position: Int) {
        presenterSearch.contentFavourite(content)
        content.hasFavourite = false
        adapterListLocation.notifyItemChanged(position)
        Toast.makeText(context, R.string.SIGN_UNSAVED_CONTENT, Toast.LENGTH_SHORT).show()
    }

    override fun onContentSelected(content: ModelDB) {

    }

    private fun initializeCollection() {
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        adapterListLocation = AdapterListLocation(activity)
        adapterListLocation.interfaceContentCollection = this
        view_recyclerview_location?.layoutManager = layoutManager
        view_recyclerview_location?.adapter = adapterListLocation

    }

}