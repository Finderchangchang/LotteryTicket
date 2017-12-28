package gy.lotteryticket.ui.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gy.lotteryticket.model.RecordModel;

/**
 * Created by JX on 2017/12/28.
 */

public class RecordAdapter extends BaseQuickAdapter<RecordModel, BaseViewHolder> {

    public RecordAdapter(@LayoutRes int layoutResId, @Nullable List<RecordModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecordModel item) {

    }
}
