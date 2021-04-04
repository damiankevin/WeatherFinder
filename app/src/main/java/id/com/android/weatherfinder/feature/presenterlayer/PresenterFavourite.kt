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

class PresenterFavourite : PresenterBase<ViewSearch> {


    private var collectionPersistance: ArrayList<ModelDB> = ArrayList()
    private var repositoryContent: RepositoryContent?

    @Inject
    constructor(repositoryContent: RepositoryContent) {
        this.repositoryContent = repositoryContent
    }

    var arrayLocation : ArrayList<ModelDB> = ArrayList()
    fun viewCreated(context: Context?) {

        doAsync {
            val collectionID = repositoryContent?.contentDao()?.getContent()
            uiThread {
                collectionPersistance.clear()
                collectionPersistance = collectionID as ArrayList<ModelDB>
                arrayLocation.addAll(collectionPersistance)
                if(arrayLocation.size==0){
                    viewLayer?.showEmpty()
                }else{
                    viewLayer?.showSuccessLoadData(arrayLocation)
                }
            }
        }
    }


    fun contentFavourite(content: ModelDB) {
        if (content.hasFavourite == true) {
            doAsync { repositoryContent?.contentDao()?.deleteFavourite(content.id) }
        } else {
            doAsync {
                val collectionId = repositoryContent?.contentDao()?.allContentIdFavourite(true)
                uiThread {
                    if(collectionId!!.contains(content.id)){
                    }else{
                        doAsync { repositoryContent?.contentDao()?.addContent(RepositoryContent.ContentConverterFavourite(content).toContent()!!) }
                    }
                }
            }
        }
    }


}