package gy.lotteryticket.ui.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.arialyy.frame.module.AbsModule;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.control.CUserModule;
import gy.lotteryticket.model.NormalRequest;
import gy.lotteryticket.model.SelectBankModel;

/**
 * Created by JX on 2017/12/28.
 */

public class BankActivity extends BaseActivity implements AbsModule.OnCallback {

    private CUserModule cUserModule;
    private String uid;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bankaccount_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uid = getUid();

        cUserModule = (CUserModule) getModule(CUserModule.class, this);

        cUserModule.getBankList(2, uid);
    }

    @Override
    public void onSuccess(int result, Object success) {
        NormalRequest<JsonArray> res = (NormalRequest<JsonArray>) success;
        Type type = new TypeToken<List<SelectBankModel>>() {
        }.getType();
        List<SelectBankModel> data = new Gson().fromJson(res.getObj(), type);
        Log.e("TAG", "size : " + data.size());
    }

    @Override
    public void onError(int result, Object error) {
        Log.e("TAG", error + "");
    }

    public String getUid() {
        SharedPreferences sharedPreferences = getSharedPreferences("grclass", MODE_PRIVATE);
        return sharedPreferences.getString("user_id", "");
    }

}
