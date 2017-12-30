package gy.lotteryticket.ui.user

import android.os.Bundle
import android.text.TextUtils
import com.arialyy.frame.module.AbsModule
import gd.mmanage.config.sp

import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityQupwdBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.ObjectRequest
import kotlinx.android.synthetic.main.activity_qupwd.*

/**
 * 取款密码
 * Created by JX on 2017/12/21.
 */

class QuPwdActivity : BaseActivity<ActivityQupwdBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        success as NormalRequest<ObjectRequest<String>>
        if (success.code == 0) {
            toast("修改成功")
            Utils.putCache(sp.pwd, new_again_et.text.toString().trim())
            finish()
        } else {
            toast("修改失败")
        }
    }

    override fun onError(result: Int, error: Any?) {
        toast("修改失败")
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_qupwd
    }

    var control: UserModule? = null
    var old_pwd_have = true
    override fun init(savedInstanceState: Bundle) {
        super.init(savedInstanceState)
        control = getModule(UserModule::class.java, this)
//        if (TextUtils.isEmpty(Utils.getCache(sp.coinPassword))) {
//            old_et.isEnabled = false
//            old_pwd_have = false
//        }
        save_btn.setOnClickListener {
            var old = old_et.text.toString().trim()
            var new = new_et.text.toString().trim()
            var new_again = new_again_et.text.toString().trim()
            if (check_null(old)&&old_pwd_have) {
                toast("旧密码不能为空")
            } else if (check_null(new) || new != new_again) {
                toast("请输入正确的新密码")
            } else {
                control!!.change_qu_pwd(new)
            }
        }
    }

    fun check_null(et: String): Boolean {
        if (TextUtils.isEmpty(et)) {
            return true
        }
        return false
    }
}
