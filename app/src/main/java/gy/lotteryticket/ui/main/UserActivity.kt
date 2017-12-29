package gy.lotteryticket.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import com.google.gson.JsonArray
import gd.mmanage.config.sp

import net.tsz.afinal.view.TitleBar

import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.MainModule
import gy.lotteryticket.databinding.ActivityUserBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.UserModel
import gy.lotteryticket.ui.user.BankActivity
import gy.lotteryticket.ui.user.LoginPwdActivity
import gy.lotteryticket.ui.user.MessageActivity
import gy.lotteryticket.ui.user.QuPwdActivity
import gy.lotteryticket.ui.user.RecordActivity
import gy.lotteryticket.ui.user.TodayActivity
import gy.lotteryticket.ui.user.UserDataActivity
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : BaseActivity<ActivityUserBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.login + 1 -> {//登录接口
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    user = Gson().fromJson<UserModel>(success.obj!![0].toString(), UserModel::class.java)
                    name_tv.text = "账号：" + Utils.getCache(sp.login_name)
                    nick_name_tv.text = "昵称：" + user!!.nickname
                    yue_tv.text = user!!.coin
                    fs_yue_tv.text = user!!.coinFanShui
                }

            }
        }
    }

    override fun onError(result: Int, error: Any?) {

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_user
    }

    var user: UserModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title_bar.setRightClick {
            Utils.putCache(sp.user_id, "")
            Utils.putCache(sp.login_name, "")
            Utils.putCache(sp.pwd, "")
            Utils.putCache(sp.pan_id, "")
            Utils.putCache(sp.coin, "")
            finish()
        }
        getModule(MainModule::class.java, this).get_user_login(Utils.getCache(sp.login_name), Utils.getCache(sp.pwd), 2)//登录操作
        llay_data.setOnClickListener {
            startActivity(Intent(this, UserDataActivity::class.java)
                    .putExtra("yue", user!!.coin)
                    .putExtra("real_name", user!!.nickname))
        }
        llay_qu.setOnClickListener { startActivity(Intent(this, QuPwdActivity::class.java)) }
        llay_msg.setOnClickListener { startActivity(Intent(this, MessageActivity::class.java)) }
        llay_login_pwd.setOnClickListener { startActivity(Intent(this, LoginPwdActivity::class.java)) }
        llay_bank.setOnClickListener { startActivity(Intent(this, BankActivity::class.java)) }
        llay_today.setOnClickListener { startActivity(Intent(this, TodayActivity::class.java)) }
        llay_xiazhu.setOnClickListener { startActivity(Intent(this, RecordActivity::class.java)) }
        llay_money.setOnClickListener {
            startActivity(Intent(this, CapitalActivity::class.java).putExtra("position", ""))
            finish()
        }
    }
}