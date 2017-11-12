package gy.lotteryticket.ui.main

import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseFragment
import gy.lotteryticket.databinding.FragCapitalBinding
import gy.lotteryticket.databinding.FragGameBinding

/**
 * Created by Administrator on 2017/11/11.
 */

class CapitalFragment : BaseFragment<FragCapitalBinding>() {
    override fun init(savedInstanceState: Bundle?) {

    }

    @BindView(R.id.ll1)
    internal var mUseModule1: Button? = null


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