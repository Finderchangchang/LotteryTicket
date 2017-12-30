package gy.lotteryticket.ui.user;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.arialyy.frame.module.AbsModule;

import java.util.ArrayList;
import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.control.CUserModule;
import gy.lotteryticket.method.LoadingDialog;
import gy.lotteryticket.model.NormalRequest;
import gy.lotteryticket.model.TodayItemModel;
import gy.lotteryticket.model.TodayModel;
import gy.lotteryticket.ui.user.adapter.TodayAdapter;

/**
 * 今日已结
 * Created by JX on 2017/12/28.
 */

public class TodayActivity extends BaseActivity implements AbsModule.OnCallback {

    private RecyclerView recy_today;
    private TextView tv_xia;
    private TextView tv_fan;
    private TextView tv_shu;

    private CUserModule cUserModule;

    private Dialog loadingDialog;

    private TodayModel todayModel;
    private List<TodayItemModel> mData;
    private TodayAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_todaycomplete_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        loadingDialog = new LoadingDialog.Builder(this).create();

        cUserModule = (CUserModule) getModule(CUserModule.class, this);
        loadingDialog.show();
        cUserModule.getToday(1);
    }

    private void initView() {
        recy_today = (RecyclerView) findViewById(R.id.recy_today);
        tv_xia = (TextView) findViewById(R.id.tv_xia);
        tv_shu = (TextView) findViewById(R.id.tv_shu);
        tv_fan = (TextView) findViewById(R.id.tv_fan);

        mData = new ArrayList<>();
        mAdapter = new TodayAdapter(R.layout.item_today_complete, mData);
        recy_today.setAdapter(mAdapter);
        recy_today.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSuccess(int result, Object success) {
        loadingDialog.dismiss();
        NormalRequest<TodayModel> res = (NormalRequest<TodayModel>) success;
        todayModel = res.getObj();
        setData();
    }

    @Override
    public void onError(int result, Object error) {
        loadingDialog.dismiss();
    }

    private void setData() {

        tv_xia.setText("下注金额："+todayModel.getTotalMoney());
        tv_fan.setText("反水金额："+todayModel.getTotalRebateMoney());
        tv_shu.setText("输赢金额："+todayModel.getTotalShuyingMoney());


        mData.addAll(todayModel.getData());
        mAdapter.notifyDataSetChanged();
    }

}
