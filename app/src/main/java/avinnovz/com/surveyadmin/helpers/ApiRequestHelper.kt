package avinnovz.com.surveyadmin.helpers

import avinnovz.com.surveyadmin.commons.ApiActions
import avinnovz.com.surveyadmin.models.request.Login
import com.google.gson.Gson
import proto.com.kotlinapp.interfaces.ApiInterface
import proto.com.kotlinapp.interfaces.OnApiRequestListener
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by rsbulanon on 6/3/17.
 */
class ApiRequestHelper constructor(val apiInterface: ApiInterface) {

    var onApiRequestListener: OnApiRequestListener? = null

    fun login(login: Login) {
        onApiRequestListener!!.onApiRequestBegin(ApiActions.LOGIN)
        handleObservableResult(ApiActions.LOGIN, apiInterface.login(login))
    }

    fun handleObservableResult(action: String?, observable: Observable<*>) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe({ result -> onApiRequestListener!!.onApiRequestSuccess(action, result) },
                        { throwable -> onApiRequestListener!!.onApiRequestFailed(action, throwable as Throwable) }
                )
    }

    fun <T> parseError(clazz: Class<*>, json: String): T  = Gson().fromJson(json, clazz) as T
}