package gy.lotteryticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JX on 2017/12/28.
 */

public class RecordItemModel implements Serializable{


    /**
     * time : 2017-12-09
     * week : 星期六
     * count : 2
     * shuying : -2
     * totalMoney : 200
     * totalRebateMoney : 0
     */

    private String time;
    private String week;
    private String count;
    private String shuying;
    private String totalMoney;
    private String totalRebateMoney;
    private List<RecordItemItemModel> data;

    public List<RecordItemItemModel> getData() {
        return data;
    }

    public void setData(List<RecordItemItemModel> data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getShuying() {
        return shuying;
    }

    public void setShuying(String shuying) {
        this.shuying = shuying;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTotalRebateMoney() {
        return totalRebateMoney;
    }

    public void setTotalRebateMoney(String totalRebateMoney) {
        this.totalRebateMoney = totalRebateMoney;
    }
}
