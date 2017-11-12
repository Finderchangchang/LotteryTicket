package gy.lotteryticket.control

import android.content.Context
import gy.lotteryticket.base.BaseModule
import gy.lotteryticket.model.NormalRequest

/**
 * Created by Finder丶畅畅 on 2017/11/12 20:51
 * QQ群481606175
 */
class LoginModule : BaseModule {
    constructor(context: Context?) : super(context)

    fun user_login(name: String, password: String) {
        callback(1000, NormalRequest<String>(1, true, "登录成功"))
    }

    /**
     * 检查更新
     * */
    fun check_version() {
        callback(10001, NormalRequest<String>(true, "更新提示"))
    }
}