package gy.lotteryticket.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.ListView
import com.arialyy.frame.module.AbsModule
import com.arialyy.frame.util.AndroidUtils
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.zyyoona7.lib.EasyPopup
import com.zyyoona7.lib.HorizontalGravity
import com.zyyoona7.lib.VerticalGravity
import gd.mmanage.config.sp
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityGameBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.CZModel
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.PopModel
import gy.lotteryticket.model.ZDModel
import gy.lotteryticket.ui.user.RecordActivity
import gy.lotteryticket.ui.user.TodayActivity
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
            command.xz + 5 -> {//即时注单
                success as NormalRequest<ZDModel>
                if (success.code == 0 && success.obj != null) {
                    var s = ""
                    var mQQPop: EasyPopup = EasyPopup(this).setContentView<EasyPopup>(R.layout.layout_right_pop)

                    mQQPop.setAnimationStyle<EasyPopup>(R.style.QQPopAnim)
                            .setFocusAndOutsideEnable<EasyPopup>(true)
                            .setBackgroundDimEnable<EasyPopup>(true)
                            .setWidth<EasyPopup>(AndroidUtils.dp2px(150))
                            .createPopup<EasyPopup>()

                    mQQPop.showAtAnchorView(title_right!!, VerticalGravity.BELOW, HorizontalGravity.LEFT, AndroidUtils.dp2px(30), 0)
                    var lv = mQQPop.getView<ListView>(R.id.pop_lv)
                    pop_list = ArrayList()
                    pop_list.add(PopModel("即时注单", "(${success.obj!!.totalMoney})"))
                    pop_list.add(PopModel("今日已结", ""))
                    pop_list.add(PopModel("下注记录", ""))
                    pop_list.add(PopModel("开奖结果", ""))
                    pop_list.add(PopModel("提现", ""))
                    lv.adapter = adapter
                    lv.setOnItemClickListener { parent, view, position, id ->
                        when (position) {
                            0 -> {//即时注单

                            }//startActivity(Intent(this@GameActivity))
                            1 -> {
                                startActivity(Intent(this, TodayActivity::class.java))
                            }
                            2 -> {
                                startActivity(Intent(this, RecordActivity::class.java))
                            }
                            3 -> {
                                //startActivity(Intent(this, TodayActivity::class.java))
                            }
                            4 -> {
                                startActivity(Intent(this@GameActivity, CapitalActivity::class.java).putExtra("position", "2"))
                            }
                        }
                    }
                    adapter!!.refresh(pop_list)
                }
            }
        }
    }

    var title_right: View? = null

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
    var adapter: CommonAdapter<PopModel>? = null
    var pop_list: ArrayList<PopModel> = ArrayList()
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        adapter = object : CommonAdapter<PopModel>(this, pop_list, R.layout.item_pop) {
            override fun convert(holder: CommonViewHolder, model: PopModel, position: Int) {
                holder.setText(R.id.title_tv, model.title)
                if (TextUtils.isEmpty(model.bottom)) {
                    holder.setVisible(R.id.bottom_tv, false)
                } else {
                    holder.setVisible(R.id.bottom_tv, true)
                    holder.setText(R.id.bottom_tv, model.bottom)
                }
            }
        }
        ll1.setOnClickListener { skip_position(array_list[0].id) }
        ll2.setOnClickListener { skip_position(array_list[1].id) }
        //pc蛋蛋点击事件
        ll3.setOnClickListener { skip_position(array_list[2].id) }
        ll4.setOnClickListener { skip_position(array_list[3].id) }
        ll5.setOnClickListener { skip_position(array_list[4].id) }
        ll6.setOnClickListener { skip_position(array_list[5].id) }
        control = getModule(XZModule::class.java, this)
        control?.get_cz_list()//获得彩种列表
        message_tv.text = Utils.getCache(sp.con1)
        //跳转到充值
        cz_btn.setOnClickListener {

        }
        //跳转到提现页面
        tx_btn.setOnClickListener { }
        title_bar.setRightClick { v ->
            title_right = v
            control!!.get_dz_last("2")
        }
        cz_btn.setOnClickListener {
            startActivity(Intent(this@GameActivity, CapitalActivity::class.java).putExtra("position", "1"))
        }
        tx_btn.setOnClickListener {
            startActivity(Intent(this@GameActivity, CapitalActivity::class.java).putExtra("position", "2"))
        }
    }

    fun dp2px(var0: Float): Int {
        val var1 = resources.displayMetrics.density
        return (var0 * var1 + 0.5f).toInt()
    }

    override fun onResume() {
        super.onResume()
        yue_tv.text = Utils.getCache(sp.coin)
    }

    fun skip_position(index: String) {
        when (index) {
        //重庆
            "1" -> startActivity(Intent(this@GameActivity, JSGBActivity::class.java).putExtra("index", "1"))
        //北京赛车
            "50" -> startActivity(Intent(this@GameActivity, PCDDActivity::class.java).putExtra("index", "50"))
        //PC蛋蛋
            "66" -> startActivity(Intent(this@GameActivity, JSGBActivity::class.java).putExtra("index", "66"))
        //快三
            "10" -> startActivity(Intent(this@GameActivity, JSGBActivity::class.java).putExtra("index", "10"))
        //幸运飞艇
            "55" -> startActivity(Intent(this@GameActivity, PCDDActivity::class.java).putExtra("index", "55"))
        //六合彩
            "70" -> startActivity(Intent(this@GameActivity, JSGBActivity::class.java).putExtra("index", "70"))
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_game
    }

}
