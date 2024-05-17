package kr.camp.youtube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.camp.youtube.databinding.ActivityMainBinding
import kr.camp.youtube.myVideo.state.MyVideoFragment
import kr.camp.youtube.view.search.SearchFragment

class  MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val whiteIcons = listOf(
        R.drawable.home_white,
        R.drawable.search_white,
        R.drawable.youtube_white
    )

    val blackIcons = listOf(
        R.drawable.home_black,
        R.drawable.search_black,
        R.drawable.youtube_black
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewPager()
    }

    //ViewPager2
    private fun viewPager() {
        val fragmentList = ArrayList<Fragment>().apply {
            add(HomeFragment())
            add(SearchFragment())
            add(MyVideoFragment())
        }

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(fragmentList, this@MainActivity)

        // ViewPager2 - TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(whiteIcons[position])
        }.attach()

        // TabLayout Click Event (Icon Change)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                for (i in 0 until tabLayout.tabCount) {
                    tabLayout.getTabAt(i)?.setIcon(getIconResource(i, position))
                }
            }
        })
    }

    private fun getIconResource(tabIndex: Int, currentPosition: Int): Int {
        return if (tabIndex == currentPosition) blackIcons[tabIndex] else whiteIcons[tabIndex]
        }
    }




