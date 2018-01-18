package gy.lotteryticket.ui.capital

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arialyy.frame.module.AbsModule

import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.databinding.ActivityQupwdBinding
import gy.lotteryticket.databinding.ActivityXzmxBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.model.RecordItemItemModel
import gy.lotteryticket.model.ZDModel

class XZMXActivity : BaseActivity<ActivityXzmxBinding>(), AbsModule.OnCallback  {
    var list: ArrayList<RecordItemItemModel> = ArrayList()
    var adapter: CommonAdapter<RecordItemItemModel>? = null
    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        adapter = object : CommonAdapter<RecordItemItemModel>(this, list, R.layout.item_xzmx) {
            override fun convert(holder: CommonViewHolder?, t: RecordItemItemModel?, position: Int) {
              holder!!.setText(R.id.item_caizhong,t!!.title)
                holder!!.setText(R.id.item_bishu,t!!.zjCount)
                holder!!.setText(R.id.item_jine,t!!.title)
                holder!!.setText(R.id.item_shuying,t!!.title)

            }

        };
    }
    override fun onSuccess(result: Int, success: Any?) {

    }

    override fun onError(result: Int, error: Any?) {

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_xzmx
    }

}
