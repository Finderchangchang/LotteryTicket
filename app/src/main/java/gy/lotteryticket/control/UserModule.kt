package gy.lotteryticket.control

import android.content.Context
import com.google.gson.reflect.TypeToken
import gd.mmanage.callback.LzyResponse
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.config.command
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.GGModel
import gy.lotteryticket.model.ObjectRequest
import gy.lotteryticket.model.ZDModel
import java.util.HashMap

/**
 * 用户逻辑管理
 * Created by Administrator on 2017/12/27.
 */
class UserModule : BaseModule {
    constructor(context: Context?) : super(context)

    /**
     * 资金记录（存款记录，取款记录）
     * @param type 1，存款记录 2，取款记录
     *@param state -1申请中 1成功的 3失败的
     * */
    fun get_zj_list(type: String, state: String) {
        var map = HashMap<String, String>()
        map.put("uid", Utils.getCache(sp.user_id))
        map.put("type", type)
        map.put("state", state)
        HttpUtils<ObjectRequest<ZDModel>>().new_get(url.normal + "ylUserMoneyJilu.php", command.user, map, this, object : TypeToken<ObjectRequest<ZDModel>>() {})
    }

    /**
     * 加载通知通告
     * */
    fun get_message_list() {
        HttpUtils<LzyResponse<GGModel>>().new_get(url.normal + "ylUserGG.php", command.user, null, this, object : TypeToken<LzyResponse<GGModel>>() {})
    }
}