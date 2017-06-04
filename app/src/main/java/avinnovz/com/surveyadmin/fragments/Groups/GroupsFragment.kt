package avinnovz.com.surveyadmin.fragments.Groups

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.adapters.GroupsAdapter
import avinnovz.com.surveyadmin.base.BaseActivity
import avinnovz.com.surveyadmin.base.BaseApplication
import avinnovz.com.surveyadmin.commons.inflate
import avinnovz.com.surveyadmin.delegates.GroupsDelegateAdapter
import avinnovz.com.surveyadmin.fragments.ManageGroup.ManageGroupDialogFragment
import avinnovz.com.surveyadmin.models.response.Department.DepartmentData
import avinnovz.com.surveyadmin.models.response.Department.Departments
import com.flipboard.bottomsheet.commons.MenuSheetView
import kotlinx.android.synthetic.main.fragment_groups.*
import javax.inject.Inject


/**
 * Created by rsbulanon on 6/4/17.
 */
class GroupsFragment : Fragment(), GroupsContract.View {

    @Inject lateinit var presenter: GroupsPresenterImpl
    private var baseActivity: BaseActivity? = null

    companion object {
        fun newInstance(): GroupsFragment {
            val fragment: GroupsFragment = GroupsFragment()
            return fragment
        }
    }

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

        //init groups recycler view
        rv_groups.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = GroupsAdapter(object : GroupsDelegateAdapter.OnSelectedDepartmentListener {
                override fun onSelectedGroup(selectedPosition: Int, department: DepartmentData) {
                    //init bottom menu
                    showGroupMenu(department)
                }
            })
        }

        presenter.onGetAllDepartments()
    }

    override fun onShowError(apiAction: String?, header: String, errorMessage: String, positiveText: String, negativeText: String?) {
    }

    override fun onShowLoading() {

    }

    override fun onLoadAllDepartments(departments: Departments) {
        (rv_groups.adapter as GroupsAdapter).addGroups(departments.content)
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

    fun showGroupMenu(department: DepartmentData) {
        val menuSheetView = MenuSheetView(activity, MenuSheetView.MenuType.LIST, "Manage Department",
                MenuSheetView.OnMenuItemClickListener { item ->
                    if (bs_menu.isSheetShowing) {
                        bs_menu.dismissSheet()
                    }
                    when (item.itemId) {
                        R.id.menu_update_group -> {
                            val fragment: ManageGroupDialogFragment = ManageGroupDialogFragment.newInstance(department)
                            fragment.onManageGroupListener = object : ManageGroupDialogFragment.OnManageGroupListener {
                                override fun onManageGroup(groupName: String, description: String) {
                                    fragment.dismiss()
                                    
                                }
                            }
                            fragment.show(fragmentManager, "update group")
                        }
                    }
                    true
                })
        menuSheetView.inflateMenu(R.menu.group_menu)
        bs_menu.showWithSheetView(menuSheetView)
    }
}