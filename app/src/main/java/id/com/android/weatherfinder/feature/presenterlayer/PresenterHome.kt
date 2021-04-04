package id.com.android.weatherfinder.feature.presenterlayer

import android.content.Context
import id.com.android.weatherfinder.controller.ControllerEndpoint
import id.com.android.weatherfinder.feature.viewlayer.ViewHome
import id.com.android.weatherfinder.model.ModelDB
import id.com.android.weatherfinder.repository.RepositoryContent
import id.com.android.weatherfinder.tools.UtilConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PresenterHome : PresenterBase<ViewHome> {

    private var serviceApi : ControllerEndpoint
    private var repositoryContent: RepositoryContent?

    @Inject
    constructor(repositoryContent: RepositoryContent,controllerEndpoint: ControllerEndpoint) {
        this.repositoryContent = repositoryContent
        this.serviceApi = controllerEndpoint
    }

    var arrayLocation : ArrayList<ModelDB> = ArrayList()
    fun viewCreated(context: Context?) {

        GlobalScope.launch(Dispatchers.IO){
            val response = serviceApi.getWeatherDetail(UtilConstant.API_KEY, "1642911")
            if(response.isSuccessful){
                viewLayer?.showDataWeather(response.body())
            }else{
                viewLayer?.showFailedLoad()
            }
        }

    }



}