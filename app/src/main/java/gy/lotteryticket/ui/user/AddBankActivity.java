package gy.lotteryticket.ui.user;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import gy.lotteryticket.method.BankPopup;
import gy.lotteryticket.method.LoadingDialog;
import gy.lotteryticket.model.BankModel;
import gy.lotteryticket.model.NormalRequest;
import gy.lotteryticket.model.SelectBankModel;

/**
 * 添加银行卡号
 * Created by JX on 2017/12/21.
 */

public class AddBankActivity extends BaseActivity implements AbsModule.OnCallback {

    private LinearLayout llay_select;
    private TextView tv_bank;
    private EditText et_kaihuhang;
    private EditText et_kahao;
    private TextView tv_name;
    private TextView tv_confirm;
    private EditText et_name;

    private CUserModule cUserModule;

    private List<SelectBankModel> data;

    private Dialog loadingDialog;

    private BankPopup bankPopup;

    private String bankId = "0";

    private BankModel updateModel;

    private int type;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_bank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadingDialog = new LoadingDialog.Builder(this).create();
        loadingDialog.setCanceledOnTouchOutside(false);

        initView();

        data = new ArrayList<>();

        cUserModule = (CUserModule) getModule(CUserModule.class, this);

        loadingDialog.show();
        cUserModule.getBankList(2);
    }

    private void initView() {
        llay_select = (LinearLayout) findViewById(R.id.llay_select);
        tv_bank = (TextView) findViewById(R.id.tv_bank);
        et_kaihuhang = (EditText) findViewById(R.id.et_kaihuhang);
        et_kahao = (EditText) findViewById(R.id.et_kahao);
        llay_select = (LinearLayout) findViewById(R.id.llay_select);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        et_name = (EditText) findViewById(R.id.et_name);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBank();
            }
        });

        llay_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null) {
                    bankPopup = new BankPopup(AddBankActivity.this, data);
                    bankPopup.show(llay_select);
                    bankPopup.setOnClickListener(new BankPopup.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            bankPopup.dismiss();
                            bankId = data.get(position).getId();
                            tv_bank.setText(data.get(position).getName());
                        }
                    });
                }else {
                    loadingDialog.show();
                    cUserModule.getBankList(2);
                }
            }
        });

        updateModel = (BankModel) getIntent().getSerializableExtra("bank");
        if (updateModel != null) {
            tv_bank.setText(updateModel.getBankName());
            tv_name.setVisibility(View.VISIBLE);
            et_name.setVisibility(View.GONE);
            et_kaihuhang.setText(updateModel.getCountname());
            et_kahao.setText(updateModel.getAccount());
            tv_name.setText("开户姓名：" + updateModel.getUsername());
            bankId = updateModel.getBankId();
        }
    }

    private void updateBank() {
        String kaihuhang = et_kaihuhang.getText().toString();
        String kahao = et_kahao.getText().toString();
        String name = "";

        if (bankId.equals("0")) {
            toast("请选择银行");
            return;
        }

        if (TextUtils.isEmpty(kaihuhang)) {
            toast("开户行不能为空");
            return;
        }

        if (TextUtils.isEmpty(kahao)) {
            toast("银行卡号不能为空");
            return;
        }

        if (updateModel == null) {
            type = 1;//添加
            name = et_name.getText().toString();
            if (TextUtils.isEmpty(name)) {
                toast("姓名不能为空");
                return;
            }
        } else {
            type = 2; //修改
            name = updateModel.getUsername();
        }

        cUserModule.updateBank(type, bankId, kahao, kaihuhang, name);

    }

    @Override
    public void onSuccess(int result, Object success) {
        loadingDialog.dismiss();
        if (result == CUserModule.GETBANK) {
            NormalRequest<JsonArray> res = (NormalRequest<JsonArray>) success;
            Type type = new TypeToken<List<SelectBankModel>>() {
            }.getType();
            List<SelectBankModel> data = new Gson().fromJson(res.getObj(), type);
            this.data.addAll(data);
        }

        if (result == CUserModule.UPDATEBANK) {
            if (type == 1) {
                toast("添加成功");
            } else {
                toast("修改成功");
            }
            finish();
        }
    }

    @Override
    public void onError(int result, Object error) {
        loadingDialog.dismiss();
        toast("网络错误");
        Log.e("TAG", error + "");
    }

}
