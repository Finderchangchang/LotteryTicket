package gy.lotteryticket.model;

/**
 * 开奖结果Model
 * Created by Finder丶畅畅 on 2017/12/23 21:49
 * QQ群481606175
 */

public class KJModel {

    /**
     * id : 104340
     * type : 1
     * time : 1512641891
     * number : 20171107058
     * data : 6,8,1,6,9
     */

    private String id;
    private String type;
    private String time;
    private String number;
    private String data;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
