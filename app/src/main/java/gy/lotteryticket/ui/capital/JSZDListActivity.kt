package gy.lotteryticket.ui.capital

import android.databinding.DataBindingUtil.setContentView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.arialyy.frame.module.AbsModule
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.UserModule
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityJszdlistBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.XZModel
import gy.lotteryticket.model.ZDModel
import gy.lotteryticket.ui.main.CapitalActivity
import kotlinx.android.synthetic.main.activity_jszdlist.*
import kotlinx.android.synthetic.main.frag_draw_list.*

class JSZDListActivity : BaseActivity<ActivityJszdlistBinding>(), AbsModule.OnCallback {
    override fun onError(result: Int, error: Any?) {

    }

    var adapter: CommonAdapter<ZDModel.DataBean>? = null
    var list: ArrayList<ZDModel.DataBean> = ArrayList()
    var type: Int = 0;
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        getModule(XZModule::class.java, this).get_dz_last("2")
        type = intent.getIntExtra("type", 0)

        when (type) {
            0 -> {
                jszd_title.text = "可赢金额"
                title_bar.center_str = "即时注单"
                jszd_ll_1.visibility = View.VISIBLE
                jszd_ll_2.visibility = View.GONE
            }
            1 -> {
                jszd_title.text = "输赢金额"
                title_bar.center_str = "今日输赢"
                jszd_ll_1.visibility = View.GONE
                jszd_ll_2.visibility = View.VISIBLE
            }
        }

        adapter = object : CommonAdapter<ZDModel.DataBean>(this, list, R.layout.item_jszd) {
            override fun convert(holder: CommonViewHolder, model: ZDModel.DataBean, position: Int) {
                holder.setText(R.id.item_jszd_qh, model.actionNo)
                holder.setText(R.id.item_jszd_xzje, model.money)

                holder.setText(R.id.item_jszd_xzmx, model.info)
                when (type) {
                    0 -> {
                        holder.setText(R.id.item_jszd_kyje, model.keying)
                    }
                    1 -> {
                        holder.setText(R.id.item_jszd_kyje, model.keying)
                    }
                }

            }
        }
        jszd_lv.adapter = adapter
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_jszdlist;
    }

    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.xz + 5 -> {//加载配置数据
                success as NormalRequest<ZDModel>
                if (success.code == 0 && success.obj != null) {
                    list = (success.obj!!.data as ArrayList<ZDModel.DataBean>?)!!
                    adapter!!.refresh(list)
                    when (type) {
                        0 -> {
                            jszd_allmoney.text = success.obj!!.totalShuyingMoney.toString()
                        }
                        1 -> {

                            jszd_xzmoney.text = success.obj!!.totalMoney.toString()
                            jszd_fsmoney.text = success.obj!!.totalRebateMoney.toString()
                            jszd_all2money.text = success.obj!!.totalShuyingMoney.toString()
                        }
                    }

                }
            }
        }
    }


}
