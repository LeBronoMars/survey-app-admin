package avinnovz.com.surveyadmin.interfaces

import avinnovz.com.surveyadmin.models.request.NewDepartment
import avinnovz.com.surveyadmin.models.request.Login
import avinnovz.com.surveyadmin.models.response.Department.DepartmentData
import avinnovz.com.surveyadmin.models.response.Department.Departments
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

    @GET("/api/departments")
    fun getAllDepartments(@Header("Authorization") header: String): Observable<Departments>

    @POST("/api/department")
    @Headers("Content-Type: application/json")
    fun createDepartment(@Header("Authorization") header: String,
                         @Body newDepartment: NewDepartment): Observable<DepartmentData>
}