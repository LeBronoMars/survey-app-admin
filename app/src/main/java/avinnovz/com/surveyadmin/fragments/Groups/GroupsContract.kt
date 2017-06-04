package avinnovz.com.surveyadmin.fragments.Groups

import avinnovz.com.surveyadmin.base.BaseView
import avinnovz.com.surveyadmin.models.request.NewDepartment
import avinnovz.com.surveyadmin.models.response.Department.DepartmentData
import avinnovz.com.surveyadmin.models.response.Department.Departments

/**
 * Created by rsbulanon on 6/4/17.
 */
interface GroupsContract {

    interface View: BaseView {
        fun onLoadAllDepartments(departments: Departments)

        fun onLoadNewDepartment(departmentData: DepartmentData)

        fun onLoadUpdatedDepartment(departmentData: DepartmentData)

        fun onRemoveDeletedDepartment()
    }

    interface Presenter {
        fun onGetAllDepartments()

        fun onCreateNewDepartment(newDepartment: NewDepartment)

        fun onUpdateDepartment(departmentId: String, newDepartment: NewDepartment)

        fun onDeleteDepartment(departmentId: String)
    }
}