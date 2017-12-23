package gy.lotteryticket.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityBjCarBinding
import gy.lotteryticket.databinding.ActivityGameBinding
import gy.lotteryticket.ui.xz.*
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : BaseActivity<ActivityGameBinding>() {
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        ll1.setOnClickListener { startActivity(Intent(this@GameActivity, BJCarActivity::class.java)) }
        ll2.setOnClickListener { startActivity(Intent(this@GameActivity, CCsscActivity::class.java)) }
        //pc蛋蛋点击事件
        ll3.setOnClickListener { startActivity(Intent(this@GameActivity, PCDDActivity::class.java)) }
        ll4.setOnClickListener { startActivity(Intent(this@GameActivity, JSGBActivity::class.java)) }
        ll5.setOnClickListener { startActivity(Intent(this@GameActivity, XYFTActivity::class.java)) }
        ll6.setOnClickListener { startActivity(Intent(this@GameActivity, LHCActivity::class.java)) }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_game
    }

}
