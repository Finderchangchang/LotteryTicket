package gy.lotteryticket.ui.user;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arialyy.frame.module.AbsModule;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.control.CUserModule;
import gy.lotteryticket.method.LoadingDialog;
import gy.lotteryticket.model.BankModel;
import gy.lotteryticket.model.NormalRequest;
import gy.lotteryticket.ui.user.adapter.BankAdapter;

/**
 * Created by JX on 2017/12/28.
 */

public class BankActivity extends BaseActivity implements AbsModule.OnCallback {

    private CUserModule cUserModule;

    private Button btn_add;
    private RecyclerView recy_bank;
    private BankAdapter mAdapter;
    private List<BankModel> mData;

    private Dialog loadingDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bankaccount_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadingDialog = new LoadingDialog.Builder(this).create();

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BankActivity.this, AddBankActivity.class));
            }
        });

        initView();

        cUserModule = (CUserModule) getModule(CUserModule.class, this);

        loadingDialog.show();
        cUserModule.getBankList(1);
    }

    private void initView() {
        recy_bank = (RecyclerView) findViewById(R.id.recy_bank);
        initAdapter();
    }

    private void initAdapter() {
        mData = new ArrayList<>();
        mAdapter = new BankAdapter(R.layout.item_bankaccount, mData);
        recy_bank.setAdapter(mAdapter);
        recy_bank.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bank", mData.get(position));
                startActivityForResult(new Intent(BankActivity.this, AddBankActivity.class).putExtras(bundle), 1000);
            }
        });
    }

    @Override
    public void onSuccess(int result, Object success) {
        loadingDialog.dismiss();
        NormalRequest<JsonArray> res = (NormalRequest<JsonArray>) success;
        Type type = new TypeToken<List<BankModel>>() {
        }.getType();
        List<BankModel> data = new Gson().fromJson(res.getObj(), type);
        Log.e("TAG", "size : " + data.size());
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(int result, Object error) {
        loadingDialog.dismiss();
        Log.e("TAG", error + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            cUserModule.getBankList(1);
        }
    }
}
