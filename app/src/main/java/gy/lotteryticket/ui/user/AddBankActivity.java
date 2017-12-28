package gy.lotteryticket.ui.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.arialyy.frame.module.AbsModule;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.control.CUserModule;
import gy.lotteryticket.method.BankPopup;
import gy.lotteryticket.model.NormalRequest;
import gy.lotteryticket.model.SelectBankModel;

/**
 * 添加银行卡号
 * Created by JX on 2017/12/21.
 */

public class AddBankActivity extends BaseActivity implements AbsModule.OnCallback {

    private LinearLayout llay_select;

    private CUserModule cUserModule;
    private String uid;

    private List<SelectBankModel> data;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_bank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        llay_select = (LinearLayout) findViewById(R.id.llay_select);

        llay_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null) {
                    BankPopup bankPopup = new BankPopup(AddBankActivity.this, data);
                    bankPopup.show(llay_select);
                }
            }
        });

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
        this.data = data;
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
