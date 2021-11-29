package com.mehmetboluk.newsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mehmetboluk.newsapp.R
import com.mehmetboluk.newsapp.databinding.ActivityMainBinding
import com.mehmetboluk.newsapp.view.fragments.FavoriteFragment
import com.mehmetboluk.newsapp.view.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val favoriteFragment = FavoriteFragment(

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        /*
        replaceFragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> replaceFragment(homeFragment)
                R.id.favoriteFragment -> replaceFragment(favoriteFragment)
            }
            true
        }

         */
    }

   /*
    private fun replaceFragment(fragment : Fragment) {
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragment)
            transaction.commit()
        }
    }

    */


}