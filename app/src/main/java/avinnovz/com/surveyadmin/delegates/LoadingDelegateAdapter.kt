package avinnovz.com.surveyadmin.delegates;

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.commons.inflate
import avinnovz.com.surveyadmin.interfaces.ViewType
import avinnovz.com.surveyadmin.interfaces.ViewTypeDelegateAdapter

/**
 * Created by rsbulanon on 6/4/17.
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class TurnViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.row_item_loading))
}