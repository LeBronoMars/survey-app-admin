package avinnovz.com.surveyadmin.fragments.Members

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.base.BaseActivity
import avinnovz.com.surveyadmin.commons.inflate

/**
 * Created by rsbulanon on 6/4/17.
 */
class MembersFragment : Fragment() {

    private var baseActivity: BaseActivity? = null

    companion object {
        fun newInstance(): MembersFragment {
            val fragment: MembersFragment = MembersFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_members)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}