package avinnovz.com.surveyadmin.fragments.ManageGroup

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.base.BaseActivity
import avinnovz.com.surveyadmin.commons.AppConstants
import avinnovz.com.surveyadmin.models.response.Department.DepartmentData

/**
 * Created by rsbulanon on 6/4/17.
 */
class ManageGroupDialogFragment : DialogFragment() {

    private var department: DepartmentData? = null
    var onManageGroupListener: OnManageGroupListener? = null

    companion object {
        fun newInstance(department: DepartmentData?) : ManageGroupDialogFragment {
            val fragment: ManageGroupDialogFragment = ManageGroupDialogFragment()
            fragment.department = department
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = activity.layoutInflater.inflate(R.layout.dialog_fragment_manage_group, null)
        val dialog: Dialog = Dialog(activity)
        val tv_header = view.findViewById(R.id.tv_header) as TextView
        val et_group_name = view.findViewById(R.id.et_group_name) as EditText
        val et_group_description = view.findViewById(R.id.et_group_description) as EditText
        val tv_create_group = view.findViewById(R.id.tv_create_group) as TextView
        val baseActivity: BaseActivity = activity as BaseActivity

        department?.apply {
            tv_header.text = resources.getString(R.string.update_group_header)
            tv_create_group.text = resources.getString(R.string.update_group)
            et_group_name.setText(name)
            et_group_name.setSelection(et_group_name.text.toString().length)
            et_group_description.setText(description)
        }

        tv_create_group.let {
            tv_create_group.setOnClickListener {
                val groupName: String = et_group_name.text.toString().trim()
                val groupNameDesc: String = et_group_description?.text.toString().trim()

                if (TextUtils.isEmpty(groupName)) {
                    baseActivity.setError(et_group_name, AppConstants.WARN_FIELD_REQUIRED)
                } else {
                    if (groupName == null) {
                        onManageGroupListener?.apply {
                            onManageGroup(groupName, groupNameDesc)
                        }
                    } else {
                        if (groupName.equals(department!!.name) && groupNameDesc.equals(department!!.description)) {
                            baseActivity.showToast("There are no changes detected.")
                        } else {
                            onManageGroupListener?.apply {
                                onManageGroup(groupName, groupNameDesc)
                            }
                        }
                    }
                }
            }
        }

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        return dialog
    }

    interface OnManageGroupListener {
        fun onManageGroup(groupName: String, description: String)
    }

}