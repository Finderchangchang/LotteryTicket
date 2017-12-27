package gy.lotteryticket.control

import android.content.Context
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.config.command
import gy.lotteryticket.method.Utils
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
        HttpUtils<String>().get(url.normal + "ylUserMoneyJilu.php", command.xz + 4, map, this)
    }
}