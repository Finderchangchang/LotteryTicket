package gy.lotteryticket.ui.user

import android.content.Intent
import android.os.Bundle
import com.arialyy.frame.module.AbsModule
import com.google.gson.JsonArray
import gy.lotteryticket.R
import gy.lotteryticket.base.BaseActivity
import gy.lotteryticket.control.UserModule
import gy.lotteryticket.databinding.ActivityMessageBinding
import gy.lotteryticket.method.CommonAdapter
import gy.lotteryticket.method.CommonViewHolder
import gy.lotteryticket.model.GGModel
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.ObjectRequest
import gy.lotteryticket.model.RightClick2Model
import kotlinx.android.synthetic.main.activity_message.*
import org.json.JSONArray

/**
 * 消息中心
 * Created by JX on 2017/12/21.
 */

class MessageActivity : BaseActivity<ActivityMessageBinding>(), AbsModule.OnCallback {
    var list = ArrayList<GGModel>()
    var adapter: CommonAdapter<GGModel>? = null//资讯

    override fun onSuccess(result: Int, success: Any?) {
        success as NormalRequest<List<GGModel>>
        list = success.obj as ArrayList<GGModel>
        adapter!!.refresh(list)
    }

    override fun onError(result: Int, error: Any?) {

    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        getModule(UserModule::class.java, this).get_message_list()
        adapter = object : CommonAdapter<GGModel>(this, list, R.layout.item_message) {
            override fun convert(holder: CommonViewHolder, num: GGModel, position: Int) {
                holder.setText(R.id.tv, num.title)
            }
        }
        lv.adapter = adapter
        lv.setOnItemClickListener { parent, view, position, id ->
            startActivity(Intent(this@MessageActivity, MessageDetailActivity::class.java).putExtra("model", list[position]))
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_message
    }
}
