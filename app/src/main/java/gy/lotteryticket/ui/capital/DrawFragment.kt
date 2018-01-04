package gy.lotteryticket.ui.capital

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.adapter.UserAdapter
import gy.lotteryticket.base.BaseFragment
import gy.lotteryticket.control.CUserModule
import gy.lotteryticket.databinding.FragCapitalBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.BankModel
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.ObjectRequest
import gy.lotteryticket.ui.main.CapitalActivity
import gy.lotteryticket.ui.main.HomeActivity
import gy.lotteryticket.ui.user.AddBankActivity
import kotlinx.android.synthetic.main.frag_draw.*

/**
 * 取款
 * Created by Administrator on 2017/11/11.
 */

class DrawFragment : BaseFragment<FragCapitalBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            CUserModule.GETBANK -> {
                val res = success as NormalRequest<JsonArray>
                val type = object : TypeToken<List<BankModel>>() {

                }.type
                if (res.obj != null) {
                    val data = Gson().fromJson<List<BankModel>>(res.obj, type)
                    if (data.size > 0) {
                        bank = data[0]
                        btn_add.visibility = View.GONE
                        card_info_et.setText(data[0].bankName + " " + data[0]!!.account + " " + data[0].username)
                    } else {
                        btn_add.visibility = View.VISIBLE
                    }
                }
            }
            CUserModule.GETMONEY -> {
                success as NormalRequest<ObjectRequest<String>>
                if (success.code == 0) {
                    CapitalActivity.context!!.toast(success.message)
                } else {
                    CapitalActivity.context!!.toast(success.message)
                }
            }
        }
        clear_et()
    }

    var bank: BankModel? = null
    override fun onError(result: Int, error: Any?) {
        clear_et()
    }

    override fun initView(view: View?) {

    }

    fun clear_et() {
        commit_btn.isClickable = true
        qk_je_et.setText("")
        qk_pwd_et.setText("")
    }

    var control: CUserModule? = null
    override fun init(savedInstanceState: Bundle?) {
        fs_cb.setOnCheckedChangeListener { _, b ->
            if (b) {
                db_tv.visibility = View.GONE
                card_info_et.visibility = View.GONE
            } else {
                db_tv.visibility = View.VISIBLE
                card_info_et.visibility = View.VISIBLE
            }
        }
        btn_add.setOnClickListener { startActivity(Intent(CapitalActivity.context, AddBankActivity::class.java)) }
        control = getModule(CUserModule::class.java, this)
        control!!.getBankList(1)
        //提交申请记录
        commit_btn.setOnClickListener {
            var qk = qk_je_et.text.toString().trim()
            var qk_pwd = qk_pwd_et.text.toString().trim()
            if (TextUtils.isEmpty(qk)) {
                CapitalActivity.context!!.toast("请输入取款金额")
            } else if (qk.toDouble() < 100) {
                CapitalActivity.context!!.toast("取款金额不能小于100元")
            } else if (qk.toDouble() > 1000000) {
                CapitalActivity.context!!.toast("取款金额不能大于1000000元")
            } else {
                commit_btn.isClickable = false
                if (fs_cb.isChecked) {
                    control!!.getMoney(2, "", "", Utils.getCache(sp.login_name), Utils.string2MD5(qk_pwd), qk)
                } else {
                    control!!.getMoney(1, bank!!.bankId, bank!!.account, Utils.getCache(sp.login_name), Utils.string2MD5(qk_pwd), qk)
                }
            }
        }
    }


    /**
     * 延时加载
     */
    override fun onDelayLoad() {

    }

    override fun setLayoutId(): Int {
        return R.layout.frag_draw
    }


    override fun dataCallback(result: Int, obj: Any) {

    }

    companion object {

    }
}