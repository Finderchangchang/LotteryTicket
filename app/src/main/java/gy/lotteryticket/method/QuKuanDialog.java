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
 * Created by Finder丶畅畅 on 2018/1/23 17:44
 * QQ群481606175
 */

public class QuKuanDialog extends Dialog {
    private Button btn_ok;//确定
    private TextView tvbianhao;//交易编号
    private TextView tvtime;//发起时间
    private TextView tvleixing;//交易类型
    private TextView tvjine;//交易金额
    private TextView tvfeiyong;//手续费用
    private TextView tvzhuangtai;//交易状态
    private TextView tvchognzhi;//充值方式
    private TextView tvbz;//备注
//    private TextView tvxinxi;//存款人
    private ZDModel.DataBean myModel;

    public QuKuanDialog(@NonNull Context context, ZDModel.DataBean model) {
        super(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        myModel = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_zijing_qukuan);
        btn_ok = (Button) findViewById(R.id.btn_zijin_ok);
        tvbianhao = (TextView) findViewById(R.id.zijin_bianhao);
        tvtime = (TextView) findViewById(R.id.zijin_time);
        tvleixing = (TextView) findViewById(R.id.zijin_leixing);
        tvjine = (TextView) findViewById(R.id.zijin_jine);
        tvfeiyong = (TextView) findViewById(R.id.zijin_feiyong);
        tvzhuangtai = (TextView) findViewById(R.id.zijin_zhuangtai);
//        tvyinhang = (TextView) findViewById(R.id.zijin_yinhang);
//        tvkahao = (TextView) findViewById(R.id.zijin_kahao);
//        tvchikaren = (TextView) findViewById(R.id.zijin_chikaren);
        tvchognzhi = (TextView) findViewById(R.id.zijin_chongzhi);
//        tvxinxi = (TextView) findViewById(R.id.zijin_xinxi);
        tvbz = (TextView) findViewById(R.id.zijin_bz);


        tvbianhao.setText(myModel.getId());
        tvtime.setText(myModel.getActionTime());
        tvleixing.setText("系统充值");
        tvjine.setText(myModel.getAmount());
        tvfeiyong.setText("0");
        if (myModel.getState().equals("0")) {
            tvzhuangtai.setText("申请中");
        } else if (myModel.getState().equals("3")) {
            tvzhuangtai.setText("失败");
        } else {
            tvzhuangtai.setText("充值成功");
        }
//        tvyinhang.setText(myModel.getBankName());
//        tvkahao.setText(myModel.getAccount());
//        tvchikaren.setText(myModel.getUsername());
//        tvxinxi.setText(myModel.getUsername());
        tvbz.setText(myModel.getFlag());
        tvchognzhi.setText(myModel.getInfo());

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
