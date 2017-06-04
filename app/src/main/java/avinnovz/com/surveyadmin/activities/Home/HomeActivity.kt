package avinnovz.com.surveyadmin.activities.Home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.adapters.ViewPagerAdapter
import avinnovz.com.surveyadmin.base.BaseActivity
import avinnovz.com.surveyadmin.fragments.Groups.GroupsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(GroupsFragment.newInstance())

        vp_screens.adapter = ViewPagerAdapter(supportFragmentManager, fragments)

        bnv_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_groups -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_members -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_questionnaire -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
