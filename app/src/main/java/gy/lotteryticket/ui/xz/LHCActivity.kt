package gy.lotteryticket.ui.xz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
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
import gy.lotteryticket.control.MainModule
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityPcDdBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.*
import gy.lotteryticket.ui.capital.GZInfoActivity
import gy.lotteryticket.ui.capital.JSZDListActivity
import gy.lotteryticket.ui.capital.KJResultListActivity
import gy.lotteryticket.ui.main.CapitalActivity
import gy.lotteryticket.ui.main.GameActivity
import gy.lotteryticket.ui.user.RecordActivity
import gy.lotteryticket.ui.user.TodayActivity
import kotlinx.android.synthetic.main.ac_type2.*
import kotlinx.android.synthetic.main.activity_lhc.*
import org.json.JSONArray

/**
 *PC蛋蛋(解决赛车的布局)
 * */
class LHCActivity : BaseActivity<ActivityPcDdBinding>(), AbsModule.OnCallback {
    var left_list: ArrayList<String> = ArrayList<String>()
    var right_all_list: ArrayList<ArrayList<LHCResult.DataGroupBean.DataTitleBean>> = ArrayList()
    var title_tab_list: ArrayList<ArrayList<PCDDModel.DataGroupBean.DataTitleBean>> = ArrayList()
    var left_adapter: CommonAdapter<String>? = null
    var right_adapter: CommonAdapter<XZModel>? = null
    var title_adapter: CommonAdapter<String>? = null
    var title_list: ArrayList<String> = ArrayList()
    var right_list: ArrayList<XZModel> = ArrayList()
    var click_position = 0
    var xz_num = 0
    var item_click_list: ArrayList<String> = ArrayList()
    var xz_list: ArrayList<XZModel> = ArrayList()//下注列表
    var cz_id = "10"
    var now_qh = ""//当前期号
    var data_ftime = ""//封盘秒数
    var tab_click = 0
    var hx_list: ArrayList<LHCResult.DataGroupBean.DataTitleBean> = ArrayList()//合肖的数据集合
    var zxbz_list: ArrayList<LHCResult.DataGroupBean.DataTitleBean> = ArrayList()//自选不中的数据集合

    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.xz -> {//加载数据
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    var model = Gson().fromJson<LHCResult>(success.obj!![0].toString(), LHCResult::class.java)
                    var list = model.dataGroup
                    left_list = ArrayList()
                    right_all_list = ArrayList()
                    title_tab_list = ArrayList()
                    if (list.size > 0) {
                        var a = 0
                        for (key in list) {
                            item_click_list.add("")
                            if (key.name == "合肖" || key.name == "自选不中") {
                                when (key.name) {
                                    "合肖" -> {
                                        hx_list = ArrayList()
                                        hx_list = key.dataTitle as ArrayList<LHCResult.DataGroupBean.DataTitleBean>
                                        var l = ArrayList<LHCResult.DataGroupBean.DataTitleBean>()
                                        var xz = ArrayList<XZModel>()
                                        for (i in 0 until 12) {
                                            xz.add(XZModel((i + 1).toString()))
                                        }
                                        var model = LHCResult.DataGroupBean.DataTitleBean()
                                        model.name = key.name
                                        model.dataContent = xz
                                        l.add(model)
                                        right_all_list.add(l)
                                    }
                                    else -> {
                                        zxbz_list = ArrayList()
                                        zxbz_list = key.dataTitle as ArrayList<LHCResult.DataGroupBean.DataTitleBean>
                                        var l = ArrayList<LHCResult.DataGroupBean.DataTitleBean>()
                                        var xz = ArrayList<XZModel>()
                                        for (i in 0 until 49) {
                                            xz.add(XZModel((i + 1).toString()))
                                        }
                                        var model = LHCResult.DataGroupBean.DataTitleBean()
                                        model.name = key.name
                                        model.dataContent = xz
                                        l.add(model)
                                        right_all_list.add(l)
                                    }
                                }

                            } else {
                                right_all_list.add(key.dataTitle as ArrayList<LHCResult.DataGroupBean.DataTitleBean>)
                            }
                            left_list.add(key.name)
                            //title_tab_list.add(key.dataTitle)
                            a++
                        }
                        left_adapter!!.refresh(left_list)
                        //加载默认数据
                        load_right(0)
                    }
                }
            }
            command.xz + 1 -> {//下注
                success as NormalRequest<JsonArray>
                if (success.code == 0) {
                    refreshUI()
                    toast("下注成功")
                    getModule(MainModule::class.java, this).get_user_login(Utils.getCache(sp.login_name), Utils.getCache(sp.pwd), 2)//登录操作
                } else {
                    toast("下注失败")
                }
            }
            command.login + 1 -> {//获得账户余额
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    var user = Gson().fromJson<UserModel>(success.obj!![0].toString(), UserModel::class.java)
                    yue_tv.text = "￥" + user.coin
                    Utils.putCache(sp.coin, user.coin)
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

                    mQQPop.showAtAnchorView(right99_iv!!, VerticalGravity.BELOW, HorizontalGravity.LEFT, AndroidUtils.dp2px(30), 0)
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
                            }
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
                                //startActivity(Intent(this, JSZDListActivity::class.java).putExtra("type", 1))
                                startActivity(Intent(this, TodayActivity::class.java))
                            }
                        }
                    }
                    adapter!!.refresh(pop_list)
                }
            }
            command.xz + 4 -> {//获得彩种顶部信息
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    var model = Gson().fromJson<KJResultModel>(success.obj!![0].toString(), KJResultModel::class.java)
                    can_run = true
                    fp_tv.visibility = View.GONE
                    bottom_ll.visibility = View.VISIBLE
                    item_can_click = true
                    top_qi_tv.text = model.lastNum + "期"
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

                    now_qh = model.kjNum//记录当前期号
                    next_qi_tv.text = now_qh + "期"
                    title_tv.text = model.title
                    data_ftime = model.data_ftime
                    kjtime = model.kjtime
                    handler.postDelayed(runnable, 1000)
                    //控制历史期数 显示
                    if (model.lastKj == null)
                        old_qi_ll.visibility = View.GONE
                    else
                        old_qi_ll.visibility = View.VISIBLE
                }
            }
        }
        dialog!!.dismiss()
    }

    var refresh_new = false//刷新最新数据
    var refresh_new_num = 0

    //和肖，自选不中
    var item_can_click = true//true:可以点击 false:不可以点击
    var can_run = true//执行循环操作
    var kjtime = ""
    var handler = Handler()
    var runnable: Runnable = object : Runnable {
        override fun run() {
            if (refresh_new) {
                if (refresh_new_num % 5 == 0) {//隔5秒请求一次
                    control!!.get_zj_last(cz_id)//获得最新一期开奖信息
                }
                refresh_new_num++
            }
            if (can_run) {
                var time = Utils.getDatePoor(Utils.change_data(kjtime))
                if (time == "00:00") {
                    can_run = false
                    next4_qi_tv.text = "开奖中"
                    refresh_new_num = 0//开始循环获得中奖结果
                    refresh_new = true
//                    control!!.get_zj_last(cz_id)//获得最新一期开奖信息
                } else if (time.split(":").size == 3) {//被截取成3份，未开盘
                    next4_qi_tv.text = "未开盘"
                    can_run = true
                    item_can_click = false//禁止点击
                    fp_tv.visibility = View.GONE
                    bottom_ll.visibility = View.VISIBLE
                } else {
                    var x_time = time.split(":")[0].toInt() * 60 + time.split(":")[1].toInt()
                    var fp_time = x_time - data_ftime.toInt()//封盘秒数
                    if (!TextUtils.isEmpty(time) && time.contains("-")) {
                        next4_qi_tv.text = "00:00"
                    } else {
                        next4_qi_tv.text = time
                    }
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
                        fp_tv.visibility = View.VISIBLE//封盘中，需要显示字
                        bottom_ll.visibility = View.GONE
                        item_can_click = false
                        next2_qi_tv.text = "封盘中"
                    } else {
                        next2_qi_tv.text = left_time + ":" + right_time
                    }
                }
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onError(result: Int, error: Any?) {
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
//        right_adapter!!.refresh(right_all_list[click_position][tab_click].dataTitle)
        left_adapter!!.refresh(left_list)
        if (title_tab_list[click_position].size > 0) {
            tab.visibility = View.VISIBLE
            for (m in title_tab_list[click_position]) {
                tab.addTab(tab.newTab().setText(m.name))
            }
        } else {
            tab.visibility = View.GONE
        }
    }

    var adapter: CommonAdapter<PopModel>? = null
    var pop_list: ArrayList<PopModel> = ArrayList()
    var control: XZModule? = null
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        yue_tv.text = "￥" + Utils.getCache(sp.coin)
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
        when (Utils.getCache(sp.pan_id)) {
            "2" -> {
                center_btn.text = "A盘"
                right_btn.text = "B盘√"
            }
            "1" -> {
                center_btn.text = "A盘√"
                right_btn.text = "B盘"
            }
        }
        dialog!!.setTitle(R.string.dialog_loading)
        dialog!!.show()
        cz_id = intent.getStringExtra("index")

        ty2_top_gv.setOnItemClickListener { _, _, position, _ ->
            //if (item_can_click)
            get_now_clicks(position)
        }
        ty2_top_gv.numColumns = 6
        title_adapter = object : CommonAdapter<String>(this, title_list, R.layout.item_title) {
            override fun convert(holder: CommonViewHolder, model: String, position: Int) {
                if (!TextUtils.isEmpty(model)) {
                    if (model[0].toString() == "0") {
                        holder.setGImage(R.id.iv, getResource(model[1].toInt()))
                    } else {
                        holder.setGImage(R.id.iv, getResource(model.toInt()))
                    }
                }
                holder.setText(R.id.tv, model)
            }
        }
        top_jiang_gv.adapter = title_adapter
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
                holder.setVisible(R.id.left_tv, false)
                holder.setVisible(R.id.right_tv, false)
                when (click_position) {
                    0, 2, 3, 4 -> {//左侧球右侧赔率
                        holder.setBGColor(R.id.total_ll, R.color.tm_white)
                        if (!TextUtils.isEmpty(model.name)) {
                            try {
                                var s = model.name.toInt()
                                holder.setBG(R.id.left_tv, getResource(s))
                            } catch (e: Exception) {
                                holder.setBGColor(R.id.left_tv, Color.TRANSPARENT)
                            }
                        }
                    }
                    else -> {
                        holder.setBGColor(R.id.left_tv, Color.TRANSPARENT)
                    }
                }
                holder.setText(R.id.left_tv, model.name)
                if (!TextUtils.isEmpty(model.odds)) {
                    holder.setText(R.id.right_tv, Utils.cut_all_0_num(model.odds))
                }
                var clicks = item_click_list[click_position]
                if (clicks.contains(",$tab_click_position:$position,")) {//被点击
                    holder.setBGColor(R.id.total_ll, R.color.tm_pressed_color)
                } else {
                    holder.setBGColor(R.id.total_ll, R.color.tm_white)
                }
            }
        }
        left_lv.adapter = left_adapter
        left_lv.setOnItemClickListener { parent, view, position, id ->
            if (click_position != position) {//两个位置不相同 执行点击操作

                load_right(position)
            }
        }
        left_lv.cacheColorHint = 0

        ty2_top_gv.adapter = right_adapter
        //A盘
        center_btn.setOnClickListener {
            if (Utils.getCache(sp.pan_id) != "1") {
                center_btn.isClickable = false
                right_btn.isClickable = false
                control?.change_ab()
            }
        }
        //B盘
        right_btn.setOnClickListener {
            if (Utils.getCache(sp.pan_id) != "2") {
                center_btn.isClickable = false
                right_btn.isClickable = false
                control?.change_ab()
            }
        }
        //加载左侧弹框
        left_ll.setOnClickListener {
            load_left()
        }
        //加载右侧弹框
        right99_iv.setOnClickListener {
            control!!.get_dz_last("2")
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
                xz_list.clear()
                var str_list = ArrayList<String>()
                for (type1_index in 0 until item_click_list.size) {
                    var now_lists = item_click_list[type1_index]//当前选中的集合内容
                    if (now_lists.length > 2) {//确定有数值
                        now_lists = now_lists.substring(1, now_lists.length - 1)
                        when (type1_index) {
                            10 -> {//合肖
                                val n = now_lists.length - now_lists.replace(":", "").length//判断当前包含几个冒号
                                if (n in 2..11) {
                                    var model = zxbz_list[n - 2].dataContent[0]
                                    var bet = ""
                                    for (key in now_lists.split(",")) {
                                        bet += (key.replace(":", "").toInt() + 1).toString() + ","
                                    }
                                    if (bet.isNotEmpty()) {
                                        bet = bet.substring(0, bet.length - 1)
                                    }
                                    model.betInfo = bet
                                    xz_list.add(model)
                                    str_list.add("【" + left_list[type1_index] + "-" + model.name + "】 @"
                                            + model.odds + "X" + tz_et.text.toString())
                                }
                            }
                            14 -> {//自选不中
                                val n = now_lists.length - now_lists.replace(":", "").length
                                if (n in 5..12) {
                                    var model = zxbz_list[n - 5].dataContent[0]
                                    var bet = ""
                                    for (key in now_lists.split(",")) {
                                        bet += (key.replace(":", "").toInt() + 1).toString() + ","
                                    }
                                    if (bet.length > 0) {
                                        bet = bet.substring(0, bet.length - 1)
                                    }
                                    model.betInfo = bet
                                    xz_list.add(model)
                                    str_list.add("【" + left_list[type1_index] + "-" + model.name + "】 @"
                                            + model.odds + "X" + tz_et.text.toString())
                                }
                            }
                            else -> {
                                for (type2 in now_lists.split(",")) {//根据逗号隔开获得当前选择的数据
                                    var nums = type2.split(":")//截取前后字段
                                    var tab_position = nums[0].toInt()//右侧顶部选中的tab
                                    var num = nums[1].toInt()//选中的第几个球
                                    var now_zhu = right_all_list[type1_index][tab_position].dataContent[num]
                                    xz_list.add(now_zhu)
                                    str_list.add("【" + left_list[type1_index] + "-" + now_zhu.name + "】 @"
                                            + now_zhu.odds?.toDoubleOrNull() + "X" + tz_et.text.toString())
                                }
                            }

                        }
                    }
                }
                str_list.add("――――――――――――")
                try {
                    str_list.add("【合计】总注数：" + xz_list.size + "   总金额：" + (xz_list.size * (tz_et.text.toString()).toDouble()).toString())
                } catch (e: Exception) {

                }
                if (xz_list.size > 0) {
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
                } else {
                    toast("请选择下注内容")
                }
            }
        }
    }

    var tab_click_position = 0//tab点击事件
    fun load_right(position: Int) {
        when (position) {
            7, 8, 9, 10, 11, 12 -> {
                ty2_top_gv.numColumns = 1
            }
            14 -> {
                ty2_top_gv.numColumns = 5
            }
            4, 6, 13 -> {
                ty2_top_gv.numColumns = 2
            }
            else -> {
                ty2_top_gv.numColumns = 3
            }
        }
        when (click_position) {
            10 -> {//合肖：如果选择的范围不在2-11 那么清空布局
                var now_lists = item_click_list[click_position]//当前选中的集合内容
                if (now_lists.length > 2) {//确定有数值
                    now_lists = now_lists.substring(1, now_lists.length - 1)
                    val n = now_lists.length - now_lists.replace(":", "").length
                    if (n in 2..11) {

                    } else {
                        item_click_list.removeAt(click_position)
                    }
                }
            }
            14 -> {//如果当前选中的没有在5..12
                var now_lists = item_click_list[click_position]//当前选中的集合内容
                if (now_lists.length > 2) {//确定有数值
                    now_lists = now_lists.substring(1, now_lists.length - 1)
                    val n = now_lists.length - now_lists.replace(":", "").length
                    if (n in 5..12) {

                    } else {
                        item_click_list.removeAt(click_position)
                    }
                }
            }
        }
        click_position = position
        ty2_title.text = left_list[click_position]
        left_adapter!!.refresh(left_list)
        if (right_all_list.size > position) {
            right_adapter!!.refresh(right_all_list[position][0].dataContent)
        }
        if (right_all_list[click_position].size > 1) {
            tab.visibility = View.VISIBLE
            if (right_all_list[click_position].size == 2) {
                tab.tabMode = TabLayout.MODE_FIXED
            } else {
                tab.tabMode = TabLayout.MODE_SCROLLABLE
            }
            tab.removeAllTabs()
            for (m in right_all_list[click_position]) {
                tab.addTab(tab.newTab().setText(m.name))
            }
            ty2_title.text = right_all_list[click_position][0].name
            tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    //选择
                    tab_click_position = tab.position//当前选择的位置
                    ty2_title.text = right_all_list[click_position][tab_click_position].name
                    right_adapter!!.refresh(right_all_list[click_position][tab_click_position].dataContent)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        } else {
            tab.visibility = View.GONE
        }
    }

    fun load_left() {
        var mQQPop: EasyPopup = EasyPopup(this).setContentView<EasyPopup>(R.layout.layout_center_pop)

        mQQPop.setAnimationStyle<EasyPopup>(R.style.QQPopAnim)
                .setFocusAndOutsideEnable<EasyPopup>(true)
                .setBackgroundDimEnable<EasyPopup>(true)
                .setWidth<EasyPopup>(AndroidUtils.dp2px(150))
                .createPopup<EasyPopup>()

        mQQPop.showAtAnchorView(title_tv!!, VerticalGravity.BELOW, HorizontalGravity.LEFT, AndroidUtils.dp2px(30), 0)
        var lv = mQQPop.getView<ListView>(R.id.pop_lv)
        pop_list = ArrayList()
        pop_list.add(PopModel("返回大厅", ""))
        pop_list.add(PopModel("北京赛车", ""))
        pop_list.add(PopModel("重庆时时彩", ""))
        pop_list.add(PopModel("PC蛋蛋", ""))
        pop_list.add(PopModel("江苏骰宝", ""))
        pop_list.add(PopModel("幸运飞艇", ""))
        pop_list.add(PopModel("香港六合彩", ""))
        lv.adapter = adapter
        lv.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> startActivity(Intent(this@LHCActivity, GameActivity::class.java))
            //重庆
                2 -> startActivity(Intent(this@LHCActivity, JSGBActivity::class.java).putExtra("index", "1"))
            //北京赛车
                1 -> startActivity(Intent(this@LHCActivity, LHCActivity::class.java).putExtra("index", "50"))
            //PC蛋蛋
                3 -> startActivity(Intent(this@LHCActivity, JSGBActivity::class.java).putExtra("index", "66"))
            //快三
                4 -> startActivity(Intent(this@LHCActivity, JSGBActivity::class.java).putExtra("index", "10"))
            //幸运飞艇
                5 -> startActivity(Intent(this@LHCActivity, LHCActivity::class.java).putExtra("index", "55"))
            //六合彩
                6 -> mQQPop.dismiss()
            }
            finish()
        }
        adapter!!.refresh(pop_list)
    }

    override fun onResume() {
        super.onResume()
        control!!.get_tz(cz_id)
        control!!.get_zj_last(cz_id)
    }

    fun getResource(imageName: Int): Int {
        var url = "1"
        when (imageName) {
            1, 2, 7, 8, 12, 13, 18, 19, 23, 24, 29,
            30, 34, 35, 40, 45, 46 -> {
                url = "1"
            }
            3, 4, 9, 10, 14, 15, 20, 25, 26, 31, 36,
            37, 41, 42, 47, 48 -> {
                url = "2"
            }
            else -> {
                url = "3"
            }
        }
        val ctx = baseContext

        if (!TextUtils.isEmpty(url) && url.substring(0, 1) == "0") {
            url = url.substring(1)
        }
        return resources.getIdentifier("qiu_" + url, "mipmap", ctx.packageName)
    }

    /**
     * 记录当前点击的item
     * @param position 当前点击的位置
     * */
    fun get_now_clicks(position: Int) {
        var clicks = item_click_list[click_position]
        var item = right_all_list[click_position]
        //if (item[tab_click_position].dataContent[position].odds != null) {
        //if (TextUtils.isEmpty(clicks)) clicks = ",$tab_click_position:$clicks"//如果数据为空加一个逗号 好判断
        if (clicks.isNotEmpty() && clicks.substring(0, 1) != ",") {
            clicks = ",$tab_click_position:$position"
        }
        var result = ""
        if (clicks.contains(",$tab_click_position:$position,")) {
            result = clicks.replace(",$tab_click_position:$position,", ",")
        } else {
            result = clicks + "$tab_click_position:$position,"
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
        right_adapter!!.refresh(right_all_list[click_position][tab_click_position].dataContent)
        left_adapter!!.refresh(left_list)
        //}
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_lhc
    }
}