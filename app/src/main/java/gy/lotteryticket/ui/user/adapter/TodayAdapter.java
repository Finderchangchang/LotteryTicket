package gy.lotteryticket.ui.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gy.lotteryticket.model.TodayModel;

/**
 * Created by JX on 2017/12/28.
 */

public class TodayAdapter extends BaseQuickAdapter<TodayModel, BaseViewHolder> {

    public TodayAdapter(@LayoutRes int layoutResId, @Nullable List<TodayModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodayModel item) {

    }
}
