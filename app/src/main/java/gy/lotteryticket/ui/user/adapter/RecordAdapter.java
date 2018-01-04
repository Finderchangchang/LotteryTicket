package gy.lotteryticket.ui.user.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.base.BaseApplication;
import gy.lotteryticket.model.RecordItemModel;
import gy.lotteryticket.ui.capital.JSZDListActivity;
import gy.lotteryticket.ui.user.RecordActivity;

/**
 * Created by JX on 2017/12/28.
 */

public class RecordAdapter extends BaseQuickAdapter<RecordItemModel, BaseViewHolder> {

    public RecordAdapter(@LayoutRes int layoutResId, @Nullable List<RecordItemModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final RecordItemModel item) {
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_week, item.getWeek());
        helper.setText(R.id.tv_number, item.getCount());
        helper.setText(R.id.tv_win, item.getShuying());
        helper.setOnClickListener(R.id.total_ll, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getCount() != null && Integer.parseInt(item.getCount()) > 0) {
                    RecordActivity.context.skip(JSZDListActivity.class, new Intent().putExtra("model", item));
                }
            }
        });
    }
}
