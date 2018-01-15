package gy.lotteryticket.ui.user

import android.content.Intent
import android.os.Bundle
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityUserBinding
import gy.lotteryticket.method.Utils
import kotlinx.android.synthetic.main.activity_my_data.*

/**
 * 个人资料
 * Created by JX on 2017/12/21.
 */

class UserDataActivity : BaseActivity<ActivityUserBinding>() {
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        name_tv.text = Utils.getCache(sp.login_name)
        yue_tv.text = intent.getStringExtra(sp.coin)//余额
        id_name_tv.text = Utils.getCache(sp.login_name)
        real_name_tv.text = intent.getStringExtra(sp.name)//真实姓名
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_my_data
    }
}
