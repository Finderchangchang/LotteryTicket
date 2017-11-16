package gy.lotteryticket.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import gy.lotteryticket.ui.main.CapitalFragment
import gy.lotteryticket.ui.main.GameFragment
import gy.lotteryticket.ui.main.MainFragment
import gy.lotteryticket.ui.main.UserFragment

/**
 * Created by Administrator on 2017/11/11.
 */

class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    internal var fragment = arrayOf<Fragment>(MainFragment(), GameFragment(), CapitalFragment(), UserFragment())

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "存款"
            1 -> "取款"
            2 -> "存款记录"
            else -> "取款记录"
        }
    }
}
