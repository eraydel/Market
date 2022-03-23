package com.dev.eraydel.market.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.eraydel.market.R
import androidx.fragment.app.Fragment
import com.dev.eraydel.market.databinding.ActivityMainBinding
import com.dev.eraydel.market.view.ui.fragments.food.FoodFragment
import com.dev.eraydel.market.view.ui.fragments.home.HomeFragment
import com.dev.eraydel.market.view.ui.fragments.products.ProductsFragment
import com.dev.eraydel.market.view.ui.fragments.services.ServicesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    // Navigation component
    private lateinit var bottomNavView: BottomNavigationView
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar)

        supportActionBar?.hide()
        bottomNavView = binding.bottomNavView

        val homeFragment = HomeFragment()
        val foodFragment = FoodFragment()
        val productsFragment = ProductsFragment()
        val servicesFragment = ServicesFragment()

        setThatFragment(homeFragment)

        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    setThatFragment(homeFragment)
                }
                R.id.food -> {
                    setThatFragment(foodFragment)
                }
                R.id.products ->  {
                    setThatFragment(productsFragment)
                }
                R.id.services -> {
                    setThatFragment(servicesFragment)
                }
            }
            true
        }

    }


    /*
    * setThatFragment
     */
    private fun setThatFragment( fragment: Fragment ) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame , fragment)
            commit()
        }


}