package id.com.android.weatherfinder.feature.userlayer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.com.android.weatherfinder.R
import id.com.android.weatherfinder.feature.ActivityBase
import id.com.android.weatherfinder.feature.presenterlayer.PresenterSearch
import id.com.android.weatherfinder.feature.viewlayer.ViewSearch
import javax.inject.Inject

class FragmentSearch : FragmentBase(), ViewSearch {

    companion object {
        var LAYOUT_RESOURCE : Int           = R.layout.fragment_search
        var CLASS_TAG : String              = "Fragment Home Market"
    }

    @Inject
    lateinit var presenterSearch            : PresenterSearch

    private fun initializePresenter() {
        (activity as ActivityBase).componentActivity?.inject(this)
        presenterSearch.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(LAYOUT_RESOURCE, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenterSearch.viewCreated()
    }

    override fun onDestroy() {
        presenterSearch.detachView()
        super.onDestroy()
    }

    override fun showSuccessLoadData() {

    }

    override fun showEmptySearchHistory() {

    }

}