package avinnovz.com.surveyadmin.activities.Login

import android.os.Bundle
import android.text.TextUtils
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.activities.Home.HomeActivity
import avinnovz.com.surveyadmin.base.BaseActivity
import avinnovz.com.surveyadmin.base.BaseApplication
import avinnovz.com.surveyadmin.commons.AppConstants
import avinnovz.com.surveyadmin.models.others.MyProfileManager
import avinnovz.com.surveyadmin.models.request.Login
import avinnovz.com.surveyadmin.models.response.MyProfile
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject lateinit var presenter: LoginPresenterImpl
    @Inject lateinit var myProfileManager: MyProfileManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DaggerLoginComponent.builder()
                .appComponent(BaseApplication.appComponent)
                .loginModule(LoginModule(this))
                .build().inject(this)

        et_username.setText("nedflanders")
        et_password.setText("P@ssw0rd")

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

    override fun onLoginSuccess() {
        presenter.onGetMyProfile()
    }

    override fun onLoadMyProfile(myProfile: MyProfile) {
        this.myProfileManager.myProfile = myProfile
        moveToOtherActivity(HomeActivity::class.java)
    }
}
