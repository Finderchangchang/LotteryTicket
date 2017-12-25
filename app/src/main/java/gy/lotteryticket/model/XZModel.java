package gy.lotteryticket.model;

/**
 * 下注Model
 * Created by Finder丶畅畅 on 2017/12/24 09:30
 * QQ群481606175
 */

public class XZModel {
    public XZModel(String name) {
        this.name = name;
    }

    /**
     * id : 6611201
     * name : 大
     * code : DA
     * played_groupid : 112
     * odds : 1.980
     * rebate : 0.000
     * ruleFun : PCDDZH
     * minMoney : 1
     * maxMoney : 500000
     */

    private String id;
    private String name;
    private String code;
    private String played_groupid;
    private String odds;
    private String rebate;
    private String ruleFun;
    private String minMoney;
    private String maxMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlayed_groupid() {
        return played_groupid;
    }

    public void setPlayed_groupid(String played_groupid) {
        this.played_groupid = played_groupid;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getRuleFun() {
        return ruleFun;
    }

    public void setRuleFun(String ruleFun) {
        this.ruleFun = ruleFun;
    }

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
    }

    public String getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(String maxMoney) {
        this.maxMoney = maxMoney;
    }
}
