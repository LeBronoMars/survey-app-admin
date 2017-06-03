package avinnovz.com.surveyadmin.activities.Login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.base.BaseActivity
import avinnovz.com.surveyadmin.base.BaseApplication
import avinnovz.com.surveyadmin.models.request.Login
import kotlinx.android.synthetic.main.activity_login.*
import proto.com.kotlinapp.commons.AppConstants
import proto.com.kotlinapp.models.response.LoginResponse
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject lateinit var presenter: LoginPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DaggerLoginComponent.builder()
                .appComponent(BaseApplication.appComponent)
                .loginModule(LoginModule(this))
                .build().inject(this)

        btn_login.setOnClickListener {
            val username: String = et_username.text.toString().trim()
            val password: String = et_password.text.toString().trim()

            if (TextUtils.isEmpty(username)) {
                setError(et_username, AppConstants.WARN_FIELD_REQUIRED)
            } else if (TextUtils.isEmpty(password)) {
                setError(et_password, AppConstants.WARN_FIELD_REQUIRED)
            } else {
                presenter.onLoginRequest(Login(username , password))
            }
        }
    }

    override fun onShowError(apiAction: String?, header: String, errorMessage: String, positiveText: String, negativeText: String?) {
        showConfirmDialog(apiAction, header, errorMessage, positiveText, negativeText, null, true)
    }

    override fun onShowLoading() {
        showProgressDialog("Login", "Checking your credentials, Please wait...", false)
    }

    override fun onDismissLoading() {
        dismissProgressDialog()
    }

    override fun onFailedToConnect() {
        showFailedToConnectDialog()
    }

    override fun onSocketTimeout() {
        showSocketTimeoutDialog()
    }

    override fun onServerRelatedError() {
        showServerRelatedErrorDialog()
    }

    override fun onNoConnectionError() {
        showNoConnectionErrorDialog()
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        Log.d("login", "on activity ${loginResponse.token}")
        showToast("Welcome!")
    }
}
