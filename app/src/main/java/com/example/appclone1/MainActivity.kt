package com.example.appclone1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.appclone1.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val stylePref = this.getSharedPreferences("style", Context.MODE_PRIVATE).getString("style","GREEN")

        setNewTheme(Styles.valueOf(stylePref!!))
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.VISIBLE
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.action_runFragment_to_homeFragment)

        return super.onOptionsItemSelected(item)
    }

    private fun setNewTheme(theme: Styles) {
        when(theme) {
            Styles.PURPLE -> this.theme.applyStyle(R.style.Theme_Purple, true)
            Styles.GREEN -> this.theme.applyStyle(R.style.Theme_AppClone1, true)
        }

    }

}

enum class Styles {
    PURPLE,GREEN
}