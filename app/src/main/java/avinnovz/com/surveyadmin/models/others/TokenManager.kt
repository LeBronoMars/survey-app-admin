package avinnovz.com.surveyadmin.models.others

import proto.com.kotlinapp.models.response.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rsbulanon on 6/3/17.
 */
@Singleton
class TokenManager @Inject constructor() {

    var loginResponse: LoginResponse? = null

}