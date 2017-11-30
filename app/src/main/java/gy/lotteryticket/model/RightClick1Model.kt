package gy.lotteryticket.model

/**
 * 右侧点击的item
 * Created by Finder丶畅畅 on 2017/11/28 19:40
 * QQ群481606175
 */
class RightClick1Model {
    constructor(name: String, checked: Boolean) {
        this.checked = checked
        this.name = name
    }

    var checked = false//是否点击
    var name = ""//点击的内容
}