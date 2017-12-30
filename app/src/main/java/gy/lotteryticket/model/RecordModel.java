package gy.lotteryticket.model;

import java.util.List;

/**
 * Created by JX on 2017/12/28.
 */

public class RecordModel {

    /**
     * totalCount : 2
     * totalMoney : 200
     * totalRebateMoney : 0
     */

    private String totalCount;
    private String totalMoney;
    private String totalRebateMoney;
    private List<RecordItemModel> data;

    public List<RecordItemModel> getData() {
        return data;
    }

    public void setData(List<RecordItemModel> data) {
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

    public String getTotalRebateMoney() {
        return totalRebateMoney;
    }

    public void setTotalRebateMoney(String totalRebateMoney) {
        this.totalRebateMoney = totalRebateMoney;
    }
}
