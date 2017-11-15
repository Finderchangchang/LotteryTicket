package gy.lotteryticket.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gy.lotteryticket.R
import gy.lotteryticket.adapter.MainAdapter
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    companion object {
        var main: HomeActivity? = null
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        main = this
        var mAdapter = MainAdapter(supportFragmentManager)
        tab_pager.adapter = mAdapter
        //预加载页面的个数
        tab_pager!!.offscreenPageLimit = 4
        alphaIndicator!!.setViewPager(tab_pager)
    }

}
