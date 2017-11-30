package gy.lotteryticket.model

/**
 * Created by Finder丶畅畅 on 2017/11/28 19:40
 * QQ群481606175
 */
class LeftClickModel {
    constructor(name: String, checked: Boolean) {
        this.checked = checked
        this.name = name
    }

    constructor(checked: Boolean, click_position: Int, name: String) {
        this.checked = checked
        this.click_position = click_position
        this.name = name
    }


    var checked = false//是否点击
    var click_position = 0//左侧点击的位置
    var name = ""//点击的内容
}