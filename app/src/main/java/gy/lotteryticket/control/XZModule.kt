package gy.lotteryticket.control

import android.content.Context
import com.google.gson.Gson
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.config.command
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.XZModel
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
    fun get_xz(cz_id: String, qiHao: String, money: String, totalMoney: String, xzs: ArrayList<XZModel>) {
        var map = HashMap<String, String>()
        map.put("type", "1")
        map.put("panid", "1")//盘ID-从缓存抓取
        map.put("czid", cz_id)//彩种ID
        map.put("turnNum", qiHao)//投注期号
        map.put("utype", "0")//用户类型 从缓存抓取 登录接口返回的type
        map.put("money", money)//投注得金额
        map.put("totalMoney", totalMoney)//是	投注总金额	投注金额 * 注数
        map.put("betList", Gson().toJson(xzs))//玩法列表	格式与“投注页面数据加载”得借口返回得数据一样
        map.put("uid", Utils.getCache(sp.user_id))//从缓存抓取
        HttpUtils<String>().get(url.normal + "ylBetsAdd.php", command.xz + 1, map, this)
    }

    /**
     * 切换AB盘
     * */
    fun change_ab() {
        var map = HashMap<String, String>()
        map.put("type", "1")
        map.put("uid", Utils.getCache(sp.user_id))
        when (Utils.getCache(sp.pan_id)) {
            "1" -> map.put("panid", "2")//盘ID
            else -> map.put("panid", "1")//盘ID
        }
        HttpUtils<String>().get(url.normal + "ylUserPan.php", command.xz + 2, map, this)
    }

    /**
     * 获得彩种列表
     *
     * type 1，彩种列表
     *      2，开奖结果
     * czid type=1不传。
     *      type=2传彩种列表返回的id，默认1（北京赛车）
     * */
    fun get_cz_list() {
        var map = HashMap<String, String>()
        map.put("type", "1")
        map.put("uid", Utils.getCache(sp.user_id))
        HttpUtils<String>().get(url.normal + "ylUserType.php", command.xz + 3, map, this)
    }

    /**
     * 根据彩种ID 刷新开奖号码，期号
     * */
    fun get_zj_last(cz_id: String) {
        var map = HashMap<String, String>()
        map.put("uid", Utils.getCache(sp.user_id))
        map.put("czid", cz_id)//彩种ID
        HttpUtils<String>().get(url.normal + "ylBetsGetNum.php", command.xz + 4, map, this)
    }
}