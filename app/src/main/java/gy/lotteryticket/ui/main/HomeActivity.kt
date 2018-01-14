package gy.lotteryticket.ui.main

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import com.arialyy.frame.module.AbsModule
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.R
import gy.lotteryticket.adapter.MainAdapter
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.control.MainModule
import gy.lotteryticket.databinding.ActivityHomeBinding
import gy.lotteryticket.down.DownloadUtils
import gy.lotteryticket.down.UpdateManager
import gy.lotteryticket.method.GlideImageLoader
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.TagModel
import gy.lotteryticket.model.VersionModel
import gy.lotteryticket.ui.CJActivity
import gy.lotteryticket.ui.WebActivity
import gy.lotteryticket.ui.xz.JSGBActivity
import gy.lotteryticket.ui.xz.PCDDActivity
import kotlinx.android.synthetic.main.activity_home.*
import pub.devrel.easypermissions.EasyPermissions

class HomeActivity : BaseActivity<ActivityHomeBinding>(), AbsModule.OnCallback, EasyPermissions.PermissionCallbacks {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            10000 -> {//加载配置数据
                success as NormalRequest<List<TagModel>>
                if (success.code == 0 && success.obj!!.isNotEmpty()) {
                    for (model in success.obj!!) {
                        if (model.type == "1") {
                            message_tv.text = model.con
                        }
                        Utils.putCache("con" + model.type, model.con)
                    }
                }
            }
            10005 -> {//检查更新
                success as NormalRequest<VersionModel>
                try {
                    if (success.obj!!.version.replace(".", "").toInt() > Utils.version.replace(".", "").toInt()) {
                        down_apk(success.obj!!)
                    }
                } catch (e: Exception) {
                    if (Utils.version != success.obj!!.version) {
                        down_apk(success.obj!!)
                    }
                }
            }
        }
    }

    fun down_apk(model: VersionModel) {
        if (!EasyPermissions.hasPermissions(this@HomeActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(this@HomeActivity, "需要下载新的apk",
                    2, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            val builder = AlertDialog.Builder(this@HomeActivity)
            builder.setTitle("提示")
            builder.setMessage(model.content)
            builder.setNegativeButton("确定") { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    DownloadUtils(this).downloadAPK(model.download, "新版本Apk.apk")
                } else {
                    UpdateManager(this@HomeActivity).checkUpdateInfo(model.download)
                }
            }
            builder.setPositiveButton("取消") { _, _ -> finish() }
            builder.setCancelable(false)
            builder.show()
        }
    }

    override fun onError(result: Int, error: Any?) {

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    //成功
    override fun onPermissionsGranted(requestCode: Int, list: List<String>) {
        if (!EasyPermissions.hasPermissions(this@HomeActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(this@HomeActivity, "您需要允许以下权限,否则无法注册企业信息",
                    2, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            control!!.check_version()
        }
    }

    //失败
    override fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        if (!EasyPermissions.hasPermissions(this@HomeActivity,  Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(this@HomeActivity, "您需要允许以下权限",
                    2, Manifest.permission.READ_EXTERNAL_STORAGE);

        }
    }

    var control: MainModule? = null
    override fun setLayoutId(): Int {
        return R.layout.activity_home
    }


    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(MainModule::class.java, this)
        control!!.get_jb_main(0)
//        control!!.check_version()
        var mAdapter = MainAdapter(supportFragmentManager)
        tab_pager.adapter = mAdapter
        //预加载页面的个数
        tab_pager!!.offscreenPageLimit = 4
        //跳转到个人中心
        user_ll.setOnClickListener { startActivity(Intent(this, UserActivity::class.java)) }
        //跳转到登录页面
        login_tv.setOnClickListener {
            if (login_tv.text.equals("登录")) {
                Utils.check_login(this)
            } else {
                builder!!.setTitle("提示")
                builder!!.setMessage("确定要退出当前账号吗？")
                builder!!.setPositiveButton("确定") { a, b ->
                    Utils.putCache(sp.user_id, "")
                    Utils.putCache(sp.login_name, "")
                    Utils.putCache(sp.pwd, "")
                    Utils.putCache(sp.pan_id, "")
                    Utils.putCache(sp.coin, "")
                    finish()
                }
                builder!!.setNegativeButton("取消") { a, b -> }
                builder!!.show()
            }
        }
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
        ll3.setOnClickListener { if (Utils.check_login(this)) startActivity(Intent(this@HomeActivity, JSGBActivity::class.java).putExtra("index", "1")) }
        //北京赛车
        ll1.setOnClickListener { if (Utils.check_login(this)) startActivity(Intent(this@HomeActivity, PCDDActivity::class.java).putExtra("index", "50")) }
        //幸运飞艇
        ll2.setOnClickListener { if (Utils.check_login(this)) startActivity(Intent(this@HomeActivity, PCDDActivity::class.java).putExtra("index", "55")) }
        ll4.setOnClickListener { if (Utils.check_login(this)) startActivity(Intent(this@HomeActivity, WebActivity::class.java).putExtra("index", 0)) }//在线客服
        ll5.setOnClickListener { if (Utils.check_login(this)) startActivity(Intent(this@HomeActivity, WebActivity::class.java).putExtra("index", 1)) }//聊天室
        ll6.setOnClickListener { if (Utils.check_login(this)) startActivity(Intent(this@HomeActivity, CJActivity::class.java).putExtra("index", 7)) }//电脑版本
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
            login_tv.setText("登录")
            //login_tv.visibility = View.VISIBLE
            user_ll.visibility = View.GONE
        } else {
            //login_tv.visibility = View.GONE
            login_tv.setText("退出")
            user_ll.visibility = View.VISIBLE
            main_tv_username.text = Utils.getCache(sp.login_name)
        }
    }
}
