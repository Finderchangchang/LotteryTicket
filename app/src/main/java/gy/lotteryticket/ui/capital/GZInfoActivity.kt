package gy.lotteryticket.ui.capital

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arialyy.frame.module.AbsModule
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityGameBinding
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.TagModel
import kotlinx.android.synthetic.main.activity_gzinfo.*

class GZInfoActivity : BaseActivity<ActivityGameBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.xz + 6 -> {
                success as NormalRequest<ArrayList<TagModel>>
                if (success.code == 0 && success.obj != null && success.obj!!.size > 0) {
                    gz_tv.text = success.obj!![0].con
                }
            }
        }
    }

    override fun onError(result: Int, error: Any?) {
        var s = ""
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_gzinfo
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        var id = intent.getStringExtra("position")
        getModule(XZModule::class.java, this).get_gz_info(id)
    }
}
