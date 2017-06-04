package avinnovz.com.surveyadmin.fragments.Groups

import android.util.Log
import avinnovz.com.surveyadmin.base.BasePresenterImpl
import avinnovz.com.surveyadmin.commons.ApiActions
import avinnovz.com.surveyadmin.helpers.ApiRequestHelper
import avinnovz.com.surveyadmin.interfaces.ApiInterface
import avinnovz.com.surveyadmin.interfaces.OnApiRequestListener
import avinnovz.com.surveyadmin.models.others.TokenManager
import avinnovz.com.surveyadmin.models.request.NewDepartment
import avinnovz.com.surveyadmin.models.response.Department.DepartmentData
import avinnovz.com.surveyadmin.models.response.Department.Departments
import proto.com.kotlinapp.models.response.GenericResponse
import retrofit2.adapter.rxjava.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by rsbulanon on 6/4/17.
 */
class GroupsPresenterImpl @Inject constructor(apiInterface: ApiInterface, view: GroupsContract.View) :
        BasePresenterImpl(), GroupsContract.Presenter, OnApiRequestListener {

    @Inject lateinit var tokenManager: TokenManager

    private var apiRequestHelper: ApiRequestHelper? = null
    private var view: GroupsContract.View = view

    init {
        this.apiRequestHelper = ApiRequestHelper(apiInterface)
        this.apiRequestHelper!!.onApiRequestListener = this
    }

    override fun onGetAllDepartments() {
        if (isNetworkAvailable()) {
            apiRequestHelper?.apply {
                getAllDepartments(tokenManager.loginResponse!!.token)
            }
        } else {
            view.onNoConnectionError()
        }
    }

    override fun onCreateNewDepartment(newDepartment: NewDepartment) {
        if (isNetworkAvailable()) {
            apiRequestHelper?.apply {
                createNewDepartment(tokenManager.loginResponse!!.token, newDepartment)
            }
        } else {
            view.onNoConnectionError()
        }
    }

    override fun onApiRequestBegin(action: String?) {
        view.onShowLoading()
    }

    override fun onApiRequestFailed(action: String?, t: Throwable) {
        Log.d("groups", "request failed --> ${t.message}")
        view.onDismissLoading()
        val errorMessage: String = t.message!!

        if (errorMessage.contains("failed to connect") || errorMessage.contains("Failed to connect")) {
            view.onFailedToConnect()
        } else if (errorMessage.contains("timeout")) {
            view.onSocketTimeout()
        } else if (errorMessage.contains("No address associated with hostname") || errorMessage.contains("Unable to resolve host")) {
            view.onNoConnectionError()
        } else {
            if (t is HttpException) {
                try {
                    val json = t.response().errorBody().string()

                    if (t.code() >= 500) {
                        view.onServerRelatedError()
                    } else {
                        val requestError: GenericResponse = apiRequestHelper!!.parseError(GenericResponse::class.java, json)
                        view.onShowError(action, "Groups", requestError.message, "Close", null)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                view.onServerRelatedError()
            }
        }
    }

    override fun onApiRequestSuccess(action: String?, result: Any) {
        view.onDismissLoading()
        if (action!!.equals(ApiActions.GET_ALL_DEPARTMENTS)) {
            view.onLoadAllDepartments(result as Departments)
        } else if (action!!.equals(ApiActions.POST_ADD_DEPARTMENT)) {
            view.onLoadNewDepartment(result as DepartmentData)
        }
    }

}