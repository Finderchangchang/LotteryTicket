package gy.lotteryticket.ui.user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityMessageDetailBinding
import gy.lotteryticket.model.GGModel
import kotlinx.android.synthetic.main.activity_message_detail.*

/**
 *通知通告详情页面
 * */
class MessageDetailActivity : BaseActivity<ActivityMessageDetailBinding>() {
    override fun setLayoutId(): Int {
        return R.layout.activity_message_detail
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        var model = intent.getSerializableExtra("model") as GGModel
        notice_tv.text = model.content
    }

}
