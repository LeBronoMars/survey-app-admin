package avinnovz.com.surveyadmin.base

import android.app.Application
import avinnovz.com.surveyadmin.dagger.components.AppComponent
import avinnovz.com.surveyadmin.dagger.components.DaggerAppComponent
import avinnovz.com.surveyadmin.dagger.modules.RetrofitModule

/**
 * Created by rsbulanon on 6/3/17.
 */
class BaseApplication : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .retrofitModule(RetrofitModule(this))
                .build()
    }
}