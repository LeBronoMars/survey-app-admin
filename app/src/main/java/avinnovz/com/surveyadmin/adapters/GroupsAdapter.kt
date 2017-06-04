package avinnovz.com.surveyadmin.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import avinnovz.com.surveyadmin.commons.AdapterConstants
import avinnovz.com.surveyadmin.delegates.GroupsDelegateAdapter
import avinnovz.com.surveyadmin.interfaces.ViewType
import avinnovz.com.surveyadmin.interfaces.ViewTypeDelegateAdapter
import avinnovz.com.surveyadmin.models.response.Department.DepartmentData

/**
 * Created by rsbulanon on 6/4/17.
 */
class GroupsAdapter(val listener: GroupsDelegateAdapter.OnSelectedDepartmentListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        delegateAdapters.put(AdapterConstants.GROUPS, GroupsDelegateAdapter(listener))
        items = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getViewType()
    }

    fun addGroups(groups: List<DepartmentData>) {
        // first remove loading and notify
        //val initPosition = items.size - 1
        //items.removeAt(initPosition)
        //notifyItemRemoved(initPosition)

        // insert news and the loading at the end of the list
        items.addAll(groups)
        notifyDataSetChanged()
        //notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun addGroup(group: DepartmentData) {
        items.add(group)
        notifyDataSetChanged()
    }

    fun removeGroup(group: DepartmentData) {
        items.forEachIndexed { index, viewType ->
            if ((viewType as DepartmentData).id.equals(group.id)) {
                items.removeAt(index)
                notifyDataSetChanged()
                return
            }
        }
    }

    fun updateGroup(group: DepartmentData) {
        items.forEachIndexed { index, viewType ->
            if ((viewType as DepartmentData).id.equals(group.id)) {
                items.set(index, group)
                notifyDataSetChanged()
                return
            }
        }
    }
}