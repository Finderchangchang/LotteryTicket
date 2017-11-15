package gy.lotteryticket.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import butterknife.BindView
import gy.lotteryticket.R
import gy.lotteryticket.adapter.MainAdapter
import gy.lotteryticket.base.BaseFragment
import gy.lotteryticket.databinding.FragCapitalBinding
import gy.lotteryticket.databinding.FragGameBinding

/**
 * 资金管理
 * Created by Administrator on 2017/11/11.
 */

class CapitalFragment : BaseFragment<FragCapitalBinding>() {
    var capital_tl: TabLayout? = null
    var capital_vp: ViewPager? = null
    override fun initView(view: View?) {
        capital_tl = view!!.findViewById(R.id.capital_tl) as TabLayout
        capital_vp = view!!.findViewById(R.id.capital_vp) as ViewPager

    }

    override fun init(savedInstanceState: Bundle?) {
        var mAdapter = MainAdapter(HomeActivity.main!!.supportFragmentManager)
        capital_vp!!.adapter = mAdapter
        capital_tl!!.setupWithViewPager(capital_vp)
    }


    /**
     * 延时加载
     */
    override fun onDelayLoad() {

    }

    override fun setLayoutId(): Int {
        return R.layout.frag_capital
    }


    override fun dataCallback(result: Int, obj: Any) {

    }

    companion object {

    }
}