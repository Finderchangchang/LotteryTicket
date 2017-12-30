package gy.lotteryticket.ui.user

import android.content.Context
import com.google.gson.reflect.TypeToken
import gd.mmanage.callback.LzyResponse
import gd.mmanage.config.sp
import gd.mmanage.config.url
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.config.command
import gy.lotteryticket.control.HttpUtils
import gy.lotteryticket.method.Utils
import gy.lotteryticket.model.ObjectRequest
import gy.lotteryticket.model.TagModel
import java.util.HashMap

/**
 * 个人信息管理页面
 * Created by Administrator on 2017/12/20.
 */

class UserModule : BaseModule {
    constructor(context: Context?) : super(context)

    /**
     * 密码修改
     * @param new_txt 新密码
     * */
    fun change_pwd(new_txt: String) {
        var map = HashMap<String, String>()
        map.put("username",Utils.getCache(sp.login_name))
        map.put("password",Utils.string2MD5(Utils.getCache(sp.pwd)))
        map.put("type", "1")//登录密码
        map.put("password2", Utils.string2MD5(new_txt))//新密码
        HttpUtils<ObjectRequest<String>>()
                .new_get(url.normal + "ylUserUPass.php", command.login, map, this, object : TypeToken<ObjectRequest<String>>() {})
    }
    /**
     * 取款密码修改
     * @param new_txt 新密码
     * */
    fun change_qu_pwd(new_txt: String) {
        var map = HashMap<String, String>()
        map.put("username",Utils.getCache(sp.login_name))
        map.put("coinPassword",Utils.string2MD5(Utils.getCache(sp.pwd)))
        map.put("type", "2")//取款密码
        map.put("coinPassword2", Utils.string2MD5(new_txt))//新密码
        HttpUtils<LzyResponse<String>>()
                .new_get(url.normal + "ylUserUPass.php", command.login, map, this, object : TypeToken<LzyResponse<String>>() {})
    }
}
