package gy.lotteryticket.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

import butterknife.BindView
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseFragment
import gy.lotteryticket.databinding.FragMainBinding
import gy.lotteryticket.ui.xz.BJCarActivity
import gy.lotteryticket.ui.xz.CCsscActivity
import gy.lotteryticket.ui.xz.LHCActivity

/**
 * Created by Administrator on 2017/11/11.
 */

class MainFragment : BaseFragment<FragMainBinding>() {
    var ll1: LinearLayout? = null
    var ll2: LinearLayout? = null
    var ll3: LinearLayout? = null
    var ll4: LinearLayout? = null
    var ll5: LinearLayout? = null
    var ll6: LinearLayout? = null

    override fun initView(view: View?) {
        ll1 = view!!.findViewById(R.id.ll1) as LinearLayout?
        ll2 = view!!.findViewById(R.id.ll2) as LinearLayout?
        ll3 = view!!.findViewById(R.id.ll3) as LinearLayout?
        ll4 = view!!.findViewById(R.id.ll4) as LinearLayout?
        ll5 = view!!.findViewById(R.id.ll5) as LinearLayout?
        ll6 = view!!.findViewById(R.id.ll6) as LinearLayout?
    }

    override fun init(savedInstanceState: Bundle?) {
        ll1!!.setOnClickListener { startActivity(Intent(HomeActivity.main, BJCarActivity::class.java)) }
        ll2!!.setOnClickListener { startActivity(Intent(HomeActivity.main, BJCarActivity::class.java)) }
        ll3!!.setOnClickListener { startActivity(Intent(HomeActivity.main, CCsscActivity::class.java)) }
        ll4!!.setOnClickListener { startActivity(Intent(HomeActivity.main, BJCarActivity::class.java)) }
        ll5!!.setOnClickListener { startActivity(Intent(HomeActivity.main, BJCarActivity::class.java)) }
        ll6!!.setOnClickListener { startActivity(Intent(HomeActivity.main, LHCActivity::class.java)) }

    }


    /**
     * 延时加载
     */
    override fun onDelayLoad() {

    }

    override fun setLayoutId(): Int {
        return R.layout.frag_main
    }


    override fun dataCallback(result: Int, obj: Any) {

    }

    companion object {

    }
}