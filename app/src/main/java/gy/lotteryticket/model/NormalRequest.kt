package gy.lotteryticket.model

/**
 * Created by Finder丶畅畅 on 2017/11/12 20:52
 * QQ群481606175
 */

class NormalRequest<T> {
    constructor(code: Int, result: Boolean, message: String, obj: T?) {
        this.code = code
        this.result = result
        this.obj = obj
        this.message = message
    }

    /**
     * @param code 请求码
     * @param message 需要显示的消息
     * @param obj 需要解析的数据
     * */
    constructor(code: Int, message: String?, obj: T?) {
        this.code = code
        this.obj = obj
        this.message = message
    }


    var code: Int = 0//请求码0:请求成功。1：失败。2：报错
    var result: Boolean = false//是否解析成功
    var message: String? = ""//提示的消息
    var obj: T? = null
}