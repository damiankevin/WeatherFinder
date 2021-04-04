package id.com.android.weatherfinder.feature.viewlayer

import id.com.android.weatherfinder.model.ModelDB

interface ViewSearch : ViewBase {
    fun showSuccessLoadData(results: ArrayList<ModelDB>)
    fun showEmpty()
}