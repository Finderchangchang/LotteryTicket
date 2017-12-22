package gy.lotteryticket.control

import android.content.Context
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.config.command
import gy.lotteryticket.method.Utils
import java.util.HashMap

/**
 * 下注页面
 * Created by Finder丶畅畅 on 2017/11/12 20:51
 * QQ群481606175
 */
class XZModule : BaseModule {
    constructor(context: Context?) : super(context)

    /**
     * 获得投注数据
     * @param cz_id 彩种ID
     * 1，	重庆
     * 10，	 快3
     * 500，	赛车
     * 55，	飞艇
     * 66，	PC蛋蛋
     * 70，	六合彩
     * @param pan_id 1，2用户登录接口获得
     * */
    fun get_tz(cz_id: String, pan_id: String) {
        var map = HashMap<String, String>()
        map.put("type", "1")
        map.put("uid", Utils.getCache(sp.user_id))
        map.put("czid", cz_id)//彩种ID
        map.put("panid", pan_id)//盘ID
        HttpUtils<String>().get(url.normal + "ylBets.php", command.xz, map, this)
    }
}