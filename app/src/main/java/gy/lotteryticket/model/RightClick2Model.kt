package gy.lotteryticket.model

/**
 * 冠亚和值
 * Created by Finder丶畅畅 on 2017/11/28 19:40
 * QQ群481606175
 */
class RightClick2Model {
    constructor(left_name: String, right_pl: String, checked: Boolean) {
        this.checked = checked
        this.left_name = left_name
        this.right_pl = right_pl
    }

    var checked = false//是否点击
    var left_name = ""//左侧描述
    var right_pl = ""//赔率
    var style = 1
}