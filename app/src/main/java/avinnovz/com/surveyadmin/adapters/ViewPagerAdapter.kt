package avinnovz.com.surveyadmin.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by rsbulanon on 6/4/17.
 */
class ViewPagerAdapter : FragmentStatePagerAdapter {

    private var fragments: ArrayList<Fragment>? = null

    constructor(fm: FragmentManager, fragments: ArrayList<Fragment>) : super(fm) {
        this.fragments = fragments
    }

    override fun getItem(position: Int): Fragment = fragments!![position]

    override fun getCount(): Int = fragments!!.size
}