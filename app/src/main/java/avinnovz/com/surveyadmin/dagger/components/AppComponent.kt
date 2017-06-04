package avinnovz.com.surveyadmin.dagger.components

import android.content.Context
import avinnovz.com.surveyadmin.dagger.modules.RetrofitModule
import avinnovz.com.surveyadmin.interfaces.ApiInterface
import avinnovz.com.surveyadmin.models.others.MyProfileManager
import avinnovz.com.surveyadmin.models.others.TokenManager
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by rsbulanon on 6/3/17.
 */
@Singleton
@Component(
        modules = arrayOf(RetrofitModule::class)
)
interface AppComponent {
    fun apiInterface(): ApiInterface
    fun retrofit(): Retrofit
    fun context(): Context
    fun tokenManager() : TokenManager
    fun myProfileManager(): MyProfileManager
}