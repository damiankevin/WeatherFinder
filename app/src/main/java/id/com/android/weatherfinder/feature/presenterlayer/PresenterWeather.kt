package id.com.android.weatherfinder.feature.presenterlayer

import android.util.Log
import id.com.android.weatherfinder.controller.ControllerEndpoint
import id.com.android.weatherfinder.feature.viewlayer.ViewWeather
import id.com.android.weatherfinder.model.weather.ModelWeather
import id.com.android.weatherfinder.model.weather.ModelWeatherList
import id.com.android.weatherfinder.repository.RepositoryContent
import id.com.android.weatherfinder.tools.UtilConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class PresenterWeather : PresenterBase<ViewWeather> {

    private var repositoryContent: RepositoryContent?
    private var serviceApi : ControllerEndpoint

    @Inject
    constructor(repositoryContent: RepositoryContent, controllerEndpoint: ControllerEndpoint) {
        this.repositoryContent = repositoryContent
        this.serviceApi = controllerEndpoint
    }


    fun weatherLoaded(id: String) {
        GlobalScope.launch(Dispatchers.IO){
            val response = serviceApi.getWeather5Days(UtilConstant.API_KEY, id)
            if(response.isSuccessful){
                separateDate(response.body())
            }else{
                viewLayer?.showFailedLoad()
            }
        }

    }

    private fun separateDate(body: ModelWeather?) {
        var tempArray : ArrayList<ModelWeatherList> = ArrayList()
        var arrayDay1 : ArrayList<ModelWeatherList> = ArrayList()
        var arrayDay2 : ArrayList<ModelWeatherList> = ArrayList()
        var arrayDay3 : ArrayList<ModelWeatherList> = ArrayList()
        tempArray.addAll(body?.list!!)

        getDay1(tempArray,arrayDay1,body)
        getDay2(tempArray,arrayDay2,body)
        getDay3(tempArray,arrayDay3,body)

        viewLayer?.showDataWeather(arrayDay1,arrayDay2,arrayDay3)

    }

    private fun getDay3(tempArray: ArrayList<ModelWeatherList>, arrayDay3: ArrayList<ModelWeatherList>, body: ModelWeather) {
        var tempArrayDay3 : ArrayList<ModelWeatherList> = ArrayList()
        tempArrayDay3.addAll(tempArray)
        var day3 = convertDay( tempArray?.get(0)?.dt_txt.toString())

        for(i in 0 until tempArrayDay3.size-1){
            if(tempArrayDay3[i].dt_txt?.contains(day3)!!){
                arrayDay3.add(tempArrayDay3[i])
                tempArray.remove(tempArrayDay3[i])
            }else{
                break
            }
        }
    }

    private fun getDay2(tempArray: ArrayList<ModelWeatherList>, arrayDay2: ArrayList<ModelWeatherList>, body: ModelWeather) {
        var tempArrayDay2 : ArrayList<ModelWeatherList> = ArrayList()
        tempArrayDay2.addAll(tempArray)
        var day2 = convertDay( tempArray?.get(0)?.dt_txt.toString())

        for(i in 0 until tempArrayDay2.size-1){
            if(tempArrayDay2[i].dt_txt?.contains(day2)!!){
                arrayDay2.add(tempArrayDay2[i])
                tempArray.remove(tempArrayDay2[i])
            }else{
                break
            }
        }
    }

    private fun getDay1(
        tempArray: ArrayList<ModelWeatherList>,
        arrayDay1: ArrayList<ModelWeatherList>,
        body: ModelWeather
    ) {
        var day1 = convertDay( tempArray[0]?.dt_txt.toString())
        for(i in 0 until body.list!!.size-1){
            if(body.list!![i].dt_txt?.contains(day1)!!){
                arrayDay1.add(body.list!![i])
                tempArray.remove(body.list!![i])
            }else{
                break
            }
        }
    }

    private fun convertDay(day: String):String {
        var date = day
        var spf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val newDate: Date = spf.parse(date)
        spf = SimpleDateFormat("yyyy-MM-dd")
        date = spf.format(newDate)
        return  date
    }


}