package com.example.test7.presentation

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test7.R
import com.example.test7.databinding.ActivityMainBinding
import com.example.test7.databinding.NavHeaderBinding
import com.example.test7.presentation.model.DrawerMenuItem
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        setupDrawerMenu(navView)

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun setupDrawerMenu(navView: NavigationView) {
        val headerView = navView.getHeaderView(0)
        val headerBinding = NavHeaderBinding.bind(headerView)
        val drawerMenuAdapter = DrawerMenuAdapter { menuItem ->
            // Handle drawer menu item clicks here
        }

        val drawerMenuItems = listOf(
            DrawerMenuItem(1, R.drawable.ic_dashboard, "Dashboard", null),
            DrawerMenuItem(2, R.drawable.ic_messages, "Messages", 15),
            DrawerMenuItem(3, R.drawable.ic_notification, "Notifications", 20),
            DrawerMenuItem(4, R.drawable.ic_calendar, "Calendar", null),
            DrawerMenuItem(5, R.drawable.ic_statistics, "Statistics", null),
            DrawerMenuItem(6, R.drawable.ic_settings, "Settings", null),
            )

        headerBinding.drawerRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = drawerMenuAdapter
        }
        drawerMenuAdapter.submitList(drawerMenuItems)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
