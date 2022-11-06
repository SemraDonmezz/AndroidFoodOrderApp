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
        tasarim=ActivitiyMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

//        tasarim.bottomNavigation.setOnItemSelectedListener{
//            when(it.itemId){
//                R.id.foodMainFragment -> sayfaGec(FoodMainFragment())
//                R.id.foodCartFragment -> sayfaGec(FoodCartFragment())
//            }
//            true
//        }
    }
    fun sayfaGec(fragment:Fragment){
        val fragmentManager=supportFragmentManager.beginTransaction()
       fragmentManager.replace(R.id.fragment_host,fragment)
        fragmentManager.commit()
    }
}
