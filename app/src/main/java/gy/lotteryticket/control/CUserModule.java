package gy.lotteryticket.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import gy.lotteryticket.base.BaseModule;
import gy.lotteryticket.model.ObjectRequest;
import gy.lotteryticket.model.RecordModel;
import gy.lotteryticket.model.TodayModel;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by JX on 2017/12/26.
 */

public class CUserModule extends BaseModule {

    private String key = "http://103.238.225.166:88/";
    private String normal = key + "Api/";

    public static int GETBANK = 111111;
    public static int GETRECORD = 111112;
    public static int GETTODAY = 111113;
    public static int UPDATEBANK = 111114;
    public static int REGISTER = 111115;
    public static int GETMONEY = 111116;//提现到银行卡
    private Context mContext;

    public CUserModule(Context context) {
        super(context);
        mContext = context;
    }

    /**
     * type 1，查询银行卡
     * 2，查询银行列表
     *
     * @param type
     */
    public void getBankList(int type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(type));
        map.put("uid", getUid());//从缓存抓取
        new HttpUtils<String>().get(normal + "ylUserBank.php", GETBANK, map, this);
    }

    /**
     * 1：添加银行卡
     * 2：修改银行卡
     *
     * @param type
     */
    public void updateBank(int type, String bankId, String account, String countname, String username) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(type));
        map.put("uid", getUid());//从缓存抓取
        map.put("bankId", bankId);
        map.put("account", account);
        map.put("countname", countname);
        map.put("username", username);
        new HttpUtils<String>().get(normal + "ylUserBankSet.php", UPDATEBANK, map, this);
    }

    /**
     * 获取下注记录
     *
     * @param type
     */
    public void getRecord(int type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(type));
        map.put("uid", getUid());//从缓存抓取
        new HttpUtils<ObjectRequest<RecordModel>>().new_get(normal + "ylUserBetsAll.php", GETRECORD, map, this, new TypeToken<ObjectRequest<RecordModel>>() {
        });
    }

    /**
     * 获取今日已结
     *
     * @param type
     */
    public void getToday(int type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(type));
        map.put("uid", getUid());//从缓存抓取
        new HttpUtils<ObjectRequest<TodayModel>>().new_get(normal + "ylUserBets.php", GETTODAY, map, this, new TypeToken<ObjectRequest<TodayModel>>() {
        });
    }
    /**
     * 提现到银行卡，反水提现到余额
     *
     * @param type
     */
    public void getMoney(int type,String bankId,String account
    ,String username,String coinPassword,String money) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(type));
        map.put("uid", getUid());//从缓存抓取
        map.put("bankId",bankId);
        map.put("account",account);
        map.put("username",username);
        map.put("coinPassword",coinPassword);
        map.put("money",money);

        new HttpUtils<ObjectRequest<String>>().new_get(normal + "ylUserTx.php", GETMONEY, map, this, new TypeToken<ObjectRequest<String>>() {
        });
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param coinPassword
     * @param qq
     * @param name
     */
    public void register(String username, String password, String coinPassword, String qq, String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("qq", qq);
        map.put("ptype", "1");
        if (!TextUtils.isEmpty(coinPassword)) {
            map.put("coinPassword", coinPassword);
        }
        if (!TextUtils.isEmpty(name)) {
            map.put("name", name);
        }
        new HttpUtils<String>().get(normal + "ylUserReg.php", UPDATEBANK, map, this);
    }

    public String getUid() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("grclass", MODE_PRIVATE);
        return sharedPreferences.getString("user_id", "");
    }

}
