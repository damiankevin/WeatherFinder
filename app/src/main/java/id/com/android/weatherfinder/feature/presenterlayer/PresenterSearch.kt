package id.com.android.weatherfinder.feature.presenterlayer

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.com.android.weatherfinder.feature.viewlayer.ViewSearch
import id.com.android.weatherfinder.model.ModelDB
import id.com.android.weatherfinder.repository.RepositoryContent
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import javax.inject.Inject


class PresenterSearch : PresenterBase<ViewSearch> {


    private var collectionPersistance: ArrayList<Int> = ArrayList()
    private var repositoryContent: RepositoryContent?

    @Inject
    constructor(repositoryContent: RepositoryContent) {
        this.repositoryContent = repositoryContent
    }

    var arrayLocation : ArrayList<ModelDB> = ArrayList()
    fun viewCreated(context: Context?) {

        doAsync {
            val collectionID = repositoryContent?.contentDao()?.allContentIdFavourite(true)
            uiThread {
                collectionPersistance.clear()
                collectionPersistance = collectionID as ArrayList<Int>

                var jsonString: String
                jsonString = try {
                    context?.assets?.open("city_list_indo.json")?.bufferedReader().use { it!!.readText() }
                } catch (ioException: IOException) {
                    ioException.printStackTrace()
                    ""
                }

                val gson = Gson()
                val results: ArrayList<ModelDB> = gson.fromJson(
                    jsonString,
                    object : TypeToken<ArrayList<ModelDB?>?>() {}.type
                )
                arrayLocation.addAll(results)
                viewLayer?.showSuccessLoadData(setFavouriteContent(arrayLocation))
            }
        }


    }

    fun filterLocation(keyword: String){
        keyword.toLowerCase()

        var tempArray : ArrayList<ModelDB> = ArrayList()
        for(i in 0 until arrayLocation.size){
            if(arrayLocation[i].name!!.toLowerCase().contains(keyword)){
                tempArray.add(arrayLocation[i])
            }
        }

        doAsync {
            val collectionID = repositoryContent?.contentDao()?.allContentIdFavourite(true)
            uiThread {
                collectionPersistance.clear()
                collectionPersistance = collectionID as ArrayList<Int>
                viewLayer?.showSuccessLoadData(setFavouriteContent(tempArray))
            }
        }

    }

    private fun setFavouriteContent(collectionContent: ArrayList<ModelDB>): ArrayList<ModelDB> {
        collectionContent.filterIsInstance<ModelDB>().forEach { it.hasFavourite = collectionPersistance.contains(it.id) }
        return collectionContent
    }

    fun contentFavourite(content: ModelDB) {
        if (content.hasFavourite == true) {
            doAsync { repositoryContent?.contentDao()?.deleteFavourite(content.id) }
        } else {
            doAsync {
                val collectionId = repositoryContent?.contentDao()?.allContentIdFavourite(true)
                uiThread {
                    collectionPersistance.clear()
                    collectionPersistance = collectionId as ArrayList<Int>
                    if(collectionPersistance.contains(content.id)){

                    }else{
                        doAsync { repositoryContent?.contentDao()?.addContent(RepositoryContent.ContentConverterFavourite(content).toContent()!!) }

                    }

                }
            }
        }
    }


}