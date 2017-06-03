package avinnovz.com.surveyadmin.activities.Login

import avinnovz.com.surveyadmin.base.BasePresenterImpl
import avinnovz.com.surveyadmin.helpers.ApiRequestHelper
import avinnovz.com.surveyadmin.models.request.Login
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

    private var apiRequestHelper: ApiRequestHelper? = null
    private var view: LoginContract.View? = null

    init {
        this.view = view
        this.apiRequestHelper = ApiRequestHelper(apiInterface)
        this.apiRequestHelper!!.onApiRequestListener = this
    }

    override fun onLoginRequest(login: Login) {
        if (isNetworkAvailable()) {
            apiRequestHelper?.apply {
                login(login)
            }
        } else {
            view!!.onNoConnectionError()
        }
    }

    override fun onApiRequestBegin(action: String?) {
        action?.let {
            view!!.onShowLoading()
        }
    }

    override fun onApiRequestFailed(action: String?, t: Throwable) {
        view!!.onDismissLoading()
        val errorMessage: String = t.message!!

        if (errorMessage.contains("failed to connect") || errorMessage.contains("Failed to connect")) {
            view!!.onFailedToConnect()
        } else if (errorMessage.contains("timeout")) {
            view!!.onSocketTimeout()
        } else if (errorMessage.contains("No address associated with hostname") || errorMessage.contains("Unable to resolve host")) {
            view!!.onNoConnectionError()
        } else {
            if (t is HttpException) {
                try {
                    val json = t.response().errorBody().string()

                    if (t.code() >= 500) {
                        view!!.onServerRelatedError()
                    } else {
                        val requestError:GenericResponse = apiRequestHelper!!.parseError(GenericResponse::class.java, json)
                        requestError?.apply {
                            view!!.onShowError(action, "Login Failed", requestError.message, "Close", null)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            } else {
                view!!.onServerRelatedError()
            }
        }
    }

    override fun onApiRequestSuccess(action: String?, result: Any) {
        val loginResponse: LoginResponse = result as LoginResponse
        this.view!!.onDismissLoading()
        this.view!!.onLoginSuccess(loginResponse)
    }
}