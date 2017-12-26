package gy.lotteryticket.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.method.Utils
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    var index = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        title_bar.setLeftClick { finish() }
        //index = intent.getStringExtra("index")
        when (index) {
            "0" -> web.loadUrl(Utils.getCache(sp.con2))
            "1" -> web.loadUrl(Utils.getCache(sp.con3))
            "2" -> web.loadUrl(Utils.getCache(sp.con4))
        }
    }
}
