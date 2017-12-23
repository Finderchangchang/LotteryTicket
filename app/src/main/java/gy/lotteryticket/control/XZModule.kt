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
//        map.put("uid", Utils.getCache(sp.user_id))
        map.put("uid", "915")
        map.put("czid", cz_id)//彩种ID
        map.put("panid", pan_id)//盘ID
        HttpUtils<String>().get(url.normal + "ylBets.php", command.xz, map, this)
    }

    /**
     * 下注
     *type	是	接口类型	1，
     *uid	是	用户uid
     *panid	是	盘id	1，2
     *czid	是	采种ID
     *turnNum	是	投注期号
     *utype	是	用户类型	登录接口返回的type
     *money	是	投注得金额
     *totalMoney	是	投注总金额	投注金额 * 注数
     *username	否	用户名称
     *betList	是	玩法列表	格式与“投注页面数据加载”得借口返回得数据一样

     * */
    fun get_xz(cz_id: String, pan_id: String) {
        var map = HashMap<String, String>()
        map.put("type", "1")
        map.put("panid", "1")//盘ID
        map.put("czid", "1")//彩种ID
        map.put("turnNum", "1")//投注期号
        map.put("utype", "1")//用户类型	登录接口返回的type
        map.put("money", "1")//投注得金额
        map.put("totalMoney", "1")//是	投注总金额	投注金额 * 注数
        map.put("betList", "1")//玩法列表	格式与“投注页面数据加载”得借口返回得数据一样
        map.put("uid", "915")
        HttpUtils<String>().get(url.normal + "ylBetsAdd.php", command.xz, map, this)
    }

    /**
     * 切换AB盘
     * */
    fun change_ab() {
        var map = HashMap<String, String>()
        map.put("type", "1")
//        map.put("uid", Utils.getCache(sp.user_id))
        map.put("uid", "915")
        when (Utils.getCache(sp.pan_id)) {
            "1" -> Utils.putCache(sp.pan_id, "2")
            else -> Utils.putCache(sp.pan_id, "1")
        }
        map.put("panid", Utils.getCache(sp.pan_id))//盘ID
        HttpUtils<String>().get(url.normal + "ylUserPan.php", command.xz, map, this)
    }
}