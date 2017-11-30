package gy.lotteryticket.ui.xz

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityLhcBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.model.LeftClickModel
import gy.lotteryticket.model.RightClick1Model
import gy.lotteryticket.model.RightClick2Model
import kotlinx.android.synthetic.main.ac_lhc_type1.*
import kotlinx.android.synthetic.main.ac_type1.*
import kotlinx.android.synthetic.main.ac_type2.*
import kotlinx.android.synthetic.main.activity_lhc.*
import java.util.ArrayList

/**
 * 六合彩
 * */
class LHCActivity : BaseActivity<ActivityLhcBinding>() {
    var top_num_list = ArrayList<Int>()
    var left_list = ArrayList<LeftClickModel>()
    var right_list = ArrayList<String>()
    var topadapter: CommonAdapter<Int>? = null//资讯
    var left_adapter: CommonAdapter<LeftClickModel>? = null//资讯
    var left_click = 0
    var type1_list = ArrayList<RightClick1Model>()//第一种分类
    var type1_adapter: CommonAdapter<RightClick1Model>? = null//资讯
    var type1_1list = ArrayList<RightClick1Model>()//第一种第二个分类
    var type1_1adapter: CommonAdapter<RightClick1Model>? = null//资讯
    var can_xz = true//可以下注
    var type2_list = ArrayList<RightClick2Model>()//冠亚和值
    var type2_adapter: CommonAdapter<RightClick2Model>? = null//资讯

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        init_view()
        topadapter = object : CommonAdapter<Int>(this, top_num_list, R.layout.item_top_num) {
            override fun convert(holder: CommonViewHolder, num: Int, position: Int) {
                holder.setImageResource(R.id.iv, num)
            }
        }
        type1_adapter = object : CommonAdapter<RightClick1Model>(this, type1_list, R.layout.item_type1) {
            override fun convert(holder: CommonViewHolder, num: RightClick1Model, position: Int) {
                type1_init(holder, num, position)
            }
        }
        type1_1adapter = object : CommonAdapter<RightClick1Model>(this, type1_1list, R.layout.item_type1) {
            override fun convert(holder: CommonViewHolder, num: RightClick1Model, position: Int) {
                type1_init(holder, num, position)
            }
        }
        type2_adapter = object : CommonAdapter<RightClick2Model>(this, type2_list, R.layout.item_type2) {
            override fun convert(holder: CommonViewHolder, num: RightClick2Model, position: Int) {
                type2_init(holder, num, position)
            }
        }
        ty1_top_gv.adapter = type1_adapter
        ty1_bottom_gv.adapter = type1_1adapter

        ty1_top_gv.setOnItemClickListener { parent, view, position, id ->
            if (can_xz && position % 6 != 0) {
                type1_list[position].checked = !type1_list[position].checked
                type1_adapter!!.refresh(type1_list)
            }
        }
        ty1_bottom_gv.setOnItemClickListener { parent, view, position, id ->
            if (can_xz && position % 6 != 0) {
                type1_1list[position].checked = !type1_1list[position].checked
                type1_1adapter!!.refresh(type1_1list)
            }
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
            if (can_xz) {
                left_click = position
                load_data()
                left_adapter!!.refresh(left_list)
            }
        }
    }

    fun type1_init(holder: CommonViewHolder, num: RightClick1Model, position: Int) {
        //当前item是否可以被6整除
        if (position % 6 == 0) {
            holder.setText(R.id.title, (position / 6 + 1).toString())
        } else {
            holder.setText(R.id.title, num.name)
        }
        if (num.checked) {
            holder.setTextColor(R.id.title, R.color.white)
            holder.setBGColor(R.id.title, R.color.lab_col)
        } else {
            holder.setTextColor(R.id.title, R.color.lab_col)
            holder.setBGColor(R.id.title, R.color.white)
        }
    }

    fun type2_init(holder: CommonViewHolder, num: RightClick2Model, position: Int) {
        when (num.style) {
        //左侧图片右侧赔率
            3 -> {
                holder.setText(R.id.left_tv, num.left_name)
            }
        }
        holder.setText(R.id.left_tv, num.left_name)
        holder.setText(R.id.right_tv, num.right_pl)
        if (num.checked) {
            holder.setTextColor(R.id.left_tv, R.color.white)
            holder.setTextColor(R.id.right_tv, R.color.white)
            holder.setBGColor(R.id.total_ll, R.color.lab_col)
        } else {
            holder.setTextColor(R.id.left_tv, R.color.black)
            holder.setTextColor(R.id.right_tv, R.color.lab_col)
            holder.setBGColor(R.id.total_ll, R.color.white)
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
            type1_list.add(RightClick1Model("9.9", false))
            type1_1list.add(RightClick1Model("8.9", false))
        }
//        for (i in 0 until 20) {
//            type2_list.add(RightClick2Model((i + 5).toString(), "100", false))
//        }
    }

    fun load2() {
        type2_list.clear()
        for (i in 0 until 20) {
            type2_list.add(RightClick2Model((i + 5).toString(), "100", false))
        }
    }

    /**
     * 根据左侧点击，刷新右侧数据
     * */
    fun load_data() {
        ty1.visibility = View.GONE
        ty2.visibility = View.GONE
        when (left_click) {
            0, 2, 4, 5, 11, 13 -> {

                if (left_click != 2) {
                    ty1.visibility = View.VISIBLE
                    ty1_tl.removeAllTabs()
                    when (left_click) {
                        0 -> {
                            bottom_ll.visibility = View.VISIBLE
                            setTitleTL(arrayOf("特码B", "特码A"))
                        }
                        4 -> {
                            setTitleTL(arrayOf("正1特", "正2特", "正3特", "正4特"))
                        }
                        5 -> {
                            setTitleTL(arrayOf("三全中", "三中二", "二全中", "二中特"))
                        }
                        11 -> {
                            setTitleTL(arrayOf("二连肖", "三连肖", "四连肖", "五连肖"))
                        }
                        13 -> {
                            setTitleTL(arrayOf("二连尾", "三连尾", "四连尾", "五连尾"))
                        }
                    }
                    ty1_tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

                        override fun onTabSelected(tab: TabLayout.Tab) {
                            ty1_title.text = tab.text
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab) {

                        }

                        override fun onTabReselected(tab: TabLayout.Tab) {

                        }
                    })
                } else {
                    bottom_ll.visibility = View.VISIBLE
                }
                ty1_top_gv.numColumns = 3
            }
            1 -> {
                load2()
                ty1_top_gv.numColumns = 2
                ty2.visibility = View.VISIBLE
                ty2_top_gv.adapter = type2_adapter
                ty2_top_gv.setOnItemClickListener { parent, view, position, id ->
                    type2_list[position].checked = !type2_list[position].checked
                    type2_adapter!!.refresh(type2_list)
                }
            }
            3 -> {//正码1-6

            }
        }
    }

    fun setTitleTL(arr: Array<String>) {
        ty1_tl.removeAllTabs()
        for (model in arr) {
            ty1_tl.addTab(ty1_tl.newTab().setText(model))
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_lhc
    }
}