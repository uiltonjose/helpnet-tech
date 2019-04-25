package com.helpnet.tech.ui.activities

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helpnet.tech.R
import com.helpnet.tech.ui.fragments.HomeFragment
import com.helpnet.tech.ui.fragments.InProgressFragment
import com.helpnet.tech.ui.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.bottom_navigation
import kotlinx.android.synthetic.main.toolbar.logo_toolbar
import kotlinx.android.synthetic.main.toolbar.tv_app_name

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        initToolbar()
        setTitleActionBar("")
        logo_toolbar.visibility = VISIBLE
        tv_app_name.visibility = VISIBLE

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
    }

    // FIXME For notifications.
    //    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    //        menuInflater.inflate(R.menu.menu_toolbar, menu)
    //        return true
    //    }
    //
    //    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    //        // TODO Missing handle notification icon
    //        return super.onOptionsItemSelected(item)
    //    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragmentSelected: Fragment? = when (item.itemId) {
            R.id.navigation_home -> HomeFragment()
            R.id.navigation_wip -> InProgressFragment()
            R.id.navigation_profile -> ProfileFragment()
            else -> null
        }

        fragmentSelected?.also {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it).commit()
        }

        true
    }
}
