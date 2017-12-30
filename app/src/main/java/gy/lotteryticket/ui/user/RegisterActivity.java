package gy.lotteryticket.ui.user;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arialyy.frame.module.AbsModule;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.control.CUserModule;
import gy.lotteryticket.method.LoadingDialog;

/**
 * Created by JX on 2017/12/30.
 */

public class RegisterActivity extends BaseActivity implements AbsModule.OnCallback {

    private EditText et_username;
    private EditText et_password;
    private EditText et_qu;
    private EditText et_qq;
    private EditText et_name;
    private TextView tv_confirm;

    private CUserModule cUserModule;

    private Dialog loadingDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadingDialog = new LoadingDialog.Builder(this).create();

        initView();

        cUserModule = (CUserModule) getModule(CUserModule.class, this);
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_qu = (EditText) findViewById(R.id.et_qu);
        et_qq = (EditText) findViewById(R.id.et_qq);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        String qu = et_qu.getText().toString();
        String qq = et_qq.getText().toString();
        String name = et_name.getText().toString();

        if (TextUtils.isEmpty(username)) {
            toast("用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            toast("密码不能为空");
            return;
        }

        if (TextUtils.isEmpty(qq)) {
            toast("QQ号码不能为空");
            return;
        }

        loadingDialog.show();

        cUserModule.register(username, password, qu, qq, name);
    }

    @Override
    public void onSuccess(int result, Object success) {
        loadingDialog.dismiss();
//        NormalRequest res = (NormalRequest) success;
        finish();
        toast("注册成功");
    }

    @Override
    public void onError(int result, Object error) {
        loadingDialog.dismiss();
        toast("网络错误");
    }
}
