package gy.lotteryticket.ui.capital

import android.databinding.DataBindingUtil.setContentView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.arialyy.frame.module.AbsModule
import com.google.gson.JsonArray
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.UserModule
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityJszdlistBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.RecordItemModel
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
    var control: XZModule? = null
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        control = getModule(XZModule::class.java, this)

        type = intent.getIntExtra("type", 0)

        when (type) {
            0 -> {
                jszd_title.text = "可赢金额"
                title_bar.center_str = "即时注单"
                jszd_ll_1.visibility = View.VISIBLE
                jszd_ll_2.visibility = View.GONE
                control!!.get_dz_last("2")
            }
            1 -> {
                jszd_title.text = "输赢金额"
                title_bar.center_str = "今日输赢"
                jszd_ll_1.visibility = View.GONE
                jszd_ll_2.visibility = View.VISIBLE
                control!!.get_dz_last("2")
            }
            2 -> {
                jszd_title.text = "下注详情"
                title_bar.center_str = "下注详情"
                jszd_ll_1.visibility = View.GONE
                jszd_ll_2.visibility = View.VISIBLE
                var model: RecordItemModel = intent.getSerializableExtra("model") as RecordItemModel
                list = model.data as ArrayList<ZDModel.DataBean>
                adapter!!.refresh(list)
            }
        }

        adapter = object : CommonAdapter<ZDModel.DataBean>(this, list, R.layout.item_jszd) {
            override fun convert(holder: CommonViewHolder, model: ZDModel.DataBean, position: Int) {
                holder.setText(R.id.item_jszd_qh, model.actionNo)
                holder.setText(R.id.item_jszd_xzje, model.money)
                if (type == 0) {
                    holder.setVisible(R.id.cx_btn, true)
                    holder.setOnClickListener(R.id.cx_btn) {
                        control!!.get_cx(model.wjorderId, model.actionTime)
                    }
                } else {
                    holder.setVisible(R.id.cx_btn, false)
                }
                holder.setText(R.id.item_jszd_xzmx, model.getGroupname() + "-" + model.getActionData() + "\n" + model.getOdds().toDoubleOrNull())
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
            command.xz + 7 -> {//撤销订单
                success as NormalRequest<String>
                if (success.code == 0) {
                    control!!.get_dz_last("2")
                }
                toast(success.message)
            }
        }
    }
}
