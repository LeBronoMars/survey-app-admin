package avinnovz.com.surveyadmin.delegates;

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.commons.inflate
import avinnovz.com.surveyadmin.interfaces.ViewType
import avinnovz.com.surveyadmin.interfaces.ViewTypeDelegateAdapter
import avinnovz.com.surveyadmin.models.response.Department.DepartmentData
import kotlinx.android.synthetic.main.row_group.view.*

/**
 * Created by rsbulanon on 6/4/17.
 */
class GroupsDelegateAdapter(val onSelectedDepartmentListener: OnSelectedDepartmentListener) : ViewTypeDelegateAdapter {

    interface OnSelectedDepartmentListener {
        fun onSelectedGroup(selectedPosition: Int, department: DepartmentData)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return GroupViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, position: Int) {
        holder as GroupViewHolder
        holder.bind(item as DepartmentData, position)
    }

    inner class GroupViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.row_group)) {

        fun bind(department: DepartmentData, position: Int) = with(itemView) {
            tv_group_name.text = department.name
            tv_group_description.text = department.description
            super.itemView.setOnClickListener { onSelectedDepartmentListener.onSelectedGroup(position, department) }
        }
    }
}