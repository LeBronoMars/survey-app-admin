package avinnovz.com.surveyadmin.activities.Login

import avinnovz.com.surveyadmin.base.BaseView
import avinnovz.com.surveyadmin.models.request.Login
import avinnovz.com.surveyadmin.models.response.MyProfile
import proto.com.kotlinapp.models.response.LoginResponse

/**
 * Created by rsbulanon on 6/3/17.
 */
interface LoginContract {

    interface View : BaseView {
        fun onLoginSuccess()
        fun onLoadMyProfile(myProfile: MyProfile)
    }

    interface Presenter {
        fun onLoginRequest(login: Login)

        fun onGetMyProfile()
    }
}