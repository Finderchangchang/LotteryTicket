package gy.lotteryticket.model

/**
 * 正常请求
 * Created by lenovo on 2017/12/21.
 */

class NormalModel<T>{
    var state:Int=0
    var msg=""
    var dataList:List<T>?=null
}
