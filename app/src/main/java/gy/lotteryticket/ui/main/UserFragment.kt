package gy.lotteryticket.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import butterknife.BindView
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseFragment
import gy.lotteryticket.databinding.FragCapitalBinding
import gy.lotteryticket.databinding.FragUserBinding

/**
 * Created by Administrator on 2017/11/11.
 */

class UserFragment : BaseFragment<FragUserBinding>() {
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
        return R.layout.frag_user
    }


    override fun dataCallback(result: Int, obj: Any) {

    }

    companion object {

    }
}