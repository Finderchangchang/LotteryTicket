package gy.lotteryticket.ui.xz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityBjCarBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.model.LeftClickModel
import kotlinx.android.synthetic.main.ac_type1.*
import kotlinx.android.synthetic.main.activity_bj_car.*
import java.util.ArrayList

/**
 * 北京赛车
 * */
class BJCarActivity : BaseActivity<ActivityBjCarBinding>() {
    var top_num_list = ArrayList<Int>()
    var left_list = ArrayList<LeftClickModel>()
    var right_list = ArrayList<String>()
    var topadapter: CommonAdapter<Int>? = null//资讯
    var left_adapter: CommonAdapter<LeftClickModel>? = null//资讯
    var left_click = 0
    var type1_list = ArrayList<String>()//第一种分类
    var type1_adapter: CommonAdapter<String>? = null//资讯
    var type1_click = 0

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        init_view()
        topadapter = object : CommonAdapter<Int>(this, top_num_list, R.layout.item_top_num) {
            override fun convert(holder: CommonViewHolder, num: Int, position: Int) {
                holder.setImageResource(R.id.iv, num)
            }
        }
        type1_adapter = object : CommonAdapter<String>(this, type1_list, R.layout.item_type1) {
            override fun convert(holder: CommonViewHolder, num: String, position: Int) {
                holder.setText(R.id.title, num)
                if (position == type1_click) {
                    holder.setTextColor(R.id.title, R.color.white)
                    holder.setBGColor(R.id.title, R.color.lab_col)
                } else {
                    holder.setTextColor(R.id.title, R.color.lab_col)
                    holder.setBGColor(R.id.title, R.color.white)
                }
            }
        }
        top_gv.adapter = type1_adapter
        bottom_gv.adapter = type1_adapter

        top_gv.setOnItemClickListener { parent, view, position, id ->
            type1_click = position
            type1_adapter!!.refresh(type1_list)
        }
        top_jiang_gv.adapter = topadapter
        left_adapter = object : CommonAdapter<LeftClickModel>(this, left_list, R.layout.item_left) {
            override fun convert(holder: CommonViewHolder, num: LeftClickModel, position: Int) {
                holder.setText(R.id.title, num.name)
                if (position == left_click) {
                    holder.setTextColor(R.id.title, R.color.white)
                } else {
                    holder.setTextColor(R.id.title, R.color.lab_col)
                }
            }
        }
        left_lv.adapter = left_adapter
        left_lv.setOnItemClickListener { parent, view, position, id ->
            left_click = position
            left_adapter!!.refresh(left_list)
        }
    }

    private fun init_view() {
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        top_num_list.add(R.mipmap.num_1)
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", true))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        left_list.add(LeftClickModel("1-10", false))
        for (i in 0 until 60) {
            type1_list.add("9.9")
        }
    }

    /**
     * 根据左侧点击，刷新右侧数据
     * */
    fun load_data() {
        when (left_click) {
            0 -> {

            }
        }

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_bj_car
    }
}