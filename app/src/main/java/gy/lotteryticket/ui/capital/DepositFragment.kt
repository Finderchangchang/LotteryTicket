package gy.lotteryticket.ui.capital

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import gy.lotteryticket.R
import gy.lotteryticket.adapter.UserAdapter
import gy.lotteryticket.base.BaseFragment
import gy.lotteryticket.databinding.FragCapitalBinding
import gy.lotteryticket.ui.CJActivity
import gy.lotteryticket.ui.main.CapitalActivity
import gy.lotteryticket.ui.main.HomeActivity
import kotlinx.android.synthetic.main.frag_deposit.*

/**
 * 存款
 * Created by Administrator on 2017/11/11.
 */

class DepositFragment : BaseFragment<FragCapitalBinding>() {
    override fun initView(view: View?) {

    }

    override fun init(savedInstanceState: Bundle?) {
        zfb_ll.setOnClickListener { startActivity(Intent(CapitalActivity.context,CJActivity::class.java).putExtra("index",5)) }
        wx_ll.setOnClickListener { startActivity(Intent(CapitalActivity.context,CJActivity::class.java).putExtra("index",6)) }
    }


    /**
     * 延时加载
     */
    override fun onDelayLoad() {

    }

    override fun setLayoutId(): Int {
        return R.layout.frag_deposit
    }


    override fun dataCallback(result: Int, obj: Any) {

    }

    companion object {

    }
}