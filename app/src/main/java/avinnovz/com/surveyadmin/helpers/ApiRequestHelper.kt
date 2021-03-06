package avinnovz.com.surveyadmin.helpers

import avinnovz.com.surveyadmin.commons.ApiActions
import avinnovz.com.surveyadmin.interfaces.ApiInterface
import avinnovz.com.surveyadmin.interfaces.OnApiRequestListener
import avinnovz.com.surveyadmin.models.request.Login
import avinnovz.com.surveyadmin.models.request.NewDepartment
import com.google.gson.Gson
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by rsbulanon on 6/3/17.
 */
class ApiRequestHelper constructor(val apiInterface: ApiInterface) {

    private val BEARER: String = "Bearer "

    var onApiRequestListener: OnApiRequestListener? = null

    fun login(login: Login) {
        onApiRequestListener!!.onApiRequestBegin(ApiActions.LOGIN)
        handleObservableResult(ApiActions.LOGIN, apiInterface.login(login))
    }

    fun myProfile(token: String) {
        onApiRequestListener!!.onApiRequestBegin(ApiActions.GET_MY_PROFILE)
        handleObservableResult(ApiActions.GET_MY_PROFILE, apiInterface.myProfile(BEARER + token))
    }

    fun getAllDepartments(token: String) {
        onApiRequestListener!!.onApiRequestBegin(ApiActions.GET_ALL_DEPARTMENTS)
        handleObservableResult(ApiActions.GET_ALL_DEPARTMENTS, apiInterface.getAllDepartments(BEARER + token))
    }

    fun createNewDepartment(token: String, newDepartment: NewDepartment) {
        onApiRequestListener!!.onApiRequestBegin(ApiActions.POST_ADD_DEPARTMENT)
        handleObservableResult(ApiActions.POST_ADD_DEPARTMENT, apiInterface.createDepartment(BEARER + token, newDepartment))
    }

    fun deleteDepartment(token: String, departmentId: String) {
        onApiRequestListener!!.onApiRequestBegin(ApiActions.DELETE_DEPARTMENT)
        handleObservableResult(ApiActions.DELETE_DEPARTMENT, apiInterface.deleteDepartment(BEARER + token, departmentId))
    }

    fun updateDepartment(token: String, departmentId: String, newDepartment: NewDepartment) {
        onApiRequestListener!!.onApiRequestBegin(ApiActions.PUT_UPDATE_DEPARTMENT)
        handleObservableResult(ApiActions.PUT_UPDATE_DEPARTMENT, apiInterface
                .updateDepartment(BEARER + token, departmentId, newDepartment))
    }

    fun handleObservableResult(action: String?, observable: Observable<*>) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe({ result -> onApiRequestListener!!.onApiRequestSuccess(action, result) },
                        { throwable -> onApiRequestListener!!.onApiRequestFailed(action, throwable as Throwable) }
                )
    }

    fun <T> parseError(clazz: Class<*>, json: String): T  = Gson().fromJson(json, clazz) as T
}