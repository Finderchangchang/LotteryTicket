package gy.lotteryticket.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Finder丶畅畅 on 2017/12/28 21:57
 * QQ群481606175
 */

public class ZDModel implements Serializable {

    /**
     * totalCount : 1
     * totalMoney : 10
     * totalShuyingMoney : 408.8
     * totalRebateMoney : 0.00
     * data : [{"zjCount":"0","lotteryNo":"","id":"633","wjorderId":"20171228153904LEdVRl31DN","actionTime":"2017-12-28 15:39:04","type":"50","title":"北京赛车(PK10)","actionNo":"658587","Groupname":"冠、亚军和","actionData":"3","odds":"41.880","money":"10","rebate":"0.00000","rebateMoney":"0.00","keying":"408.800","state":"撤单"}]
     */

    private int totalCount;
    private int totalMoney;
    private double totalShuyingMoney;
    private String totalRebateMoney;
    private List<DataBean> data;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getTotalShuyingMoney() {
        return totalShuyingMoney;
    }

    public void setTotalShuyingMoney(double totalShuyingMoney) {
        this.totalShuyingMoney = totalShuyingMoney;
    }

    public String getTotalRebateMoney() {
        return totalRebateMoney;
    }

    public void setTotalRebateMoney(String totalRebateMoney) {
        this.totalRebateMoney = totalRebateMoney;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * zjCount : 0
         * lotteryNo :
         * id : 633
         * wjorderId : 20171228153904LEdVRl31DN
         * actionTime : 2017-12-28 15:39:04
         * type : 50
         * title : 北京赛车(PK10)
         * actionNo : 658587
         * Groupname : 冠、亚军和
         * actionData : 3
         * odds : 41.880
         * money : 10
         * rebate : 0.00000
         * rebateMoney : 0.00
         * keying : 408.800
         * state : 撤单
         */

        private String zjCount;
        private String lotteryNo;
        private String id;
        private String wjorderId;
        private String actionTime;
        private String type;
        private String title;
        private String actionNo;
        private String groupname;
        private String actionData;
        private String odds;
        private String money;
        private String rebate;
        private String rebateMoney;
        private String keying;
        private String state;
        private String amount;//金额
        private String info;//描述
        private String fdTime;//封单时间
        private String panid;//1显示A 2显示B

        private String bankName;//提现银行
        private String account;//卡号
        private String flag;//
        private String userAccount;//持卡人
        private String username;//用户名

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getPanid() {
            return panid;
        }

        public void setPanid(String panid) {
            this.panid = panid;
        }

        public String getFdTime() {
            return fdTime;
        }

        public void setFdTime(String fdTime) {
            this.fdTime = fdTime;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getZjCount() {
            return zjCount;
        }

        public void setZjCount(String zjCount) {
            this.zjCount = zjCount;
        }

        public String getLotteryNo() {
            return lotteryNo;
        }

        public void setLotteryNo(String lotteryNo) {
            this.lotteryNo = lotteryNo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWjorderId() {
            return wjorderId;
        }

        public void setWjorderId(String wjorderId) {
            this.wjorderId = wjorderId;
        }

        public String getActionTime() {
            return actionTime;
        }

        public void setActionTime(String actionTime) {
            this.actionTime = actionTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActionNo() {
            return actionNo;
        }

        public void setActionNo(String actionNo) {
            this.actionNo = actionNo;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getActionData() {
            return actionData;
        }

        public void setActionData(String actionData) {
            this.actionData = actionData;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }

        public String getRebateMoney() {
            return rebateMoney;
        }

        public void setRebateMoney(String rebateMoney) {
            this.rebateMoney = rebateMoney;
        }

        public String getKeying() {
            return keying;
        }

        public void setKeying(String keying) {
            this.keying = keying;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
