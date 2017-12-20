package gy.lotteryticket.control

import android.content.Context
import android.text.TextUtils
import gd.mmanage.config.url
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.config.command
import gy.lotteryticket.method.Utils.string2MD5
import gy.lotteryticket.model.NormalRequest
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Finder丶畅畅 on 2017/11/12 20:51
 * QQ群481606175
 */
class LoginModule : BaseModule {
    constructor(context: Context?) : super(context)

    fun user_login(name: String, password: String) {

    }

    //获得时间戳
    fun dateToStamp(): String {
        val now = System.currentTimeMillis() / 1000L
        val daySecond = (60 * 60 * 24).toLong()
        val dayTime = now - (now + 8 * 3600) % daySecond
        return dayTime.toString()
    }

    /**
     * 检查更新
     * */
    fun check_version() {
        var key = "7f8ddadbe4e91911a58ed525b3510748"
        var ramdom = "7788"
        var time = dateToStamp()
        var map = HashMap<String, String>()
        map.put("signStr", key)
        map.put("Sign", string2MD5("signStr=$ramdom&key=$key&timeStamp=$time"))
        HttpUtils<String>().post(url.key + "ylMain.php", command.login, map, this)

    }
}