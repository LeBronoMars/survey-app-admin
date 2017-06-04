package avinnovz.com.surveyadmin.fragments.Groups

import android.app.ProgressDialog
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
import avinnovz.com.surveyadmin.interfaces.OnConfirmDialogListener
import avinnovz.com.surveyadmin.models.request.NewDepartment
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
    private var progressDialog: ProgressDialog? = null
    private var selectedDepartment: DepartmentData? = null

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

        fab_add_group.setOnClickListener {
            //add new group
            val fragment: ManageGroupDialogFragment = ManageGroupDialogFragment.newInstance(null)
            fragment.onManageGroupListener = object : ManageGroupDialogFragment.OnManageGroupListener {
                override fun onManageGroup(groupName: String, description: String) {
                    fragment.dismiss()
                    presenter.onCreateNewDepartment(NewDepartment(groupName, description))
                }
            }
            fragment.show(fragmentManager, "add group")
        }

        presenter.onGetAllDepartments()
    }

    override fun onShowError(apiAction: String?, header: String, errorMessage: String, positiveText: String, negativeText: String?) {
        baseActivity!!.showConfirmDialog(apiAction, header, errorMessage, positiveText, negativeText, null, false)
    }

    override fun onShowLoading() {
        progressDialog?.let {
            progressDialog = null
        }
        progressDialog = ProgressDialog(activity)
        progressDialog?.apply {
            setCancelable(false)
            show()
        }
    }

    override fun onLoadNewDepartment(departmentData: DepartmentData) {
        (rv_groups.adapter as GroupsAdapter).addGroup(departmentData)
    }

    override fun onLoadAllDepartments(departments: Departments) {
        (rv_groups.adapter as GroupsAdapter).addGroups(departments.content)
    }

    override fun onLoadUpdatedDepartment(departmentData: DepartmentData) {
        (rv_groups.adapter as GroupsAdapter).updateGroup(departmentData)
    }

    override fun onRemoveDeletedDepartment() {
        (rv_groups.adapter as GroupsAdapter).removeGroup(selectedDepartment!!)
    }

    override fun onDismissLoading() {
        progressDialog?.apply {
            dismiss()
        }
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
        selectedDepartment?.let {
            selectedDepartment = null
        }
        selectedDepartment = department
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
                                    presenter.onUpdateDepartment(department.id, NewDepartment(groupName, description))
                                }
                            }
                            fragment.show(fragmentManager, "update group")
                        }
                        R.id.menu_remove_group -> {
                            baseActivity!!.showConfirmDialog(null, "Remove Group", "Are you sure you want " +
                                    "to delete this group?", "Yes", "Cancel", object: OnConfirmDialogListener {
                                        override fun onConfirmed(action: String?) {
                                            presenter.onDeleteDepartment(department.id)
                                        }

                                        override fun onCancelled(action: String?) {
                                        }
                                    }, true)
                        }
                    }
                    true
                })
        menuSheetView.inflateMenu(R.menu.group_menu)
        bs_menu.showWithSheetView(menuSheetView)
    }
}