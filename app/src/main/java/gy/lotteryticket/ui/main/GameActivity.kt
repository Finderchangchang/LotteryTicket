package gy.lotteryticket.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityBjCarBinding

class GameActivity : BaseActivity<ActivityBjCarBinding>() {
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
    }
    override fun setLayoutId(): Int {
        return R.layout.activity_game
    }

}
