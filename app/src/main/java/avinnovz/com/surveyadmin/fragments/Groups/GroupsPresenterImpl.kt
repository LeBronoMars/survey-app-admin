package avinnovz.com.surveyadmin.fragments.Groups

import avinnovz.com.surveyadmin.activities.Login.LoginContract
import avinnovz.com.surveyadmin.base.BasePresenterImpl
import avinnovz.com.surveyadmin.helpers.ApiRequestHelper
import avinnovz.com.surveyadmin.models.others.TokenManager
import proto.com.kotlinapp.interfaces.ApiInterface
import proto.com.kotlinapp.interfaces.OnApiRequestListener
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

        } else {
            view.onNoConnectionError()
        }
    }

    override fun onApiRequestBegin(action: String?) {
        view.onShowLoading()
    }

    override fun onApiRequestSuccess(action: String?, result: Any) {
        view.onDismissLoading()
    }

    override fun onApiRequestFailed(action: String?, t: Throwable) {
        view.onDismissLoading()
    }

}