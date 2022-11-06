package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController

import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.ui.fragment.FoodCartFragment
import com.example.foodapp.ui.fragment.FoodMainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   // lateinit var  bottomNav:BottomNavigationView
    private lateinit var tasarim:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val navController = findNavController(R.id.bottomNavigation)
//        setupBottomNavMenu(navController)

//        loadFragment(FoodMainFragment())
//        bottomNav = findViewById(R.id.bottomNavigation) as BottomNavigationView
//        bottomNav.setOnNavi
//        }

//        tasarim.bottomNavigation.setOnItemSelectedListener{
//            when(it.itemId){
//                R.id.foodMainFragment -> sayfaGec(FoodMainFragment())
//                R.id.foodCartFragment -> sayfaGec(FoodCartFragment())
//            }
//            true
//        }
    }
//    private fun setupBottomNavMenu(navController: NavController) {
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
//        bottomNav?.setupWithNavController(navController)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(findNavController(R.id.bottomNavigation)) || super.onOptionsItemSelected(item)
//    }
//
//    private fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(com.google.android.material.R.id.container,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
    fun sayfaGec(fragment:Fragment){
        val fragmentManager=supportFragmentManager.beginTransaction()
       fragmentManager.replace(R.id.fragment_host,fragment)
        fragmentManager.commit()
    }
}