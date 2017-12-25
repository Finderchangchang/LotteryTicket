package gy.lotteryticket.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import com.google.gson.JsonArray
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityGameBinding
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.CZModel
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.ui.xz.*
import kotlinx.android.synthetic.main.activity_game.*
import org.json.JSONArray

class GameActivity : BaseActivity<ActivityGameBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.xz + 3 -> {
                success as NormalRequest<JsonArray>
                if (success.obj != null) {
                    array_list.clear()
                    for (index in 0 until success.obj!!.size()) {
                        array_list.add(Gson().fromJson(success.obj!![index].toString(), CZModel::class.java))
                        when (index) {
                            0 -> binding.title1Ll = array_list[0].title
                            1 -> binding.title2Ll = array_list[1].title
                            2 -> binding.title3Ll = array_list[2].title
                            3 -> binding.title4Ll = array_list[3].title
                            4 -> binding.title5Ll = array_list[4].title
                            5 -> binding.title6Ll = array_list[5].title
                        }
                    }
                    handler.postDelayed(runnable, 1000)
                }
            }
        }
    }

    var control: XZModule? = null
    var array_list: ArrayList<CZModel> = ArrayList()
    override fun onError(result: Int, error: Any?) {

    }

    var handler = Handler()
    var runnable: Runnable = object : Runnable {
        override fun run() {
            for (index in 0 until array_list.size) {
                var t = array_list[index].kjtime
                var time_result = "未开盘"
                if (t != "0") {
                    var time = Utils.getDatePoor(Utils.change_data(t))
                    if (time == "00:00") {
                        time_result = "开奖中"
                        control?.get_cz_list()
                    } else {
                        time_result = time
                    }
                }
                when (index) {
                    0 -> binding.time1Ll = time_result
                    1 -> binding.time2Ll = time_result
                    2 -> binding.time3Ll = time_result
                    3 -> binding.time4Ll = time_result
                    4 -> binding.time5Ll = time_result
                    5 -> binding.time6Ll = time_result
                }
            }


            handler.postDelayed(this, 1000)
        }
    }


    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        ll1.setOnClickListener { startActivity(Intent(this@GameActivity, PCDDActivity::class.java).putExtra("index", "50")) }
        ll2.setOnClickListener { startActivity(Intent(this@GameActivity, CCsscActivity::class.java)) }
        //pc蛋蛋点击事件
        ll3.setOnClickListener { startActivity(Intent(this@GameActivity, JSGBActivity::class.java).putExtra("index", "66")) }
        ll4.setOnClickListener { startActivity(Intent(this@GameActivity, JSGBActivity::class.java).putExtra("index", "10")) }
        ll5.setOnClickListener { startActivity(Intent(this@GameActivity, XYFTActivity::class.java)) }
        ll6.setOnClickListener { startActivity(Intent(this@GameActivity, LHCActivity::class.java)) }
        control = getModule(XZModule::class.java, this)
        control?.get_cz_list()
        message_tv.text = Utils.getCache(sp.con1)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_game
    }

}
