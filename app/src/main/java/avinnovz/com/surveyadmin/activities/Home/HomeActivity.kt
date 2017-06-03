package avinnovz.com.surveyadmin.activities.Home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import avinnovz.com.surveyadmin.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


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
