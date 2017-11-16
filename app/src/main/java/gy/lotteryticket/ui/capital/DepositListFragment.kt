package gy.lotteryticket.ui.capital

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import gy.lotteryticket.R
import gy.lotteryticket.adapter.UserAdapter
import gy.lotteryticket.base.BaseFragment
import gy.lotteryticket.databinding.FragCapitalBinding
import gy.lotteryticket.ui.main.HomeActivity

/**
 * 存款记录
 * Created by Administrator on 2017/11/11.
 */

class DepositListFragment : BaseFragment<FragCapitalBinding>() {
    override fun initView(view: View?) {

    }

    override fun init(savedInstanceState: Bundle?) {
       }


    /**
     * 延时加载
     */
    override fun onDelayLoad() {

    }

    override fun setLayoutId(): Int {
        return R.layout.frag_deposit_list
    }


    override fun dataCallback(result: Int, obj: Any) {

    }

    companion object {

    }
}