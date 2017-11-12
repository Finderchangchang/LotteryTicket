package gy.lotteryticket.ui

import android.os.Bundle
import com.arialyy.frame.module.AbsModule

import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import android.text.TextUtils
import com.jaeger.library.StatusBarUtil
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.control.LoginModule
import gy.lotteryticket.databinding.ActivityLoginBinding
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.ui.main.MainActivity
import net.tsz.afinal.view.LoadingDialog


class LoginActivity : BaseActivity<ActivityLoginBinding>(), AbsModule.OnCallback {
    private var control: LoginModule? = null
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(LoginModule::class.java, this)//初始化网络请求
        StatusBarUtil.setTransparent(this)//设置状态栏颜色
        //登录按钮
        login_btn.setOnClickListener {
            var name = id_et.text.toString().trim()
            var pwd = pwd_et.text.toString().trim()
            when {
                TextUtils.isEmpty(name) -> toast("请输入用户名")
                TextUtils.isEmpty(pwd) -> toast("请输入密码")
                else -> {
                    control!!.user_login(name, pwd)//登录操作
                }
            }
        }
        control!!.check_version()//检查更新
    }

    /**
     * 网络访问成功处理
     * */
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            1000 -> {//登录接口
                success as NormalRequest<String>
                when (success.code) {
                    1 -> {//跳转到主页
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                    2 -> {//跳转到绑定code页面
                        //startActivity(Intent(this@LoginActivity, DownHotelActivity::class.java))
                    }
                }
                toast(success.obj)
            }
            10001 -> {
                success as NormalRequest<String>
                if (success.result) {//提示更新

                }
            }
        }
    }

    /**
     * 网络访问失败处理
     * */
    override fun onError(result: Int, error: Any?) {

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_login
    }
}
