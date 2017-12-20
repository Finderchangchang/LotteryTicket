package gy.lotteryticket.ui.user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import com.arialyy.frame.module.AbsModule
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityBjCarBinding
import gy.lotteryticket.databinding.ActivityChangePwdBinding
import kotlinx.android.synthetic.main.activity_change_pwd.*

class ChangePwdActivity : BaseActivity<ActivityChangePwdBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {

    }

    override fun onError(result: Int, error: Any?) {

    }

    var control: UserModule? = null
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(UserModule::class.java, this)
        save_btn.setOnClickListener {
            var old = old_et.text.toString().trim()
            var new = new_et.text.toString().trim()
            var new_again = new_again_et.text.toString().trim()
            if (check_null(old)) {
                toast("旧密码不能为空")
            } else if (check_null(new) || new != new_again) {
                toast("请输入正确的新密码")
            } else {
                control!!.change_pwd(new)
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

