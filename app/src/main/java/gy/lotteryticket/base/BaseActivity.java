package gy.lotteryticket.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.arialyy.frame.core.AbsActivity;

import net.tsz.afinal.view.TitleBar;

import gy.lotteryticket.method.LoadingDialog;

/**
 * Created by lyy on 2016/7/13.
 * https://github.com/AriaLyy/MVVM
 */
public abstract class BaseActivity<VB extends ViewDataBinding> extends AbsActivity<VB> {
    TitleBar title_bar;
    Toast toast;
    public AlertDialog.Builder builder;
    public LoadingDialog.Builder dialog;

    public void toast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        int imageViewId = getResources().getIdentifier("title_bar", "id", getPackageName());
        title_bar = (TitleBar) findViewById(imageViewId);
        if (title_bar != null) {
            title_bar.setLeftClick(new TitleBar.LeftClick() {
                @Override
                public void onClick() {
                    finish();
                }
            });
        }
        dialog = new LoadingDialog.Builder(this);
        builder = new AlertDialog.Builder(this);
    }

    @Override
    protected void dataCallback(int result, Object data) {

    }

}
