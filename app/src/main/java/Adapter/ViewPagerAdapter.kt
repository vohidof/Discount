package Adapter

import Fragment.FavouriteFragment
import Fragment.HomeFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    var list: ArrayList<String>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                FavouriteFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}