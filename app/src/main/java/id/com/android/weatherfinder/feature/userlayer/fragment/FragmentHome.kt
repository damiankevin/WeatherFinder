package id.com.android.weatherfinder.feature.userlayer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.com.android.weatherfinder.R
import id.com.android.weatherfinder.feature.ActivityBase
import id.com.android.weatherfinder.feature.presenterlayer.PresenterHome
import id.com.android.weatherfinder.feature.viewlayer.ViewHome
import id.com.android.weatherfinder.model.weather.ModelWeatherList
import id.com.android.weatherfinder.tools.UtilConstant
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.holder_no_location.*
import javax.inject.Inject


class FragmentHome : FragmentBase(), ViewHome,SwipeRefreshLayout.OnRefreshListener {

    companion object {
        var LAYOUT_RESOURCE : Int           = R.layout.fragment_home
    }

    @Inject
    lateinit var presenterHome            : PresenterHome

    private fun initializePresenter() {
        (activity as ActivityBase).componentActivity?.inject(this)
        presenterHome.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializePresenter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(LAYOUT_RESOURCE, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view_weather_swiperefreshlayout?.setOnRefreshListener(this)
        presenterHome.viewCreated(context)
    }


    override fun onDestroy() {
        presenterHome.detachView()
        super.onDestroy()
    }

    override fun showDataWeather(content: ModelWeatherList?) {

        activity?.runOnUiThread {
            view_holder_no_location.visibility = View.GONE
            view_weather_layout_content.visibility = View.VISIBLE
            view_home_weather_temp.text = convertKelvinToCelcius(content?.main?.temp)
            viewhome_weather_min_max_temp.text = convertKelvinToCelcius(content?.main?.temp_min) + "/ "+convertKelvinToCelcius(content?.main?.temp_max)
            view_home_weathe_haze.text = content?.weather?.get(0)?.main
            view_home_weather_wind.text =  content?.wind?.speed.toString()+"km/h  | "+ content?.wind?.deg.toString()+"°"
        }

    }

    private fun convertKelvinToCelcius(temp: Double?): String? {
        var celcius= (temp!! -273.15).toInt()
        return "$celcius°"
    }

    override fun showFailedLoad() {
        view_holder_no_location.visibility = View.VISIBLE
        view_weather_layout_content.visibility = View.GONE

    }

    override fun onRefresh() {
        view_weather_swiperefreshlayout?.isRefreshing = false
        presenterHome.viewCreated(context)
    }


}