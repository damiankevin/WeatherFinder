package id.com.android.weatherfinder.injector.component

import dagger.Component
import id.com.android.weatherfinder.feature.ActivityBase
import id.com.android.weatherfinder.feature.userlayer.activity.ui.HomeActivity
import id.com.android.weatherfinder.feature.userlayer.activity.ui.SplashActivity
import id.com.android.weatherfinder.feature.userlayer.activity.ui.WeatherActivity
import id.com.android.weatherfinder.feature.userlayer.fragment.FragmentFavourite
import id.com.android.weatherfinder.feature.userlayer.fragment.FragmentHome
import id.com.android.weatherfinder.feature.userlayer.fragment.FragmentSearch
import id.com.android.weatherfinder.injector.module.ModuleActivity
import id.com.android.weatherfinder.injector.scope.PerActivity

@PerActivity
@Component(dependencies = [(ComponentApplication::class)], modules = [(ModuleActivity::class)])
interface ComponentActivity {
    fun inject (activityBase                : ActivityBase)
    fun inject (activitySplashActivity      : SplashActivity)
    fun inject (activityHomeActivity        : HomeActivity)
    fun inject (activityWeather             : WeatherActivity)

    fun inject (fragmentHome                : FragmentHome)
    fun inject (fragmentSearch              : FragmentSearch)
    fun inject (fragmentFavourite           : FragmentFavourite)
}
