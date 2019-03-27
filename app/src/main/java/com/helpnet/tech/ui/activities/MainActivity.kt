package com.helpnet.tech.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helpnet.tech.R
import com.helpnet.tech.ui.fragments.HomeFragment
import com.helpnet.tech.ui.fragments.InProgressFragment
import com.helpnet.tech.ui.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //TODO Missing handle notification icon
        print("teste miguel")
        return super.onOptionsItemSelected(item)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragmentSelected: Fragment? = when (item.itemId) {
            R.id.navigation_home -> HomeFragment()
            R.id.navigation_wip -> InProgressFragment()
            R.id.navigation_profile -> ProfileFragment()
            else -> null
        }

        print("teste miguel")

        fragmentSelected?.also {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it).commit()
        }

        true
    }

    private fun teste() {
        print("teste miguel")
    }

}
