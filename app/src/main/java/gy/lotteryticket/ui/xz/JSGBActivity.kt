package gy.lotteryticket.ui.xz

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import com.google.gson.JsonArray
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityPcDdBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.*
import kotlinx.android.synthetic.main.ac_type2.*
import android.os.Handler
import android.view.View
import android.widget.ListView
import com.arialyy.frame.util.AndroidUtils
import com.arialyy.frame.util.DensityUtils.dp2px
import com.zyyoona7.lib.EasyPopup
import com.zyyoona7.lib.HorizontalGravity
import com.zyyoona7.lib.VerticalGravity
import gd.mmanage.config.sp
import gy.lotteryticket.ui.WebActivity
import gy.lotteryticket.ui.capital.GZInfoActivity
import gy.lotteryticket.ui.capital.JSZDListActivity
import gy.lotteryticket.ui.capital.KJResultListActivity
import gy.lotteryticket.ui.main.CapitalActivity
import gy.lotteryticket.ui.user.RecordActivity
import gy.lotteryticket.ui.user.TodayActivity
import kotlinx.android.synthetic.main.activity_js_gb.*
import org.json.JSONArray


/**
 * 江苏骰宝
 * */
class JSGBActivity : BaseActivity<ActivityPcDdBinding>(), AbsModule.OnCallback {

