package gy.lotteryticket.ui.user;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseActivity;
import gy.lotteryticket.model.RecordModel;
import gy.lotteryticket.ui.user.adapter.RecordAdapter;

/**
 * 下注记录
 * Created by JX on 2017/12/28.
 */

public class RecordActivity extends BaseActivity {

    private RecyclerView recy_record;
    private List<RecordModel> mData;
    private RecordAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bet_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recy_record = (RecyclerView) findViewById(R.id.recy_record);

        mData = new ArrayList<>();
        mAdapter = new RecordAdapter(R.layout.item_record, mData);
        recy_record.setAdapter(mAdapter);
        recy_record.setLayoutManager(new LinearLayoutManager(this));
        recy_record.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
}
