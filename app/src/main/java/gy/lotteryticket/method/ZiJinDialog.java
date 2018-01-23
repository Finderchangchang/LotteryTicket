package gy.lotteryticket.method;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gy.lotteryticket.R;
import gy.lotteryticket.model.ZDModel;

/**
 * Created by Finder丶畅畅 on 2018/1/23 12:19
 * QQ群481606175
 */

public class ZiJinDialog extends Dialog {
    private Button btn_ok;//确定
    private TextView tvbianhao;//交易编号
    private TextView tvtime;//发起时间
    private TextView tvleixing;//交易类型
    private TextView tvjine;//交易金额
    private TextView tvfeiyong;//手续费用
    private TextView tvzhuangtai;//交易状态
    private TextView tvyinhang;//提现银行
    private TextView tvkahao;//提款卡号
    private TextView tvchikaren;//持卡人
    private TextView tvxinxi;//信息
    private TextView tvbz;//备注

    private ZDModel.DataBean myModel;

    public ZiJinDialog(@NonNull Context context, ZDModel.DataBean model) {
        super(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        myModel = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_zijin_detail);
        btn_ok = (Button) findViewById(R.id.btn_zijin_ok);
        tvbianhao = (TextView) findViewById(R.id.zijin_bianhao);
        tvtime = (TextView) findViewById(R.id.zijin_time);
        tvleixing = (TextView) findViewById(R.id.zijin_leixing);
        tvjine = (TextView) findViewById(R.id.zijin_jine);
        tvfeiyong = (TextView) findViewById(R.id.zijin_feiyong);
        tvzhuangtai = (TextView) findViewById(R.id.zijin_zhuangtai);
        tvyinhang = (TextView) findViewById(R.id.zijin_yinhang);
        tvkahao = (TextView) findViewById(R.id.zijin_kahao);
        tvchikaren = (TextView) findViewById(R.id.zijin_chikaren);
        tvxinxi = (TextView) findViewById(R.id.zijin_xinxi);
        tvbz = (TextView) findViewById(R.id.zijin_bz);


        tvbianhao.setText(myModel.getId());
        tvtime.setText(myModel.getActionTime());
        tvleixing.setText("提现");
        tvjine.setText(myModel.getAmount());
        tvfeiyong.setText("0");
        if (myModel.getState().equals("1")) {
            tvzhuangtai.setText("申请中");
        } else if (myModel.getState().equals("0") || myModel.getState().equals("3")) {
            tvzhuangtai.setText("提现成功");
        } else {
            tvzhuangtai.setText("失败");
        }
        tvyinhang.setText(myModel.getBankName());
        tvkahao.setText(myModel.getAccount());
        tvchikaren.setText(myModel.getUsername());
        tvxinxi.setText(myModel.getInfo());
        tvbz.setText(myModel.getFlag());


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