    var left_list: ArrayList<String> = ArrayList<String>()
    var right_all_list: ArrayList<ArrayList<XZModel>> = ArrayList<ArrayList<XZModel>>()
    var left_adapter: CommonAdapter<String>? = null
    var right_adapter: CommonAdapter<XZModel>? = null
    var right_list: ArrayList<XZModel> = ArrayList<XZModel>()
    var click_position = 0
    var xz_num = 0
    var item_click_list: ArrayList<String> = ArrayList<String>()
    var xz_list: ArrayList<XZModel> = ArrayList<XZModel>()//下注列表
    var cz_id = "10"
    var now_qh = ""//当前期号
    var data_ftime = ""//封盘秒数
    var now_position = ""//当前彩种
    var title_adapter: CommonAdapter<String>? = null
    var title_list: ArrayList<String> = ArrayList()
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.xz -> {//加载数据
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    var model = Gson().fromJson<PCDDModel>(success.obj!![0].toString(), PCDDModel::class.java)
                    var list = model.dataGroup
                    right_all_list = ArrayList()
                    left_list = ArrayList()
                    if (list.size > 0) {
                        var a = 0
                        for (key in list) {
                            item_click_list.add("")
                            left_list.add(key.name)
                            if (a == 0 && cz_id == "1") {
                                var list = key.dataContent as ArrayList<XZModel>
                                for (i in 0 until 10) {
                                    list.add(i * 6, XZModel(i.toString()))
                                }
                                right_all_list.add(list)
                            } else {
                                right_all_list.add(key.dataContent as ArrayList<XZModel>)
                            }
                            a++
                        }
                        left_adapter!!.refresh(left_list)
                        //加载默认数据
                        if (right_all_list.size > 0) {
                            ty2_title.text = left_list[0]
                            right_adapter!!.refresh(right_all_list[0])
                        }
                    }
                }
            }
            command.xz + 1 -> {//下注
                success as NormalRequest<JsonArray>
                if (success.code == 0) {
                    refreshUI()
                    toast("下注成功")
                } else {
                    toast("下注失败")
                }
            }
            command.xz + 2 -> {//切换AB盘
                success as NormalRequest<JSONArray>
                if (success.code == 0) {
                    toast("切换成功")

                    when (Utils.getCache(sp.pan_id)) {
                        "1" -> {
                            Utils.putCache(sp.pan_id, "2")
                            center_btn.text = "A盘"
                            right_btn.text = "B盘√"
                        }
                        "2" -> {
                            Utils.putCache(sp.pan_id, "1")
                            center_btn.text = "A盘√"
                            right_btn.text = "B盘"
                        }
                    }
                    control!!.get_tz(cz_id)
                    center_btn.isClickable = true
                    right_btn.isClickable = true
                } else {
                    toast("切换失败")
                }
            }
            command.xz + 3 -> {//获得彩种列表
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    for (key in success.obj!!.iterator()) {
                        var model = Gson().fromJson<CZModel>(key.toString(), CZModel::class.java)
                        if (model.title == "PC蛋蛋") {

                        }
                    }
                }
            }
            command.xz + 4 -> {//获得彩种顶部信息
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    var model = Gson().fromJson<KJResultModel>(success.obj!![0].toString(), KJResultModel::class.java)
                    can_run = true
                    fp_tv.visibility = View.GONE
                    bottom_ll.visibility = View.VISIBLE
                    top_qi_tv.text = model.lastNum + "期"
                    now_qh = model.kjNum//记录当前期号
                    item_can_click = true
                    next_qi_tv.text = now_qh + "期"
                    title_bar.center_str = model.title
                    data_ftime = model.data_ftime
                    kjtime = model.kjtime
                    title_list.clear()
                    for (key in model.lastKj.split(",")) {
                        if (!TextUtils.isEmpty(key)) {
                            if (key.substring(0) == "0" && key.length > 1) {
                                title_list.add(key.substring(1, key.length - 1))
                            } else {
                                title_list.add(key)
                            }
                        }
                    }
                    title_adapter!!.refresh(title_list)
                    handler.postDelayed(runnable, 1000)
                    //控制历史期数 显示
                    if (model.lastKj == null)
                        old_qi_ll.visibility = View.GONE
                    else
                        old_qi_ll.visibility = View.VISIBLE
                }
            }
            command.xz + 5 -> {//即时注单
                success as NormalRequest<ZDModel>
                if (success.code == 0 && success.obj != null) {
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
                    pop_list.add(PopModel("游戏规则", ""))
                    pop_list.add(PopModel("充值", ""))
                    pop_list.add(PopModel("提现", ""))
                    pop_list.add(PopModel("今天输赢", "(${success.obj!!.totalShuyingMoney})"))
                    lv.adapter = adapter
                    lv.setOnItemClickListener { parent, view, position, id ->
                        when (position) {
                            0 -> {//即时注单
                                startActivity(Intent(this, JSZDListActivity::class.java).putExtra("type", 0))
                            }//startActivity(Intent(this@GameActivity))
                            1 -> {
                                startActivity(Intent(this, TodayActivity::class.java))
                            }
                            2 -> {
                                startActivity(Intent(this, RecordActivity::class.java))
                            }
                            3 -> {//开奖结果
                                startActivity(Intent(this, KJResultListActivity::class.java).putExtra("position", cz_id))
                            }
                            4 -> {//游戏规则
                                startActivity(Intent(this, GZInfoActivity::class.java).putExtra("position", cz_id))
                            }
                            5 -> {//充值
                                startActivity(Intent(this, CapitalActivity::class.java).putExtra("position", "1"))
                            }
                            6 -> {//提现
                                startActivity(Intent(this, CapitalActivity::class.java).putExtra("position", "2"))
                            }
                            7 -> {//今日输赢
                                startActivity(Intent(this, TodayActivity::class.java))
                            }
                        }
                    }
                    adapter!!.refresh(pop_list)
                }
            }
        }
        dialog!!.dismiss()
    }

    /**
     * 刷新UI
     * */
    fun refreshUI() {
        for (i in 0 until item_click_list.size) {
            item_click_list[i] = ""
        }
        //计算当前下的注数
        xz_num = 0
        zhu_tv.text = xz_num.toString()
        right_adapter!!.refresh(right_all_list[click_position])
        left_adapter!!.refresh(left_list)
    }

    var can_run = true//执行循环操作
    var kjtime = ""
    var handler = Handler()
    var runnable: Runnable = object : Runnable {
        override fun run() {
            if (can_run) {
                if (kjtime != "") {
                    var time = Utils.getDatePoor(Utils.change_data(kjtime))
                    if (time == "00:00") {
                        can_run = false
                        next4_qi_tv.text = "开奖中"
                        control!!.get_zj_last(cz_id)//获得最新一期开奖信息
                    } else if (time.split(":").size == 3) {//被截取成3份，未开盘
                        next4_qi_tv.text = "未开盘"
                        can_run = true
                        item_can_click = false//禁止点击
                        fp_tv.visibility = View.VISIBLE
                        bottom_ll.visibility = View.GONE
                    } else {
                        var x_time = time.split(":")[0].toInt() * 60 + time.split(":")[1].toInt()
                        var fp_time = x_time - data_ftime.toInt()//封盘秒数
                        next4_qi_tv.text = time
                        //封盘时间
                        var left_time = (fp_time / 60).toString()
                        if (left_time.length == 1) {
                            left_time = "0" + left_time
                        }
                        var right_time = (fp_time % 60).toString()
                        if (right_time.length == 1) {
                            right_time = "0" + right_time
                        }
                        //封盘时间
                        if (left_time + ":" + right_time == "00:00" || (left_time + ":" + right_time).contains("-")) {
                            fp_tv.visibility = View.VISIBLE
                            bottom_ll.visibility = View.GONE
                            next2_qi_tv.text = "封盘中"
                            item_can_click = false
                        } else {
                            next2_qi_tv.text = left_time + ":" + right_time
                        }
                    }
                    handler.postDelayed(this, 1000)
                } else {
                    next2_qi_tv.text = "封盘中"
                }
            }
        }
    }

    override fun onError(result: Int, error: Any?) {
        dialog!!.dismiss()
    }

    var control: XZModule? = null
    fun getResource(imageName: String): Int {
        val ctx = baseContext
        var url = imageName
        if (!TextUtils.isEmpty(url) && url.substring(0, 1) == "0") {
            url = url.substring(1)
        }
        return resources.getIdentifier("s_" + url, "mipmap", ctx.packageName)
    }

    var title_right: View? = null

    var adapter: CommonAdapter<PopModel>? = null
    var pop_list: ArrayList<PopModel> = ArrayList()
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(XZModule::class.java, this)
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
        title_bar.setRightClick { v ->
            title_right = v
            control!!.get_dz_last("2")
        }
        title_adapter = object : CommonAdapter<String>(this, title_list, R.layout.item_kj_title) {
            override fun convert(holder: CommonViewHolder, model: String, position: Int) {
                when (cz_id) {
                    "1" -> {//重庆时时彩
                        holder.setBG(R.id.tv, R.drawable.cycle)
                        holder.setText(R.id.tv, model)
                        holder.setVisible(R.id.iv, false)
                    }
                    "10" -> {//江苏快3
                        holder.setGImage(R.id.iv, getResource(model))
                        holder.setVisible(R.id.tv, false)
                    }
                    "66" -> {//PC蛋蛋
                        holder.setBG(R.id.tv, R.drawable.cycle)
                        holder.setText(R.id.tv, model)
                        holder.setVisible(R.id.iv, false)
                    }
                    else -> {
                        holder.setGImage(R.id.iv, getResource(model))
                    }
                }
            }
        }
        top_jiang_gv.adapter = title_adapter
        dialog!!.setTitle(R.string.dialog_loading)
        dialog!!.show()
        cz_id = intent.getStringExtra("index")
        when (cz_id) {
            "1" -> {
                ty2_top_gv.numColumns = 6
            }
            else -> {
                ty2_top_gv.numColumns = 2
            }
        }
        ty2_top_gv.setOnItemClickListener { _, _, position, _ ->
            if (item_can_click) get_now_clicks(position)
        }
        left_adapter = object : CommonAdapter<String>(this, left_list, R.layout.item_left) {
            override fun convert(holder: CommonViewHolder, model: String, position: Int) {
                if (position == click_position) {
                    holder.setTextColor(R.id.title, R.color.white)
                    holder.setBGColor(R.id.total_rl, R.color.black)
                } else {
                    holder.setTextColor(R.id.title, R.color.black)
                    holder.setBGColor(R.id.total_rl, R.color.tm_white)
                }

                if (item_click_list[position].length > 2) {
                    holder.setVisible(R.id.iv, true)
                } else {
                    holder.setVisible(R.id.iv, false)
                }
                holder.setText(R.id.title, model)
            }
        }
        right_adapter = object : CommonAdapter<XZModel>(this, right_list, R.layout.item_type2) {
            override fun convert(holder: CommonViewHolder, model: XZModel, position: Int) {
                if (click_position == 1) {//特码的情况
                    holder.setBGColor(R.id.total_ll, R.color.colorPrimaryDark)
//                    holder.setTextColor(R.id.left_tv, R.color.tm_colorPrimaryDark)
                } else {
                    holder.setBGColor(R.id.total_ll, R.color.tm_white)
                    holder.setTextColor(R.id.left_tv, R.color.black)
                }
                when (cz_id) {
                    "1" -> {//重庆时时彩
                        holder.setText(R.id.left_tv, model.name)
                        try {
                            model.name.toInt()
                            holder.setBG(R.id.left_tv, R.drawable.cycle)
                        } catch (e: Exception) {
                            holder.setBG(R.id.left_tv, R.color.quan_tm_white)
                        }
                        if (model.odds != null && click_position == 0) {
                            holder.setVisible(R.id.left_tv, false)
                            holder.setText(R.id.right_tv, Utils.cut_all_0_num(model.odds))
                        } else {
                            if (model.odds != null) holder.setText(R.id.right_tv, Utils.cut_all_0_num(model.odds))
                            holder.setVisible(R.id.left_tv, true)
                        }
                    }
                    "10" -> {//江苏快3
                        holder.setVisible(R.id.left1_tv, false)
                        holder.setVisible(R.id.left2_tv, false)
                        holder.setVisible(R.id.left3_tv, false)
                        if (model.name.contains("_")) {
                            holder.setVisible(R.id.left_tv, false)
                            for (key in 0 until model.name.split("_").size) {
                                when (key) {
                                    0 -> {
                                        holder.setVisible(R.id.left1_tv, true)
                                        holder.setBG(R.id.left1_tv, getResource(model.name.split("_")[0]))
                                    }
                                    1 -> {
                                        holder.setVisible(R.id.left2_tv, true)
                                        holder.setBG(R.id.left2_tv, getResource(model.name.split("_")[1]))
                                    }
                                    2 -> {
                                        holder.setVisible(R.id.left3_tv, true)
                                        holder.setBG(R.id.left3_tv, getResource(model.name.split("_")[2]))
                                    }
                                }
                            }
                        } else {
                            try {
                                model.name.toInt()
                                holder.setVisible(R.id.left_tv, false)
                                holder.setVisible(R.id.left1_tv, true)
                                holder.setBG(R.id.left1_tv, getResource(model.name))
                            } catch (e: Exception) {
                                holder.setText(R.id.left_tv, model.name)
                                holder.setBG(R.id.left_tv, R.color.quan_tm_white)
                            }
                        }
                        holder.setText(R.id.right_tv, Utils.cut_all_0_num(model.odds))
                    }
                    "66" -> {//PC蛋蛋
                        holder.setText(R.id.left_tv, model.name)
                        try {
                            model.name.toInt()
                            holder.setBG(R.id.left_tv, R.drawable.cycle)
                        } catch (e: Exception) {
                            holder.setBG(R.id.left_tv, R.color.quan_tm_white)
                        }
                        holder.setText(R.id.right_tv, Utils.cut_all_0_num(model.odds))
                    }
                }
                var clicks = item_click_list[click_position]
                if (clicks.contains("," + position.toString() + ",")) {//被点击
                    holder.setBGColor(R.id.total_ll, R.color.tm_pressed_color)
                } else {
                    holder.setBGColor(R.id.total_ll, R.color.tm_white)
                }
            }
        }
        left_lv.adapter = left_adapter
        left_lv.setOnItemClickListener { parent, view, position, id ->
            if (click_position != position) {//两个位置不相同 执行点击操作
                click_position = position
                if (click_position == 0 && cz_id == "1") {
                    ty2_top_gv.numColumns = 6
                } else {
                    ty2_top_gv.numColumns = 2
                }
                ty2_title.text = left_list[click_position]
                left_adapter!!.refresh(left_list)
                if (right_all_list.size > position) {
                    right_adapter!!.refresh(right_all_list[position])
                }
            }
        }
        ty2_top_gv.adapter = right_adapter
        when (Utils.getCache(sp.pan_id)) {
            "1" -> {
                center_btn.text = "A盘√"
                right_btn.text = "B盘"
            }
            "2" -> {
                center_btn.text = "A盘"
                right_btn.text = "B盘√"
            }
        }
        //A盘
        center_btn.setOnClickListener {
            if (Utils.getCache(sp.pan_id) == "2") {
                center_btn.isClickable = false
                right_btn.isClickable = false
                control?.change_ab()
            }
        }
        //B盘
        right_btn.setOnClickListener {
            if (Utils.getCache(sp.pan_id) == "1") {
                center_btn.isClickable = false
                right_btn.isClickable = false
                control?.change_ab()
            }
        }
        left_btn.setOnClickListener {
            var tz = tz_et.text.toString().trim()
            if (TextUtils.isEmpty(tz)) {
                toast(resources.getString(R.string.toast_tz))
            } else if (tz.toInt() == 0) {
                toast(resources.getString(R.string.toast_zero))
            } else if (xz_num == 0) {
                toast(resources.getString(R.string.toast_wf))
            } else {
                xz_list = ArrayList()
                var str_list = ArrayList<String>()
                for (type1_index in 0 until item_click_list.size) {
                    var now_lists = item_click_list[type1_index]//当前选中的集合内容
                    if (now_lists.length > 2) {//确定有数值
                        now_lists = now_lists.substring(1, now_lists.length - 1)
                        for (type2 in now_lists.split(",")) {//根据逗号隔开获得当前选择的数据
                            var num = type2.toInt()
                            xz_list.add(right_all_list[type1_index][num])
                            str_list.add("【" + left_list[type1_index] + "-" + right_all_list[type1_index][num].name + "】 @"
                                    + right_all_list[type1_index][num].odds + "X" + tz_et.text.toString())
                        }
                    }
                }
                var dialog_list = arrayOfNulls<String>(str_list.size)
                for (i in 0 until str_list.size) {
                    dialog_list[i] = str_list[i]
                }
                builder.setTitle("下注清单")
                builder.setItems(dialog_list, null)
                builder.setNegativeButton("确定") { v, b ->
                    if (now_qh == "0") {
                        toast("下单失败，请重试")
                        control!!.get_tz(cz_id)//重新加载数据
                    } else {
                        control!!.get_xz(cz_id, now_qh, tz, (tz.toInt() * xz_num).toString(), xz_list)
                    }
                }
                builder.setPositiveButton("取消") { v, b -> }
                builder.show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        control!!.get_tz(cz_id)
        control!!.get_zj_last(cz_id)
    }

    var item_can_click = true//true:可以点击 false:不可以点击

    /**
     * 记录当前点击的item
     * @param position 当前点击的位置
     * */
    fun get_now_clicks(position: Int) {
        var clicks = item_click_list[click_position]
        if (TextUtils.isEmpty(clicks)) clicks = "," + clicks//如果数据为空加一个逗号 好判断
        if (clicks.isNotEmpty() && clicks.substring(0, 1) != ",") {
            clicks = "," + clicks
        }
        var result = ""
        if (clicks.contains("," + position.toString() + ",")) {
            result = clicks.replace("," + position.toString() + ",", ",")
        } else {
            result = clicks + position.toString() + ","
        }
        if (result.isNotEmpty() && result.substring(0, 1) != ",") {
            result = "," + result
        }
        item_click_list[click_position] = result
        //计算当前下的注数
        xz_num = item_click_list.sumBy {
            if (it.length > 2) {
                it.substring(1, it.length - 1).split(",").size
            } else 0
        }
        zhu_tv.text = xz_num.toString()

        right_adapter!!.refresh(right_all_list[click_position])
        left_adapter!!.refresh(left_list)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_js_gb
    }
}