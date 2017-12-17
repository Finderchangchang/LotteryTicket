package gy.lotteryticket.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityHomeBinding

/**
 * 个人中心
 * */
class UserActivity : BaseActivity<ActivityHomeBinding>() {
    override fun setLayoutId(): Int {
        return R.layout.activity_user
    }
}
