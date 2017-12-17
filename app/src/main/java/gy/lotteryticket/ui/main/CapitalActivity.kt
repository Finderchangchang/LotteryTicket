package gy.lotteryticket.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gy.lotteryticket.R
import gy.lotteryticket.adapter.UserAdapter
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityBjCarBinding
import kotlinx.android.synthetic.main.activity_capital.*

class CapitalActivity : BaseActivity<ActivityBjCarBinding>() {
    override fun setLayoutId(): Int {
        return R.layout.activity_capital
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        var mAdapter = UserAdapter(supportFragmentManager)
        capital_vp!!.adapter = mAdapter
        capital_tl!!.setupWithViewPager(capital_vp)
    }
}
