package gy.lotteryticket.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import com.google.gson.JsonArray
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.adapter.UserAdapter
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.MainModule
import gy.lotteryticket.databinding.ActivityBjCarBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.UserModel
import kotlinx.android.synthetic.main.activity_capital.*

class CapitalActivity : BaseActivity<ActivityBjCarBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.login + 1 -> {//登录接口
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    var user = Gson().fromJson<UserModel>(success.obj!![0].toString(), UserModel::class.java)
                    name_tv.text = Utils.getCache(sp.login_name)
                    yue_tv.text = user.coin+"RMB"
                    fs_yue_tv.text = "反水金额："+user.coinFanShui
                }

            }
        }
    }

    override fun onError(result: Int, error: Any?) {

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_capital
    }

    var position = ""
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        context = this
        position = intent.getStringExtra("position")
        var mAdapter = UserAdapter(supportFragmentManager)
        capital_vp!!.adapter = mAdapter
        capital_tl!!.setupWithViewPager(capital_vp)
        when (position) {
            "1" -> capital_vp!!.setCurrentItem(0, false)
            "2" -> capital_vp!!.setCurrentItem(1, false)
        }
        refresh1_iv.setOnClickListener {
            getModule(MainModule::class.java, this).get_user_login(Utils.getCache(sp.login_name), Utils.getCache(sp.pwd), 2)//登录操作
        }
        refresh_iv.setOnClickListener {
            getModule(MainModule::class.java, this).get_user_login(Utils.getCache(sp.login_name), Utils.getCache(sp.pwd), 2)//登录操作
        }
        getModule(MainModule::class.java, this).get_user_login(Utils.getCache(sp.login_name), Utils.getCache(sp.pwd), 2)//登录操作
    }

    companion object {
        var context: CapitalActivity? = null

    }
}
