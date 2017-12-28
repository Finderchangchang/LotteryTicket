package gy.lotteryticket.model

/**
 * 用户信息（注册）
 * Created by lenovo on 2017/12/21.
 */
class UserModel {
    constructor()
    constructor(username: String) {
        this.username = username
    }

    var username = ""
    var password = ""
    var coinPassword = ""
    var qq = ""
    var name = ""
    var ptype = "1"

    var uid = ""//编号
    var isDelete = ""//
    var enable = ""//
    var gudongId = ""//股东ID
    var zparentId = ""//代理ID
    var parentId = ""//父级ID
    var type = ""//用户类型:3股东，2总代理，1代理，0会员
    var panid = ""//当前所在盘：1A盘，2B盘
    var nickname = ""//昵称
    var coinFanShui = ""//反水总金额
    var coin = ""//余额
    var fcoin = ""//冻结金额
    var fanDian = ""//股东代理返点比例

}