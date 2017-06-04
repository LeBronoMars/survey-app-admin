package avinnovz.com.surveyadmin.activities.Home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import avinnovz.com.surveyadmin.R
import avinnovz.com.surveyadmin.adapters.ViewPagerAdapter
import avinnovz.com.surveyadmin.base.BaseActivity
import avinnovz.com.surveyadmin.fragments.Groups.GroupsFragment
import avinnovz.com.surveyadmin.fragments.Members.MembersFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(GroupsFragment.newInstance())
        fragments.add(MembersFragment.newInstance())
        fragments.add(MembersFragment.newInstance())

        vp_screens?.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, fragments)
            offscreenPageLimit = fragments.size
        }

        bnv_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_groups -> {
                vp_screens.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_members -> {
                vp_screens.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_questionnaire -> {
                vp_screens.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
