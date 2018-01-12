package gy.lotteryticket.ui.user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import com.arialyy.frame.module.AbsModule
import gd.mmanage.callback.LzyResponse
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityBjCarBinding
import gy.lotteryticket.databinding.ActivityChangePwdBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.ObjectRequest
import kotlinx.android.synthetic.main.activity_change_pwd.*
import kotlinx.android.synthetic.main.activity_login.*

class ChangePwdActivity : BaseActivity<ActivityChangePwdBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        success as NormalRequest<ObjectRequest<String>>
        if (success.code == 0) {
            toast("修改成功")
            when (position) {
                "2" -> {
                    Utils.putCache(sp.pwd, new_again_et.text.toString().trim())
                }
                "1" -> {
                    Utils.putCache(sp.coinPassword, new_again_et.text.toString().trim())
                }
            }
            finish()
        } else {
            toast("修改失败")
        }
    }

    override fun onError(result: Int, error: Any?) {
        toast("修改失败")
    }

    var control: UserModule? = null
    var position = "1"
    var old_pwd_have = true

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        position = intent.getStringExtra("position")

        when (position) {
            "1" -> {
                title_bar.setCentertv("取款密码")
                if (TextUtils.isEmpty(Utils.getCache(sp.coinPassword))) {
                    old_et.isEnabled = false
                    old_pwd_have = false
                }
            }
            "2" -> {
                title_bar.setCentertv("登录密码")
            }
        }
        control = getModule(UserModule::class.java, this)
        save_btn.setOnClickListener {
            var old = old_et.text.toString().trim()
            var new = new_et.text.toString().trim()
            var new_again = new_again_et.text.toString().trim()
            var oldpsw = Utils.getCache(sp.pwd)
            var zhipsw = Utils.getCache(sp.coinPassword)
            when (position) {
                "1" -> {
                    if (check_null(old) && old_pwd_have) {
                        toast("旧密码不能为空")
                    } else if (!Utils.string2MD5(old).equals(zhipsw)) {
                        toast("旧密码错误")
                    } else if (check_null(new) || new != new_again) {
                        toast("请输入正确的新密码")
                    } else {
                        control!!.change_qu_pwd(new)
                    }
                }
                "2" -> {
                    if (check_null(old)) {
                        toast("旧密码不能为空")
                    } else if (!oldpsw.equals(old)) {
                        toast("旧密码错误")
                    } else if (check_null(new) || new != new_again) {
                        toast("请输入正确的新密码")
                    } else {
                        control!!.change_pwd(new)
                    }
                }
            }
        }
    }

    fun check_null(et: String): Boolean {
        if (TextUtils.isEmpty(et)) {
            return true
        }
        return false
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_change_pwd
    }

}

