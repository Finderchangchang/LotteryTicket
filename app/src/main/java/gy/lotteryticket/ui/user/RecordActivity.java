package gy.lotteryticket.ui.user;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.arialyy.frame.module.AbsModule;

import java.util.ArrayList;
import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.control.CUserModule;
import gy.lotteryticket.method.LoadingDialog;
import gy.lotteryticket.model.NormalRequest;
import gy.lotteryticket.model.RecordItemModel;
import gy.lotteryticket.model.RecordModel;
import gy.lotteryticket.ui.user.adapter.RecordAdapter;

/**
 * 下注记录
 * Created by JX on 2017/12/28.
 */

public class RecordActivity extends BaseActivity implements AbsModule.OnCallback {

    private TextView tv_count;
    private TextView tv_money;

    private RecyclerView recy_record;
    private List<RecordItemModel> mData;
    private RecordAdapter mAdapter;

    private CUserModule cUserModule;
    private RecordModel recordModel;

    private Dialog loadingDialog;
    public static RecordActivity context;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bet_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        loadingDialog = new LoadingDialog.Builder(this).create();

        initView();

        mData = new ArrayList<>();
        mAdapter = new RecordAdapter(R.layout.item_record, mData);
        recy_record.setAdapter(mAdapter);
        recy_record.setLayoutManager(new LinearLayoutManager(this));
        recy_record.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        cUserModule = (CUserModule) getModule(CUserModule.class, this);

        loadingDialog.show();
        cUserModule.getRecord(1);
    }

    private void initView() {
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_money = (TextView) findViewById(R.id.tv_money);
        recy_record = (RecyclerView) findViewById(R.id.recy_record);
    }

    @Override
    public void onSuccess(int result, Object success) {
        loadingDialog.dismiss();
        NormalRequest<RecordModel> res = (NormalRequest<RecordModel>) success;
        recordModel = res.getObj();
        setData();
    }

    @Override
    public void onError(int result, Object error) {
        loadingDialog.dismiss();
    }

    private void setData() {
        tv_count.setText("总笔数：" + recordModel.getTotalCount());
        tv_money.setText("总输赢：" + recordModel.getTotalMoney());
        mData.addAll(recordModel.getData());
        Log.e("TAG", "mData.size = " + mData.size());
        mAdapter.notifyDataSetChanged();
    }

}
