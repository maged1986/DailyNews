package com.megednan.dailynews.ui.activities

import android.os.Bundle
import android.view.Gravity.apply
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat.apply
import androidx.core.view.MenuItemCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.megednan.dailynews.R
import com.megednan.dailynews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private var navHostFragment: NavHostFragment? = null
    private var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        setSupportActionBar(binding.toolBar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment?
        val navController: NavController = navHostFragment!!.getNavController()
        val navView = findViewById<NavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(navView, navController)
        appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment, R.id.techFragment,
                R.id.cultureFragment, R.id.businessFragment, R.id.savedFragment, R.id.sportFragment, R.id.articleFragment).setDrawerLayout(binding.drawerLayout).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration!!)
        NavigationUI.setupWithNavController(navView, navController)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search_toolbar, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                val bundle=Bundle()
                bundle.putString("search item",s)
                navHostFragment?.findNavController()?.navigate(R.id.searchFragment,bundle)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}