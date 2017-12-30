package gy.lotteryticket.model;

import java.util.List;

/**
 * Created by JX on 2017/12/28.
 */

public class TodayModel {


    /**
     * totalCount : 2
     * totalMoney : 200
     * totalShuyingMoney : -2
     * totalRebateMoney : 0.00
     */

    private String totalCount;
    private String totalMoney;
    private String totalShuyingMoney;
    private String totalRebateMoney;
    private List<TodayItemModel> data;

    public List<TodayItemModel> getData() {
        return data;
    }

    public void setData(List<TodayItemModel> data) {
        this.data = data;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTotalShuyingMoney() {
        return totalShuyingMoney;
    }

    public void setTotalShuyingMoney(String totalShuyingMoney) {
        this.totalShuyingMoney = totalShuyingMoney;
    }

    public String getTotalRebateMoney() {
        return totalRebateMoney;
    }

    public void setTotalRebateMoney(String totalRebateMoney) {
        this.totalRebateMoney = totalRebateMoney;
    }
}
