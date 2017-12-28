package gy.lotteryticket.ui.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.model.SelectBankModel;

/**
 * Created by JX on 2017/12/28.
 */

public class BanPopupAdapter extends BaseQuickAdapter<SelectBankModel, BaseViewHolder> {

    public BanPopupAdapter(@LayoutRes int layoutResId, @Nullable List<SelectBankModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectBankModel item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
