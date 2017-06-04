package avinnovz.com.surveyadmin.activities.Login

import android.util.Log
import avinnovz.com.surveyadmin.base.BasePresenterImpl
import avinnovz.com.surveyadmin.commons.ApiActions
import avinnovz.com.surveyadmin.helpers.ApiRequestHelper
import avinnovz.com.surveyadmin.models.others.TokenManager
import avinnovz.com.surveyadmin.models.request.Login
import avinnovz.com.surveyadmin.models.response.MyProfile
import proto.com.kotlinapp.interfaces.ApiInterface
import proto.com.kotlinapp.interfaces.OnApiRequestListener
import proto.com.kotlinapp.models.response.GenericResponse
import proto.com.kotlinapp.models.response.LoginResponse
import retrofit2.adapter.rxjava.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by rsbulanon on 6/3/17.
 */
class LoginPresenterImpl @Inject constructor(apiInterface: ApiInterface, view: LoginContract.View):
        BasePresenterImpl(), LoginContract.Presenter, OnApiRequestListener {

    @Inject lateinit var tokenManager: TokenManager

    private var apiRequestHelper: ApiRequestHelper? = null
    private var view: LoginContract.View = view

    init {
        this.apiRequestHelper = ApiRequestHelper(apiInterface)
        this.apiRequestHelper!!.onApiRequestListener = this
    }

    override fun onLoginRequest(login: Login) {
        if (isNetworkAvailable()) {
            apiRequestHelper?.apply {
                login(login)
            }
        } else {
            view.onNoConnectionError()
        }
    }

    override fun onGetMyProfile() {
        if (isNetworkAvailable()) {
            apiRequestHelper?.apply {
                myProfile(tokenManager.loginResponse!!.token)
            }
        } else {
            view.onNoConnectionError()
        }
    }

    override fun onApiRequestBegin(action: String?) {
        action?.let {
            view.onShowLoading()
        }
    }

    override fun onApiRequestFailed(action: String?, t: Throwable) {
        Log.d("login", "failed request --> ${t.message}")
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
                        val requestError:GenericResponse = apiRequestHelper!!.parseError(GenericResponse::class.java, json)
                        requestError?.apply {
                            view.onShowError(action, "Login Failed", requestError.message, "Close", null)
                        }
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
        this.view.onDismissLoading()

        if (action.equals(ApiActions.LOGIN)) {
            val loginResponse: LoginResponse = result as LoginResponse
            tokenManager.loginResponse = loginResponse
            this.view.onLoginSuccess()
        } else if (action.equals(ApiActions.GET_MY_PROFILE)) {
            this.view.onLoadMyProfile(result as MyProfile)
        }
    }
}