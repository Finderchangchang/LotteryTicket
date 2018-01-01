package gy.lotteryticket.model;

import java.io.Serializable;

/**
 * 广告Model
 * Created by Finder丶畅畅 on 2018/1/2 00:31
 * QQ群481606175
 */

public class GGModel implements Serializable {
    public String title;
    public String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
