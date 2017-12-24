package gy.lotteryticket.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.yinglan.alphatabs.OnTabChangedListner
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.adapter.MainAdapter
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.control.MainModule
import gy.lotteryticket.databinding.ActivityHomeBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.TagModel
import gy.lotteryticket.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<ActivityHomeBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            10000 -> {
                success as NormalRequest<JsonArray>
                if (success.code == 0) {
                    if (success.obj != null && success.obj!!.size() > 0) {
                        for (model in 0 until success.obj!!.size()) {
                            var m = Gson().fromJson(success.obj!![model], TagModel::class.java)
                            when (model) {
                                0 -> Utils.putCache(sp.con1, m.con)
                                1 -> Utils.putCache(sp.con2, m.con)
                                2 -> Utils.putCache(sp.con3, m.con)
                                else -> Utils.putCache(sp.con4, m.con)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onError(result: Int, error: Any?) {

    }

    var control: MainModule? = null

    companion object {
        var main: HomeActivity? = null
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_home
    }


    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        main = this
        control = getModule(MainModule::class.java, this)
        control!!.get_jb_main()
        //title_bars.setLeftClick { if (Utils.check_login(this)) startActivity(Intent(this@HomeActivity, LoginActivity::class.java)) }
        var mAdapter = MainAdapter(supportFragmentManager)
        tab_pager.adapter = mAdapter
        //预加载页面的个数
        tab_pager!!.offscreenPageLimit = 4
        alphaIndicator!!.setViewPager(tab_pager)
        alphaIndicator!!.setOnTabChangedListner({ tabNum ->
            when (tabNum) {
                0 -> {
                    //首页
                }
                1 -> {
                    //游戏大厅
                    if (Utils.check_login(this)) startActivity(Intent(this, GameActivity::class.java))
                }
                2 -> {
                    //资金管理
                    if (Utils.check_login(this)) startActivity(Intent(this, CapitalActivity::class.java))
                }
                3 -> {
                    //我的
                    if (Utils.check_login(this)) startActivity(Intent(this, UserActivity::class.java))
                }
            }
        })
    }

}
