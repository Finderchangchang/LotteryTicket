package gy.lotteryticket.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gy.lotteryticket.R
import gy.lotteryticket.adapter.MainAdapter
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityDemoBinding
import kotlinx.android.synthetic.main.activity_demo.*

class DemoActivity : BaseActivity<ActivityDemoBinding>() {
    override fun setLayoutId(): Int {
        return R.layout.activity_demo
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        var mAdapter = MainAdapter(supportFragmentManager)
        tab_pager.adapter = mAdapter
        //预加载页面的个数
        tab_pager!!.offscreenPageLimit = 4
        alphaIndicator!!.setViewPager(tab_pager)
        btn.setOnClickListener {
            toast("666")
        }
    }

}
