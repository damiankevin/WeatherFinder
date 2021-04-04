package id.com.android.weatherfinder.feature.userlayer.activity.ui

import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.com.android.weatherfinder.R
import id.com.android.weatherfinder.adapter.AdapterWeatherDetail
import id.com.android.weatherfinder.feature.ActivityBase
import id.com.android.weatherfinder.feature.presenterlayer.PresenterWeather
import id.com.android.weatherfinder.feature.viewlayer.ViewWeather
import id.com.android.weatherfinder.model.weather.ModelWeatherList
import id.com.android.weatherfinder.tools.UtilConstant
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.holder_failed_load_data.*
import javax.inject.Inject

class WeatherActivity :  ActivityBase(),ViewWeather, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val LAYOUT_RESOURCE : Int   = R.layout.activity_weather
        val CLASS_TAG : String      = "Activity Weather"
    }

    @Inject
    lateinit var presenterWeather: PresenterWeather
    private lateinit var adapterWeatherDetail: AdapterWeatherDetail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT_RESOURCE)
        componentActivity?.inject(this)
        presenterWeather.attachView(this)
        initializeToolbar()
        initializeCollection()
        presenterWeather.weatherLoaded(intent.getStringExtra(UtilConstant.WEATHER_ID))

    }

    private fun initializeCollection() {
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(baseContext)
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        adapterWeatherDetail = AdapterWeatherDetail(this)
        view_weather_swiperefreshlayout?.setOnRefreshListener(this)
        view_weather_detail_recyclervieq?.layoutManager = layoutManager
        view_weather_detail_recyclervieq?.adapter = adapterWeatherDetail
    }


    private fun initializeToolbar() {
        setSupportActionBar(view_weather_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = intent.getStringExtra(UtilConstant.WEATHER_NAME)
    }

    override fun showDataWeather(
        arrayDay1: ArrayList<ModelWeatherList>,
        arrayDay2: ArrayList<ModelWeatherList>,
        arrayDay3: ArrayList<ModelWeatherList>
    ) {
        view_holder_failed_load_data.visibility = View.GONE
        view_weather_layout_content.visibility = View.VISIBLE
        adapterWeatherDetail.collectionWeather?.add(arrayDay1)
        adapterWeatherDetail.collectionWeather?.add(arrayDay2)
        adapterWeatherDetail.collectionWeather?.add(arrayDay3)
        runOnUiThread {
            adapterWeatherDetail.notifyItemRangeInserted(
                adapterWeatherDetail.itemCount,
                adapterWeatherDetail.collectionWeather!!.size
            )
        }

    }

    override fun showFailedLoad() {
        view_holder_failed_load_data.visibility = View.VISIBLE
        view_weather_layout_content.visibility = View.GONE
    }

    override fun onRefresh() {
        view_weather_swiperefreshlayout?.isRefreshing = false
        adapterWeatherDetail.collectionWeather?.clear()
        adapterWeatherDetail.notifyDataSetChanged()
        presenterWeather.weatherLoaded(intent.getStringExtra(UtilConstant.WEATHER_ID))
    }
}