package avinnovz.com.surveyadmin.interfaces

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by rsbulanon on 6/4/17.
 */
interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, position: Int)
}