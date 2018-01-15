package gy.lotteryticket.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.jaeger.library.StatusBarUtil
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.LoginModule
import gy.lotteryticket.control.MainModule
import gy.lotteryticket.databinding.ActivityLoginBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.UserModel
import gy.lotteryticket.ui.user.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity<ActivityLoginBinding>(), AbsModule.OnCallback {
    private var control: MainModule? = null
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(MainModule::class.java, this)//初始化网络请求
        getModule(LoginModule::class.java, this).check_version()
        //登录按钮
        login_btn.setOnClickListener {
            var name = id_et.text.toString().trim()
            var pwd = pwd_et.text.toString().trim()
            when {
                TextUtils.isEmpty(name) -> toast("请输入用户名")
                TextUtils.isEmpty(pwd) -> toast("请输入密码")
                else -> {
                    control!!.get_user_login(name, pwd, 1)//登录操作
                }
            }
        }
        //注册按钮
        register_btn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    /**
     * 网络访问成功处理
     * */
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.login + 1 -> {//登录接口
                success as NormalRequest<JsonArray>
                var user: UserModel = UserModel()
                if (success.code == 0) {
                    if (success.obj != null && success.obj!!.size() > 0) {
                        user = Gson().fromJson(success.obj!![0], UserModel::class.java)
                        Utils.putCache(sp.user_id, user.uid)
                        Utils.putCache(sp.login_name, id_et.text.toString().trim())
                        Utils.putCache(sp.pwd, pwd_et.text.toString().trim())
                        Utils.putCache(sp.pan_id, user.panid)
                        Utils.putCache(sp.coin, user.coin)
                        Utils.putCache(sp.coinPassword,user.coinPassword)
                        Utils.putCache(sp.name,user.name)
                    }
                    finish()
                }else {
                    toast(success.message)
                }
            }
            command.xz -> {

            }
        }
    }

    /**
     * 网络访问失败处理
     * */
    override fun onError(result: Int, error: Any?) {
        var s = ""
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_login
    }
}
