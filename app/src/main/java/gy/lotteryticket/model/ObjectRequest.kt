package gy.lotteryticket.model

import java.io.Serializable

/**
 * Created by Finder丶畅畅 on 2017/12/28 22:17
 * QQ群481606175
 */
class ObjectRequest<T> : Serializable {
    var state: Int = 0
    var msg = ""
    var dataList: T? = null
}