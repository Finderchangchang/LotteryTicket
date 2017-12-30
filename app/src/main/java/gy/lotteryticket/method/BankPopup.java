package gy.lotteryticket.method;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import gy.lotteryticket.R;
import gy.lotteryticket.model.SelectBankModel;
import gy.lotteryticket.ui.user.adapter.BanPopupAdapter;

/**
 * Created by JX on 2017/12/28.
 */

public class BankPopup extends PopupWindow {

    private View contentView;

    private RecyclerView recyBank;
    private BanPopupAdapter banPopupAdapter;

    public interface OnClickListener {
        void onClick(int position);
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BankPopup(Context context, List<SelectBankModel> data) {
        super(context);
        contentView = LayoutInflater.from(context).inflate(R.layout.popup_bank, null);
        setContentView(contentView);
        setOutsideTouchable(true);
        recyBank = (RecyclerView) contentView.findViewById(R.id.recy_bank);

        banPopupAdapter = new BanPopupAdapter(R.layout.item_popup_bank, data);
        recyBank.setAdapter(banPopupAdapter);
        recyBank.setLayoutManager(new LinearLayoutManager(context));

        banPopupAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            }
        });
    }

    public void show(View v) {
        showAsDropDown(v);
    }

}
