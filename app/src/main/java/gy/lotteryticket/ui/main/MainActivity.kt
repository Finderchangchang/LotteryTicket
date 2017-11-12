package gy.lotteryticket.ui.main

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.text.TextUtils
import gy.lotteryticket.R
import gy.lotteryticket.adapter.MainAdapter
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.inten
import gy.lotteryticket.databinding.ActivityMainBinding
import gy.lotteryticket.method.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    /**
     * 设置资源布局
     */
    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        //var mAdapter = MainAdapter(supportFragmentManager)
        //tab_pager.adapter = mAdapter
        //预加载页面的个数
        //tab_pager!!.offscreenPageLimit = 4
        //alphaIndicator!!.setViewPager(tab_pager)
        main_tv1.setOnClickListener {
            var s = ""
            //tab_pager.currentItem = 0
            //  alphaIndicator!!.setTabCurrenItem(0)
        }
        btn.setOnClickListener {
            var s = ""

            //tab_pager.currentItem = 1
            //alphaIndicator!!.setTabCurrenItem(1)
        }
        main_tv2.setOnClickListener {
            var s = ""

            //tab_pager.currentItem = 1

            //alphaIndicator!!.setTabCurrenItem(1)
        }
        main_tv3.setOnClickListener {
            var s = ""

            //tab_pager.currentItem = 2

            //alphaIndicator!!.setTabCurrenItem(2)
        }
        main_tv4.setOnClickListener {
            var s = ""

            //tab_pager.currentItem = 3
            //alphaIndicator!!.setTabCurrenItem(3)
        }
    }


    /**
     * 检测权限
     * */
    private fun checkPermission(): Boolean {
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        } else {
            return true
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        checkPermission()
    }

    /**
     * 权限处理结果
     * */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 0) {

        } else if (requestCode == 1) {

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}
