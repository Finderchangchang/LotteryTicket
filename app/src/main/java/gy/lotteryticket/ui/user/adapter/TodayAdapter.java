package gy.lotteryticket.ui.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.model.TodayItemModel;

/**
 * Created by JX on 2017/12/28.
 */

public class TodayAdapter extends BaseQuickAdapter<TodayItemModel, BaseViewHolder> {

    public TodayAdapter(@LayoutRes int layoutResId, @Nullable List<TodayItemModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodayItemModel item) {
        //期号
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_number, item.getActionNo());
        helper.setText(R.id.tv_time, item.getActionTime());

        //明细
        helper.setText(R.id.tv_detail, item.getMoney());
        helper.setText(R.id.tv_detail_fan, "反水（" + item.getRebate() + "）");

        //金额
        helper.setText(R.id.tv_money, item.getMoney());

        //输赢
        helper.setText(R.id.tv_win, item.getKeying());
        helper.setText(R.id.tv_win_fan, "反水（" + item.getRebateMoney() + "）");

    }
}
