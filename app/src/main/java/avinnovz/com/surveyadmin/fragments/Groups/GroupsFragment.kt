package avinnovz.com.surveyadmin.fragments.Groups

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.base.BaseActivity
import avinnovz.com.surveyadmin.base.BaseApplication
import avinnovz.com.surveyadmin.commons.inflate
import avinnovz.com.surveyadmin.models.response.Department.Departments

/**
 * Created by rsbulanon on 6/4/17.
 */
class GroupsFragment : Fragment(), GroupsContract.View {

    private var baseActivity: BaseActivity? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_groups)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity = activity as BaseActivity

        DaggerGroupsComponent.builder()
                .appComponent(BaseApplication.appComponent)
                .groupsModule(GroupsModule(this))
                .build().inject(this)
    }

    override fun onShowError(apiAction: String?, header: String, errorMessage: String, positiveText: String, negativeText: String?) {
    }

    override fun onShowLoading() {

    }

    override fun onLoadAllDepartments(departments: Departments) {
    }

    override fun onDismissLoading() {
    }

    override fun onFailedToConnect() {
        baseActivity!!.showFailedToConnectDialog()
    }

    override fun onSocketTimeout() {
        baseActivity!!.showSocketTimeoutDialog()
    }

    override fun onServerRelatedError() {
        baseActivity!!.showServerRelatedErrorDialog()
    }

    override fun onNoConnectionError() {
        baseActivity!!.showNoConnectionErrorDialog()
    }
}