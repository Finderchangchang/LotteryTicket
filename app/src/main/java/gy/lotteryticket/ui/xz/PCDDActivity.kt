package gy.lotteryticket.ui.xz

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityPcDdBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import kotlinx.android.synthetic.main.activity_pc_dd.*
import com.google.gson.JsonArray
import gd.mmanage.config.sp
import gy.lotteryticket.config.command
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.*
import kotlinx.android.synthetic.main.ac_type2.*
import org.json.JSONArray

/**
 *PC蛋蛋(解决赛车的布局)
 * */
class PCDDActivity : BaseActivity<ActivityPcDdBinding>(), AbsModule.OnCallback {
    var left_list: ArrayList<String> = ArrayList<String>()
    var right_all_list: ArrayList<ArrayList<XZModel>> = ArrayList<ArrayList<XZModel>>()
    var left_adapter: CommonAdapter<String>? = null
    var right_adapter: CommonAdapter<XZModel>? = null
    var title_adapter: CommonAdapter<String>? = null
    var title_list: ArrayList<String> = ArrayList()
    var right_list: ArrayList<XZModel> = ArrayList<XZModel>()
    var click_position = 0
    var xz_num = 0
    var item_click_list: ArrayList<String> = ArrayList<String>()
    var xz_list: ArrayList<XZModel> = ArrayList<XZModel>()//下注列表
    var cz_id = "10"
    var now_qh = ""//当前期号
    var data_ftime = ""//封盘秒数
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.xz -> {//加载数据
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    var model = Gson().fromJson<PCDDModel>(success.obj!![0].toString(), PCDDModel::class.java)
                    var list = model.dataGroup
                    if (list.size > 0) {
                        var a = 0
                        for (key in list) {
                            item_click_list.add("")
                            left_list.add(key.name)
                            if (a == 0) {
                                var list = key.dataContent as ArrayList<XZModel>
                                for (i in 0 until 10) {
                                    list.add(i * 6, XZModel((i + 1).toString()))
                                }
                                list.add(60, XZModel("名次"))
                                list.add(61, XZModel("第六"))
                                list.add(62, XZModel("第七"))
                                list.add(63, XZModel("第八"))
                                list.add(64, XZModel("第九"))
                                list.add(65, XZModel("第十"))
                                for (i in 11 until 21) {
                                    list.add(i * 6, XZModel((i - 10).toString()))
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
                            ty2_title.text = "三军、大小"
                            right_adapter!!.refresh(right_all_list[0])
                        }
                    }
                }
            }
            command.xz + 1 -> {//下注
                success as NormalRequest<JsonArray>
                if (success.code == 0) {

                } else {

                }
                toast(success.message)
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
                    item_can_click = true
                    top_qi_tv.text = model.lastNum + "期    " + model.lastKj
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
                    title_bar.center_str = model.title
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

    var item_can_click = true//true:可以点击 false:不可以点击
    var can_run = true//执行循环操作
    var kjtime = ""
    var handler = Handler()
    var runnable: Runnable = object : Runnable {
        override fun run() {
            if (can_run) {
                var time = Utils.getDatePoor(Utils.change_data(kjtime))
                if (time == "00:00") {
                    can_run = false
                    next4_qi_tv.text = "开奖中"
                    control!!.get_zj_last(cz_id)//获得最新一期开奖信息
                }
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
                    item_can_click = false
                    next2_qi_tv.text = "封盘中"
                } else {
                    next2_qi_tv.text = left_time + ":" + right_time
                }
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onError(result: Int, error: Any?) {
        dialog!!.dismiss()
    }

    var control: XZModule? = null
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(XZModule::class.java, this)
        dialog!!.setTitle(R.string.dialog_loading)
        dialog!!.show()
        cz_id = intent.getStringExtra("index")
        control!!.get_tz(cz_id, "1")
        control!!.get_zj_last(cz_id)
        ty2_top_gv.setOnItemClickListener { _, _, position, _ -> if (item_can_click) get_now_clicks(position) }
        ty2_top_gv.numColumns = 6
        title_adapter = object : CommonAdapter<String>(this, title_list, R.layout.item_title) {
            override fun convert(holder: CommonViewHolder, model: String, position: Int) {
                holder.setGImage(R.id.iv, getResource(model))
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
                if (click_position == 1) {//特码的情况
                    holder.setBGColor(R.id.total_ll, R.color.colorPrimaryDark)
//                    holder.setTextColor(R.id.left_tv, R.color.tm_colorPrimaryDark)
                } else {
                    holder.setBGColor(R.id.total_ll, R.color.tm_white)
                    holder.setTextColor(R.id.left_tv, R.color.black)
                }

                when (click_position) {
                    0 -> {
                        if (position % 6 == 0 || position in 60..65) {
                            holder.setText(R.id.left_tv, model.name)
                            holder.setVisible(R.id.right_tv, false)
                        } else {
                            holder.setText(R.id.left_tv, Utils.cut_all_0_num(model.odds))
                        }
                    }
                    else -> {
                        holder.setText(R.id.left_tv, model.name)
                        if (model.odds != null) {
                            if (position % 6 == 0) {
                                holder.setVisible(R.id.right_tv, false)
                            } else {
                                holder.setVisible(R.id.right_tv, true)
                            }
                            holder.setText(R.id.right_tv, Utils.cut_all_0_num(model.odds))
                        } else {
                            holder.setVisible(R.id.right_tv, false)
                        }
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
                if (position == 0) {
                    ty2_top_gv.numColumns = 6
                } else {
                    ty2_top_gv.numColumns = 2
                }
                click_position = position
                ty2_title.text = left_list[click_position]
                left_adapter!!.refresh(left_list)
                if (right_all_list.size > position) {
                    right_adapter!!.refresh(right_all_list[position])
                }
            }
        }
        left_lv.cacheColorHint = 0

        ty2_top_gv.adapter = right_adapter
        //A盘
        center_btn.setOnClickListener {
            center_btn.isClickable = false
            right_btn.isClickable = false
            control?.change_ab()
        }
        //B盘
        right_btn.setOnClickListener {
            center_btn.isClickable = false
            right_btn.isClickable = false
            control?.change_ab()
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
                for (type1_index in 0 until item_click_list.size) {
                    var now_lists = item_click_list[type1_index]//当前选中的集合内容
                    if (now_lists.length > 2) {//确定有数值
                        now_lists = now_lists.substring(1, now_lists.length - 1)
                        for (type2 in now_lists.split(",")) {//根据逗号隔开获得当前选择的数据
                            var num = type2.toInt()
                            xz_list.add(right_all_list[type1_index][num])
                        }
                    }
                }
                control!!.get_xz(cz_id, now_qh, tz, (tz.toInt() * xz_num).toString(), xz_list)
            }
        }
    }

    fun getResource(imageName: String): Int {
        val ctx = baseContext
        return resources.getIdentifier(imageName, "mipmap", ctx.packageName)
    }

    /**
     * 记录当前点击的item
     * @param position 当前点击的位置
     * */
    fun get_now_clicks(position: Int) {
        var clicks = item_click_list[click_position]
        var item = right_all_list[click_position]
        if (item[position].odds != null) {
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
            right_adapter!!.refresh(item)
            left_adapter!!.refresh(left_list)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_pc_dd
    }
}