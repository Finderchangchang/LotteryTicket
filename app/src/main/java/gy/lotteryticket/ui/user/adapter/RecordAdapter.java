package gy.lotteryticket.ui.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.model.RecordItemModel;

/**
 * Created by JX on 2017/12/28.
 */

public class RecordAdapter extends BaseQuickAdapter<RecordItemModel, BaseViewHolder> {

    public RecordAdapter(@LayoutRes int layoutResId, @Nullable List<RecordItemModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecordItemModel item) {
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_week, item.getWeek());
        helper.setText(R.id.tv_number, item.getCount());
        helper.setText(R.id.tv_win, item.getShuying());
    }
}
