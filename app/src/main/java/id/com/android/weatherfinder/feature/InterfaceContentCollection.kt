package id.com.android.weatherfinder.feature

import id.com.android.weatherfinder.model.ModelDB


interface InterfaceContentCollection {
    fun onContentFavourite(content: ModelDB, position: Int)
    fun onContentUnFavourite(content: ModelDB, position: Int)
    fun onContentSelected(content: ModelDB)
}