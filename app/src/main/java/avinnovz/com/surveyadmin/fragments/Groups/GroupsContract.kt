package avinnovz.com.surveyadmin.fragments.Groups

import avinnovz.com.surveyadmin.base.BaseView
import avinnovz.com.surveyadmin.models.response.Department.Departments

/**
 * Created by rsbulanon on 6/4/17.
 */
interface GroupsContract {

    interface View: BaseView {
        fun onLoadAllDepartments(departments: Departments)
    }

    interface Presenter {
        fun onGetAllDepartments()
    }
}