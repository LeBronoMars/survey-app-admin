package avinnovz.com.surveyadmin.activities.Login

import avinnovz.com.surveyadmin.base.BaseView
import avinnovz.com.surveyadmin.models.request.Login
import proto.com.kotlinapp.models.response.LoginResponse

/**
 * Created by rsbulanon on 6/3/17.
 */
interface LoginContract {

    interface View : BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
    }

    interface Presenter {
        fun onLoginRequest(login: Login)
    }
}