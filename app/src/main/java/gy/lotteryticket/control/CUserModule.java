package gy.lotteryticket.control;

import android.content.Context;

import java.util.HashMap;

import gy.lotteryticket.base.BaseModule;

/**
 * Created by JX on 2017/12/26.
 */

public class CUserModule extends BaseModule {

    private String key = "http://103.238.225.166:88/";
    private String normal = key + "Api/";

    public static int GETBANK = 111111;

    public CUserModule(Context context) {
        super(context);
    }

    /**
     * type 1，查询银行卡
     * 2，查询银行列表
     *
     * @param type
     */
    public void getBankList(int type ,String uid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(type));
        map.put("uid", uid);//从缓存抓取
        new HttpUtils<String>().get(normal + "ylUserBank.php", GETBANK, map, this);
    }
}
