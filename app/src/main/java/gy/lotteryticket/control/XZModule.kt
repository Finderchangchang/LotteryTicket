package gy.lotteryticket.control

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gd.mmanage.callback.LzyResponse
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.config.command
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.ObjectRequest
import gy.lotteryticket.model.TagModel
import gy.lotteryticket.model.XZModel
import gy.lotteryticket.model.ZDModel
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
    fun get_tz(cz_id: String) {
        var map = HashMap<String, String>()
        map.put("type", "1")
        map.put("uid", Utils.getCache(sp.user_id))
        map.put("czid", cz_id)//彩种ID
        map.put("panid", Utils.getCache(sp.pan_id))//盘ID
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
        map.put("username", Utils.getCache(sp.user_name))//从缓存抓取
        HttpUtils<String>().get(url.normal + "ylBetsAdd.php", command.xz + 1, map, this)
    }

    /**
     * 切换AB盘
     * */
    fun change_ab() {
        var map = HashMap<String, String>()
        map.put("type", "1")
        map.put("uid", Utils.getCache(sp.user_id))
        map.put("panid", Utils.getCache(sp.pan_id))//盘ID
        HttpUtils<String>().get(url.normal + "ylUserPan.php", command.xz + 2, map, this)
    }

    fun get_cz_list(type: String) {
        get_cz_list(type, "")
    }

    /**
     * 获得彩种列表
     *
     * type 1，彩种列表
     *      2，开奖结果
     * czid type=1不传。
     *      type=2传彩种列表返回的id，默认1（北京赛车）
     * */
    fun get_cz_list(type: String, cz_id: String) {
        var map = HashMap<String, String>()
        map.put("type", type)
        map.put("czid", cz_id)
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

    /**
     * 今日已结=今日输赢，即时驻单
     * @param type 1，今日已结，今日输赢 2，即时驻单
     * */
    fun get_dz_last(type: String) {
        var map = HashMap<String, String>()
        map.put("uid", Utils.getCache(sp.user_id))
        map.put("type", type)
        HttpUtils<ObjectRequest<ZDModel>>().new_get(url.normal + "ylUserBets.php", command.xz + 5, map, this, object : TypeToken<ObjectRequest<ZDModel>>() {})
    }

    /**
     * 撤销订单
     * @param id 订单ID
     * @param time 封单时间
     * */
    fun get_cx(id: String, time: String) {
        var map = HashMap<String, String>()
        map.put("id", id)
        map.put("fdTime", time)
        HttpUtils<ObjectRequest<String>>().new_get(url.normal + "ylUserDeleteLotteryData.php", command.xz + 7, map, this, object : TypeToken<ObjectRequest<String>>() {})
    }

    fun get_gz_info(type: String) {
        //0全部
        //1滚动文字广告，
        //2在线客服地址，
        //3聊天室地址，  //4电脑版地址  //5支付宝充值地址
        //6微信充值地址  //10重庆游戏规则  //20赛车规则  30飞艇规则  40快三规则 50PC蛋蛋规则   60六合彩规则
        var map = HashMap<String, String>()
        var key = ""
        when (type) {
            "50" -> key = "20"
            "1" -> key = "10"
            "66" -> key = "50"
            "55" -> key = "30"
            "70" -> key = "60"
            "10" -> key = "40"
            else -> key = type
        }
        map.put("type", key)
        HttpUtils<LzyResponse<TagModel>>().new_get(url.normal + "ylMain.php", command.xz + 6, map, this, object : TypeToken<LzyResponse<TagModel>>() {})

    }
}