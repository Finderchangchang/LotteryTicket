package gy.lotteryticket.model;

/**
 * 开奖结果
 * Created by Finder丶畅畅 on 2017/12/24 10:49
 * QQ群481606175
 */

public class KJResultModel {

    /**
     * id : 55
     * name : xyft
     * title : 幸运飞艇
     * data_ftime : 60
     * num : 180
     * kjtime : 2017-12-24 00:39:00
     * kjNum : 139
     * sytime : 177
     * fptime : 117
     * lastNum : 20171019104
     * lastKj : 09,08,02,06,01,05,10,07,04,03
     */

    private String id;
    private String name;
    private String title;
    private String data_ftime;
    private String num;
    private String kjtime;
    private String kjNum;
    private int sytime;
    private int fptime;
    private String lastNum;
    private String lastKj;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData_ftime() {
        return data_ftime;
    }

    public void setData_ftime(String data_ftime) {
        this.data_ftime = data_ftime;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getKjtime() {
        return kjtime;
    }

    public void setKjtime(String kjtime) {
        this.kjtime = kjtime;
    }

    public String getKjNum() {
        return kjNum;
    }

    public void setKjNum(String kjNum) {
        this.kjNum = kjNum;
    }

    public int getSytime() {
        return sytime;
    }

    public void setSytime(int sytime) {
        this.sytime = sytime;
    }

    public int getFptime() {
        return fptime;
    }

    public void setFptime(int fptime) {
        this.fptime = fptime;
    }

    public String getLastNum() {
        return lastNum;
    }

    public void setLastNum(String lastNum) {
        this.lastNum = lastNum;
    }

    public String getLastKj() {
        return lastKj;
    }

    public void setLastKj(String lastKj) {
        this.lastKj = lastKj;
    }
}
