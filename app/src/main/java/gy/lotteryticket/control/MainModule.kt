package gy.lotteryticket.control

import android.content.Context
import android.text.TextUtils
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.config.command
import gy.lotteryticket.method.Utils
import gy.lotteryticket.method.Utils.string2MD5
import gy.lotteryticket.model.NormalRequest
import gy.lotteryticket.model.UserModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * 主要的请求接口
 * Created by Finder丶畅畅 on 2017/11/12 20:51
 * QQ群481606175
 */
class MainModule : BaseModule {
    constructor(context: Context?) : super(context)

    /**
     * 基本参数请求
     * */
    fun get_jb_main() {
        var map = HashMap<String, String>()
        map.put("type", "0")
        HttpUtils<String>().get(url.normal + "ylMain.php", command.login, map, this)
    }

    /**
     * 用户信息(登录返回基本信息，个人资料，刷新用户金额反水金额)
     * @param type
     * 1，	登录，基本信息
     * 2，	刷新用户金额，反水金
     * */
    fun get_user_login(username: String, pwd: String, type: Int) {
        var map = HashMap<String, String>()
        map.put("type", type.toString())
        map.put("username", username)
        map.put("password", string2MD5(pwd))
        HttpUtils<String>().get(url.normal + "ylUser.php", command.login + 1, map, this)
    }

    /**
     * 注册
     * */
    fun user_reg(user: UserModel) {
        var map = HashMap<String, String>()

        HttpUtils<String>().get(url.normal + "ylUserReg.php", command.login + 2, map, this)
    }

    /**
     * 修改密码（修改密码，修改资金密码）（4个密码参数都为Md5加密32位小写）
     * */
    fun change_pwd() {
        var map = HashMap<String, String>()
        map.put("username", "0")//用户帐号
        map.put("password", "0")//用户密码
        map.put("coinPassword", "0")//取款密码
        map.put("type", "0")//密码类型-1，登录密码2资金密码
        map.put("password2", "0")//新登录密码 type=1必传
        map.put("coinPassword2", "0")//新资金密码 type=2必传

        HttpUtils<String>().get(url.normal + "ylUserUPass.php", command.login + 3, map, this)
    }
    /**
     * 银行卡（查询银行卡,查询银行列表）
     * */
    fun get_bank() {
        var map = HashMap<String, String>()
        map.put("type", "0")//1，彩种列表  2，开奖结果
        map.put("uid", Utils.getCache(sp.user_id))//用户uid
        HttpUtils<String>().get(url.normal + "ylUserBank.php", command.login+4, map, this)
    }
    /**
     * .php银行卡（添加银行卡，修改银行卡）
     * */
    fun add_update_card() {
        var map = HashMap<String, String>()
        map.put("type", "0")//1，彩种列表  2，开奖结果
        map.put("uid", Utils.getCache(sp.user_id))//用户uid
        map.put("czid", "0")//彩种id  type=1不传。 type=2传彩种列表返回的id，默认1（北京赛车）
        HttpUtils<String>().get(url.normal + "ylUserBankSet.php", command.login, map, this)
    }

    /**
     * 彩种列表（彩种列表，开奖结果）
     * */
    fun check_version() {
        var map = HashMap<String, String>()
        map.put("type", "0")//1，彩种列表  2，开奖结果
        map.put("uid", Utils.getCache(sp.user_id))//用户uid
        map.put("czid", "0")//彩种id  type=1不传。 type=2传彩种列表返回的id，默认1（北京赛车）
        HttpUtils<String>().get(url.normal + "ylUserType.php", command.login, map, this)
    }
}