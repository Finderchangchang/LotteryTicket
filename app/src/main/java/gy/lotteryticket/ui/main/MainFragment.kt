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

/**
 * Created by Administrator on 2017/11/11.
 */

class MainFragment : BaseFragment<FragMainBinding>() {
    override fun initView(view: View?) {
        ll1 = view!!.findViewById(R.id.ll1) as LinearLayout?
    }

    override fun init(savedInstanceState: Bundle?) {
        ll1!!.setOnClickListener { startActivity(Intent(HomeActivity.main, BJCarActivity::class.java)) }
    }

    var ll1: LinearLayout? = null
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