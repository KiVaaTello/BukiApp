package com.kivaatello.fragments2

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kivaatello.fragments2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        val fragments: ArrayList<Fragment> = arrayListOf(
            DogFragment(),
            ProfesionesFragment()
        )

        viewPager.adapter = ViewPagerAdapter(fragments, this)

        TabLayoutMediator(tabLayout, viewPager) {
                tab, position ->
            tab.text = when(position){
                0 -> {"Dogs"}
                1 -> {"Professions"}
                else -> {throw Resources.NotFoundException("Position not found")}
            }
        }.attach()
    }


}