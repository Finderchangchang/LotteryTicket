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
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.PCDDModel
import kotlinx.android.synthetic.main.activity_pc_dd.*
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.ac_type2.*

/**
 *PC蛋蛋
 * */
class PCDDActivity : BaseActivity<ActivityPcDdBinding>(), AbsModule.OnCallback {
    var left_list: ArrayList<String> = ArrayList<String>()
    var right_all_list: ArrayList<ArrayList<PCDDModel.DataGroupBean.DataContentBean>> = ArrayList<ArrayList<PCDDModel.DataGroupBean.DataContentBean>>()
    var left_adapter: CommonAdapter<String>? = null
    var right_adapter: CommonAdapter<PCDDModel.DataGroupBean.DataContentBean>? = null
    var right_list: ArrayList<PCDDModel.DataGroupBean.DataContentBean> = ArrayList<PCDDModel.DataGroupBean.DataContentBean>()
    var click_position = 0
    var item_click_list: ArrayList<String> = ArrayList<String>()
    var arr = Array(3) { IntArray(5) }
    override fun onSuccess(result: Int, success: Any?) {
        success as NormalRequest<JsonArray>
        if (success.obj != null && success.obj!!.size() > 0) {
            var model = Gson().fromJson<PCDDModel>(success.obj!![0].toString(), PCDDModel::class.java)
            var list = model.dataGroup
            if (list.size > 0) {
                for (key in list) {
                    item_click_list.add("")
                    left_list.add(key.name)
                    right_all_list.add(key.dataContent as ArrayList<PCDDModel.DataGroupBean.DataContentBean>)
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

    override fun onError(result: Int, error: Any?) {

    }

    var control: XZModule? = null
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(XZModule::class.java, this)
        control!!.get_tz("66", "1")
        ty2_top_gv.setOnItemClickListener { parent, view, position, id ->
            var clicks = item_click_list[click_position]
            var result = ""
            for (i in clicks.split(",")) {

                    if (i != position.toString()) {
                        clicks += position.toString() + ","
                    } else {
                        var a = ""
                    }

            }
            item_click_list[click_position] = clicks
            var s = ""
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
                holder.setText(R.id.title, model)
            }
        }
        right_adapter = object : CommonAdapter<PCDDModel.DataGroupBean.DataContentBean>(this, right_list, R.layout.item_type2) {
            override fun convert(holder: CommonViewHolder, model: PCDDModel.DataGroupBean.DataContentBean, position: Int) {
                if (click_position == 1) {//特码的情况
                    holder.setBGColor(R.id.left_tv, R.color.white)
                    holder.setTextColor(R.id.left_tv, R.color.black)
                } else {
                    holder.setBGColor(R.id.left_tv, 0)
                    holder.setTextColor(R.id.left_tv, R.color.black)
                }
                holder.setText(R.id.left_tv, model.name)
                holder.setText(R.id.right_tv, model.odds)
            }
        }
        left_lv.adapter = left_adapter
        left_lv.setOnItemClickListener { parent, view, position, id ->
            if (click_position != position) {//两个位置不相同 执行点击操作
                click_position = position
                when (click_position) {
                    0 -> ty2_title.text = "混合"
                    else -> ty2_title.text = "特码"
                }
                left_adapter!!.refresh(left_list)
                if (right_all_list.size > position) {
                    right_adapter!!.refresh(right_all_list[position])
                }
            }
        }
        ty2_top_gv.adapter = right_adapter
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_pc_dd
    }
}