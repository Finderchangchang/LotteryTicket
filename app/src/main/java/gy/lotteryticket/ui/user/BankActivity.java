package gy.lotteryticket.ui.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arialyy.frame.module.AbsModule;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.control.CUserModule;
import gy.lotteryticket.model.BankModel;
import gy.lotteryticket.model.NormalRequest;
import gy.lotteryticket.model.SelectBankModel;
import gy.lotteryticket.ui.user.adapter.BankAdapter;

/**
 * Created by JX on 2017/12/28.
 */

public class BankActivity extends BaseActivity implements AbsModule.OnCallback {

    private CUserModule cUserModule;
    private String uid;

    private Button btn_add;
    private RecyclerView recy_bank;
    private BankAdapter mAdapter;
    private List<BankModel> mData;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bankaccount_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BankActivity.this, AddBankActivity.class));
            }
        });

        initAdapter();

        uid = getUid();
    }

    private void initAdapter() {
        mData = new ArrayList<>();
        mAdapter = new BankAdapter(R.layout.item_bankaccount, mData);
        recy_bank.setAdapter(mAdapter);
        recy_bank.setLayoutManager(new LinearLayoutManager(this));
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
