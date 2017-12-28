package gy.lotteryticket.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.ui.user.BankActivity;
import gy.lotteryticket.ui.user.LoginPwdActivity;
import gy.lotteryticket.ui.user.MessageActivity;
import gy.lotteryticket.ui.user.QuPwdActivity;
import gy.lotteryticket.ui.user.RecordActivity;
import gy.lotteryticket.ui.user.TodayActivity;
import gy.lotteryticket.ui.user.UserDataActivity;

public class UserActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llayData;
    private LinearLayout llayLoginPwd;
    private LinearLayout llayMsg;
    private LinearLayout llayMoney;
    private LinearLayout llayQu;
    private LinearLayout llayBank;
    private LinearLayout llayToday;
    private LinearLayout llayXiazhu;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        llayData = (LinearLayout) findViewById(R.id.llay_data);
        llayLoginPwd = (LinearLayout) findViewById(R.id.llay_login_pwd);
        llayMsg = (LinearLayout) findViewById(R.id.llay_msg);
        llayMoney = (LinearLayout) findViewById(R.id.llay_money);
        llayQu = (LinearLayout) findViewById(R.id.llay_qu);
        llayBank = (LinearLayout) findViewById(R.id.llay_bank);
        llayToday = (LinearLayout) findViewById(R.id.llay_today);
        llayXiazhu = (LinearLayout) findViewById(R.id.llay_xiazhu);

        llayData.setOnClickListener(this);
        llayLoginPwd.setOnClickListener(this);
        llayMsg.setOnClickListener(this);
        llayMoney.setOnClickListener(this);
        llayQu.setOnClickListener(this);
        llayBank.setOnClickListener(this);
        llayToday.setOnClickListener(this);
        llayXiazhu.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llay_data:
                startActivity(new Intent(this, UserDataActivity.class));
                break;
            case R.id.llay_qu:
                startActivity(new Intent(this, QuPwdActivity.class));
                break;
            case R.id.llay_msg:
                startActivity(new Intent(this, MessageActivity.class));
                break;
            case R.id.llay_login_pwd:
                startActivity(new Intent(this, LoginPwdActivity.class));
                break;
            case R.id.llay_bank:
                startActivity(new Intent(this, BankActivity.class));
                break;
            case R.id.llay_today:
                startActivity(new Intent(this, TodayActivity.class));
                break;
            case R.id.llay_xiazhu:
                startActivity(new Intent(this, RecordActivity.class));
                break;
        }
    }
}