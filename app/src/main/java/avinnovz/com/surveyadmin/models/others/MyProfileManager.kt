package avinnovz.com.surveyadmin.models.others

import avinnovz.com.surveyadmin.models.response.MyProfile
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rsbulanon on 6/4/17.
 */
@Singleton
class MyProfileManager @Inject constructor() {

    var myProfile:  MyProfile? = null

}