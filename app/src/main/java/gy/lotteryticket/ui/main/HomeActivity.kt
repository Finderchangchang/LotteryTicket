package gy.lotteryticket.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.arialyy.frame.module.AbsModule
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.adapter.MainAdapter
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.control.MainModule
import gy.lotteryticket.databinding.ActivityHomeBinding
import gy.lotteryticket.method.GlideImageLoader
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.TagModel
import gy.lotteryticket.ui.WebActivity
import gy.lotteryticket.ui.xz.JSGBActivity
import gy.lotteryticket.ui.xz.PCDDActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<ActivityHomeBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            10000 -> {//加载配置数据
                success as NormalRequest<List<TagModel>>
                if (success.code == 0 && success.obj!!.isNotEmpty()) {
                    for (model in success.obj!!) {
                        if (model.type == "1") {
                            message_tv.text = model.con
                        }
                        Utils.putCache("con" + model.con, model.con)
                    }
                }
            }
        }
    }

    override fun onError(result: Int, error: Any?) {

    }

    var control: MainModule? = null

    override fun setLayoutId(): Int {
        return R.layout.activity_home
    }


    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(MainModule::class.java, this)
        control!!.get_jb_main()
        var mAdapter = MainAdapter(supportFragmentManager)
        tab_pager.adapter = mAdapter
        //预加载页面的个数
        tab_pager!!.offscreenPageLimit = 4
        //跳转到个人中心
        user_ll.setOnClickListener { startActivity(Intent(this, UserActivity::class.java)) }
        //跳转到登录页面
        login_tv.setOnClickListener { Utils.check_login(this) }
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
                    if (Utils.check_login(this)) startActivity(Intent(this, CapitalActivity::class.java).putExtra("position", ""))
                }
                3 -> {
                    //我的
                    if (Utils.check_login(this)) startActivity(Intent(this, UserActivity::class.java))
                }
            }
        })
        //重庆
        ll3.setOnClickListener { startActivity(Intent(this@HomeActivity, JSGBActivity::class.java).putExtra("index", "1")) }
        //北京赛车
        ll1.setOnClickListener { startActivity(Intent(this@HomeActivity, PCDDActivity::class.java).putExtra("index", "50")) }
        //幸运飞艇
        ll2.setOnClickListener { startActivity(Intent(this@HomeActivity, PCDDActivity::class.java).putExtra("index", "55")) }
        ll4.setOnClickListener { startActivity(Intent(this@HomeActivity, WebActivity::class.java).putExtra("index", 0)) }//在线客服
        ll5.setOnClickListener { startActivity(Intent(this@HomeActivity, WebActivity::class.java).putExtra("index", 1)) }//聊天室
        ll6.setOnClickListener { startActivity(Intent(this@HomeActivity, WebActivity::class.java).putExtra("index", 2)) }//电脑版本
        init_title_imgs()
    }

    /**
     * 加载顶部图片轮播
     * */
    fun init_title_imgs() {
        var list: ArrayList<Int> = ArrayList()
        list.add(R.mipmap.title_main)
        banner!!.setImages(list)
        banner!!.setImageLoader(GlideImageLoader())
        banner!!.start()
    }

    override fun onResume() {
        super.onResume()
        if (TextUtils.isEmpty(Utils.getCache(sp.user_id))) {
            login_tv.visibility = View.VISIBLE
            user_ll.visibility = View.GONE
        } else {
            login_tv.visibility = View.GONE
            user_ll.visibility = View.VISIBLE
            main_tv_username.text = Utils.getCache(sp.login_name)
        }
    }
}
