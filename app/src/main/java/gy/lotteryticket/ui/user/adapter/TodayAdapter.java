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
        helper.setText(R.id.tv_detail1, item.getGroupname() );

        helper.setText(R.id.tv_detail2,"-" + item.getActionData() );
        helper.setText(R.id.tv_detail3, "@" + Double.parseDouble(item.getOdds()));
        if(item.getPanid().equals("1")){
            helper.setText(R.id.tv_detail_fan, "A盘");
        }else{
            helper.setText(R.id.tv_detail_fan, "B盘");
        }


        //金额
        helper.setText(R.id.tv_money, item.getMoney());

        //输赢
        helper.setText(R.id.tv_win, Double.parseDouble(item.getKeying()) + "");
        helper.setText(R.id.tv_win_fan, "反水（" + item.getRebateMoney() + "）");
        if (item.getZjCount().equals("1")) {
            helper.setText(R.id.result_tv, "中奖");
            helper.setTextColor(R.id.result_tv,mContext.getResources().getColor(R.color.green));
        } else {
            helper.setText(R.id.result_tv, "未中奖");
            helper.setTextColor(R.id.result_tv,mContext.getResources().getColor(R.color.red));
        }
    }
}
