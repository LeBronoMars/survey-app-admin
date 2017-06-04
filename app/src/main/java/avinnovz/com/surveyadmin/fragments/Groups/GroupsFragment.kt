package avinnovz.com.surveyadmin.fragments.Groups

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import avinnovz.com.surveyadmin.R
import proto.com.kotlinapp.commons.inflate

/**
 * Created by rsbulanon on 6/4/17.
 */
class GroupsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_groups)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}