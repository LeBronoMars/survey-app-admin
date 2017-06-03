package avinnovz.com.surveyadmin.base

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

/**
 * Created by rsbulanon on 6/3/17.
 */
abstract class BasePresenterImpl {

    @Inject lateinit var context: Context

    fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        activeNetwork?.let {
            return (activeNetwork.type == ConnectivityManager.TYPE_WIFI
                    || activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
        }
        return false
    }

}