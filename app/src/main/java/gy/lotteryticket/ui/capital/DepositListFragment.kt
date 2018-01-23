package gy.lotteryticket.ui.capital

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.arialyy.frame.module.AbsModule
import gy.lotteryticket.R
import gy.lotteryticket.adapter.UserAdapter
import gy.lotteryticket.base.BaseFragment
import gy.lotteryticket.config.command
import gy.lotteryticket.control.UserModule
import gy.lotteryticket.databinding.FragCapitalBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.method.QuKuanDialog
import gy.lotteryticket.method.ZiJinDialog
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.ZDModel
import gy.lotteryticket.ui.main.CapitalActivity
import gy.lotteryticket.ui.main.HomeActivity
import kotlinx.android.synthetic.main.frag_deposit_list.*

/**
 * 存款记录
 * Created by Administrator on 2017/11/11.
 */

class DepositListFragment : BaseFragment<FragCapitalBinding>(), AbsModule.OnCallback {
    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.user -> {//加载配置数据
                success as NormalRequest<ZDModel>
                if (success.code == 0 && success.obj != null) {
                    list = (success.obj!!.data as ArrayList<ZDModel.DataBean>?)!!
                    left_adapter!!.refresh(list)
                }
            }
        }
    }

    override fun onError(result: Int, error: Any?) {

    }

    var left_adapter: CommonAdapter<ZDModel.DataBean>? = null
    var list: ArrayList<ZDModel.DataBean> = ArrayList()
    override fun initView(view: View?) {

    }

    override fun init(savedInstanceState: Bundle?) {
        left_adapter = object : CommonAdapter<ZDModel.DataBean>(CapitalActivity.context, list, R.layout.item_jl) {
            override fun convert(holder: CommonViewHolder, model: ZDModel.DataBean, position: Int) {
                holder.setText(R.id.left_tv, model.actionTime)
                holder.setText(R.id.center_tv, model.amount)
                //0申请中，1，2，9成功，3失败
                when (model.state) {
                    "0" -> holder.setText(R.id.right_tv, "申请中")
                    "3" -> holder.setText(R.id.right_tv, "失败")
                    else -> holder.setText(R.id.right_tv, "充值成功")
                }
            }
        }
        main_lv.adapter = left_adapter
        main_lv.setOnItemClickListener{parent, view, position, id ->
            var dialog=QuKuanDialog(context,list.get(position));
            dialog.show();
        }
        getModule(UserModule::class.java, this).get_zj_list("1", "10")
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
}