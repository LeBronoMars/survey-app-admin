package proto.com.kotlinapp.interfaces

import avinnovz.com.surveyadmin.models.request.Login
import avinnovz.com.surveyadmin.models.response.MyProfile
import proto.com.kotlinapp.models.response.LoginResponse
import retrofit2.http.*
import rx.Observable

/**
 * Created by rsbulanon on 6/3/17.
 */
interface ApiInterface {

    @POST("/api/user/auth")
    @Headers("Content-Type: application/json")
    fun login(@Body login: Login): Observable<LoginResponse>

    @GET("/api/users/me")
    fun myProfile(@Header("Authorization") header: String): Observable<MyProfile>
}