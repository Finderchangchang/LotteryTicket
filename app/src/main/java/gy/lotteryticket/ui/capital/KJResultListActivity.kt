package gy.lotteryticket.ui.capital

import android.databinding.repacked.google.common.io.Resources
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.GridView
import com.arialyy.frame.module.AbsModule
import com.google.gson.Gson
import com.google.gson.JsonArray
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.config.command
import gy.lotteryticket.control.XZModule
import gy.lotteryticket.databinding.ActivityJszdlistBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.model.CZModel
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.ZDModel
import kotlinx.android.synthetic.main.activity_kjresultlist.*

/**
 * 开奖结果
 * */
class KJResultListActivity : BaseActivity<ActivityJszdlistBinding>(), AbsModule.OnCallback {
    override fun onError(result: Int, error: Any?) {

    }

    var adapter: CommonAdapter<CZModel>? = null
    var type = ""
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        type = intent.getStringExtra("position")
        getModule(XZModule::class.java, this).get_cz_list("2", type)
        title_adapter = object : CommonAdapter<String>(this, title_list, R.layout.item_title) {
            override fun convert(holder: CommonViewHolder, model: String, position: Int) {
                holder.setGImage(R.id.iv, getResource(model))
            }
        }
        adapter = object : CommonAdapter<CZModel>(this, array_list, R.layout.item_kj_result) {
            override fun convert(holder: CommonViewHolder, model: CZModel, position: Int) {
                holder.setText(R.id.item_qh, model.number)
                //holder.setText(R.id.item_time, model.time)
                if (!TextUtils.isEmpty(model.data)) {
                    var list = model.data.split(",")
                    title_list.clear()
                    for (i in list) {
                        title_list.add(i)
                    }
                    var gv: GridView = holder.getView(R.id.gv)
                    gv.adapter = title_adapter
                }
            }
        }
        jszd_lv.adapter = adapter
    }

    fun getResource(imageName: String): Int {
        val ctx = baseContext
        var url = imageName
        if (!TextUtils.isEmpty(url) && url.substring(0, 1) == "0") {
            url = url.substring(1)
        }
        return resources.getIdentifier("num_" + url, "mipmap", ctx.packageName)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_kjresultlist
    }

    var array_list = ArrayList<CZModel>()
    var title_list = ArrayList<String>()
    var title_adapter: CommonAdapter<String>? = null

    override fun onSuccess(result: Int, success: Any?) {
        when (result) {
            command.xz + 3 -> {
                success as NormalRequest<JsonArray>
                if (success.obj != null) {
                    array_list.clear()
                    for (index in 0 until success.obj!!.size()) {
                        array_list.add(Gson().fromJson(success.obj!![index].toString(), CZModel::class.java))
                    }
                    adapter!!.refresh(array_list)
                }
            }
        }
    }
}