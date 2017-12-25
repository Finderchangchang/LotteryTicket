package gy.lotteryticket.model;

/**
 * 彩种Model
 * Created by Finder丶畅畅 on 2017/12/23 21:49
 * QQ群481606175
 */

public class CZModel {

    /**
     * id : 1
     * name : cqssc
     * title : 重庆时时彩
     * onGetNoed : noHdCQSSC
     * data_ftime : 60
     * num : 120
     * kjtime : 2017-12-25 20:21:00
     * sytime : 214
     */

    private String id;
    private String name;
    private String title;
    private String onGetNoed;
    private String data_ftime;
    private String num;
    private String kjtime;
    private int sytime;

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

    public String getOnGetNoed() {
        return onGetNoed;
    }

    public void setOnGetNoed(String onGetNoed) {
        this.onGetNoed = onGetNoed;
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

    public int getSytime() {
        return sytime;
    }

    public void setSytime(int sytime) {
        this.sytime = sytime;
    }
}
