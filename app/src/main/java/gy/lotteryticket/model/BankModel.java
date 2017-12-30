package gy.lotteryticket.model;

import java.io.Serializable;

/**
 * Created by JX on 2017/12/28.
 */

public class BankModel implements Serializable {


    /**
     * id : 108
     * uid : 840
     * admin : 0
     * enable : 1
     * bankId : 4
     * username : 邓冬莲
     * account : 6228481231343655916
     * editEnable : 0
     * countname :
     * xgtime : 0
     * bdtime : 0
     * bankName : 中国农业银行
     */

    private String id;
    private String uid;
    private String admin;
    private String enable;
    private String bankId;
    private String username;
    private String account;
    private String editEnable;
    private String countname;
    private String xgtime;
    private String bdtime;
    private String bankName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEditEnable() {
        return editEnable;
    }

    public void setEditEnable(String editEnable) {
        this.editEnable = editEnable;
    }

    public String getCountname() {
        return countname;
    }

    public void setCountname(String countname) {
        this.countname = countname;
    }

    public String getXgtime() {
        return xgtime;
    }

    public void setXgtime(String xgtime) {
        this.xgtime = xgtime;
    }

    public String getBdtime() {
        return bdtime;
    }

    public void setBdtime(String bdtime) {
        this.bdtime = bdtime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
