package com.vohidov.discount

import Adapter.ViewPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = ArrayList<String>()

        list.add("All")
        list.add("Favourite")

        adapter = ViewPagerAdapter(list, supportFragmentManager, lifecycle)

        view_pager.adapter = adapter

        TabLayoutMediator(tab_layout, view_pager){tab, position ->
            when(position){
                0 -> {
                    tab.text = list[0]
                }

                1 -> {
                    tab.text = list[1]
                }
            }
        }.attach()
    }
}