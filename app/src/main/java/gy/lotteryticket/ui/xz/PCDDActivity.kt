package gy.lotteryticket.ui.xz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
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
import gy.lotteryticket.config.command
import gy.lotteryticket.model.*
import kotlinx.android.synthetic.main.ac_type2.*

/**
 *PC蛋蛋
 * */
class PCDDActivity : BaseActivity<ActivityPcDdBinding>(), AbsModule.OnCallback {
    var left_list: ArrayList<String> = ArrayList<String>()
    var right_all_list: ArrayList<ArrayList<XZModel>> = ArrayList<ArrayList<XZModel>>()
    var left_adapter: CommonAdapter<String>? = null
    var right_adapter: CommonAdapter<XZModel>? = null
    var right_list: ArrayList<XZModel> = ArrayList<XZModel>()
    var click_position = 0
    var xz_num = 0
    var item_click_list: ArrayList<String> = ArrayList<String>()
    var xz_list: ArrayList<XZModel> = ArrayList<XZModel>()//下注列表

    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.xz -> {//加载数据
                success as NormalRequest<JsonArray>
                if (success.obj != null && success.obj!!.size() > 0) {
                    var model = Gson().fromJson<PCDDModel>(success.obj!![0].toString(), PCDDModel::class.java)
                    var list = model.dataGroup
                    if (list.size > 0) {
                        for (key in list) {
                            item_click_list.add("")
                            left_list.add(key.name)
                            right_all_list.add(key.dataContent as ArrayList<XZModel>)
                        }
                        left_adapter!!.refresh(left_list)
                        //加载默认数据
                        if (right_all_list.size > 0) {
                            ty2_title.text = "混合"
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
                var s = ""
            }
            command.xz + 2 -> {//切换AB盘

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
                    top_qi_tv.text = model.kjNum + "期"
                    title_bar.center_str = model.title
                }
            }
        }
        dialog!!.dismiss()
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
        control!!.get_tz("66", "1")
        control!!.get_zj_last("50")
        ty2_top_gv.setOnItemClickListener { _, _, position, _ ->
            //get_now_clicks(position)
        }
        left_adapter = object : CommonAdapter<String>(this, left_list, R.layout.item_left) {
            override fun convert(holder: CommonViewHolder, model: String, position: Int) {
                if (position == click_position) {
                    holder.setTextColor(R.id.title, R.color.white)
                    holder.setBGColor(R.id.title, R.color.black)
                } else {
                    holder.setTextColor(R.id.title, R.color.black)
                    holder.setBGColor(R.id.title, R.color.white)
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
                    holder.setBGColor(R.id.left_tv, R.color.white)
                    holder.setTextColor(R.id.left_tv, R.color.black)
                } else {
                    holder.setBGColor(R.id.left_tv, 0)
                    holder.setTextColor(R.id.left_tv, R.color.black)
                }
                holder.setText(R.id.left_tv, model.name)
                holder.setText(R.id.right_tv, model.odds)
                var clicks = item_click_list[click_position]
                if (clicks.contains("," + position.toString() + ",")) {//被点击
                    holder.setBGColor(R.id.total_ll, R.color.black)
                } else {
                    holder.setBGColor(R.id.total_ll, R.color.white)
                }
            }
        }
        left_lv.adapter = left_adapter
        left_lv.setOnItemClickListener { parent, view, position, id ->
            if (click_position != position) {//两个位置不相同 执行点击操作
                click_position = position
                ty2_title.text = left_list[click_position]
                left_adapter!!.refresh(left_list)
                if (right_all_list.size > position) {
                    right_adapter!!.refresh(right_all_list[position])
                }
            }

            ty2_top_gv.adapter = right_adapter
            //A盘
            center_btn.setOnClickListener { control?.change_ab() }
            //B盘
            right_btn.setOnClickListener { control?.change_ab() }
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
                    control!!.get_xz("66", "777", tz, (tz.toInt() * xz_num).toString(), xz_list)
                }
            }
        }

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
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_pc_dd
    }
}