package id.com.android.weatherfinder.feature.presenterlayer

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.com.android.weatherfinder.feature.viewlayer.ViewSearch
import id.com.android.weatherfinder.model.ModelDB
import id.com.android.weatherfinder.repository.RepositoryContent
import id.com.android.weatherfinder.tools.UtilListLocation
import javax.inject.Inject


class PresenterSearch : PresenterBase<ViewSearch> {


    private var collectionPersistance: ArrayList<String> = ArrayList()
    private var repositoryContent: RepositoryContent?

    @Inject
    constructor(repositoryContent: RepositoryContent) {
        this.repositoryContent = repositoryContent
    }

    fun viewCreated(){
        val gson = Gson()
        val results: ArrayList<ModelDB> = gson.fromJson(
            UtilListLocation.LOCATION,
            object : TypeToken<ArrayList<ModelDB?>?>() {}.type
        )
    }


}