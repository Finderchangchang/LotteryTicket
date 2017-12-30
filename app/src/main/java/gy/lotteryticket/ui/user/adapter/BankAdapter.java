package gy.lotteryticket.ui.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.model.BankModel;

/**
 * Created by JX on 2017/12/28.
 */

public class BankAdapter extends BaseQuickAdapter<BankModel, BaseViewHolder> {

    public BankAdapter(@LayoutRes int layoutResId, @Nullable List<BankModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankModel item) {
        helper.setText(R.id.tv_name, "开户姓名："+item.getUsername());
        helper.setText(R.id.tv_accout, "开户账号："+item.getAccount());
        helper.setText(R.id.tv_point, "开户网点："+item.getCountname());
    }
}
