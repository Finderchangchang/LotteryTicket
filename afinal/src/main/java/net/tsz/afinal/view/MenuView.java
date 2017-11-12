package net.tsz.afinal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.tsz.afinal.R;

/**
 * 左右侧文字菜单项，主要用于个人中心
 * Created by Administrator on 2017/4/8.
 */

public class MenuView extends LinearLayout {
    int str_left_iv;//左侧图
    int str_right_iv;//右侧图（默认为箭头）
    String str_left_tv;//左侧文字
    String str_right_tv;//右侧文字
    int str_left_tv_color;//左侧文字颜色
    int str_right_tv_color;//右侧文字颜色
    TextView left_tv;
    TextView right_tv;
    ImageView left_iv;
    ImageView right_iv;
    void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.v_menu, this);
        left_tv = (TextView) findViewById(R.id.left_tv);
        right_tv = (TextView) findViewById(R.id.right_tv);
        left_iv = (ImageView) findViewById(R.id.left_iv);
        right_iv = (ImageView) findViewById(R.id.right_iv);

        if (!TextUtils.isEmpty(str_left_tv)) {
            left_tv.setText(str_left_tv);
            if (str_left_tv_color != 0) {
                left_tv.setTextColor(str_left_tv_color);
            }
        }
        if (!TextUtils.isEmpty(str_right_tv)) {
            right_tv.setText(str_right_tv);
            if (str_right_tv_color != 0) {
                right_tv.setTextColor(str_right_tv_color);
            }
        }
        if (str_left_iv != 0) {
            left_iv.setImageResource(str_left_iv);
            left_iv.setVisibility(VISIBLE);
        } else {
            left_iv.setVisibility(GONE);
        }


        //if (str_right_iv != 0) {
        //    right_iv.setImageResource(str_right_iv);
        //    right_iv.setVisibility(VISIBLE);
        //} else {
        //    right_iv.setVisibility(GONE);
        //}
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuView, defStyleAttr, 0);
        str_left_iv = a.getResourceId(R.styleable.MenuView_menu_left_iv, 0);
        str_left_tv = a.getString(R.styleable.MenuView_menu_left_tv);
        str_right_tv = a.getString(R.styleable.MenuView_menu_right_tv);
        str_left_tv_color = a.getColor(R.styleable.MenuView_menu_left_tv_color, 0);
        str_right_tv_color = a.getColor(R.styleable.MenuView_menu_right_tv_color, 0);
        str_right_iv = a.getResourceId(R.styleable.MenuView_menu_right_iv, 0);

        a.recycle();
        init(context);
    }

    public MenuView(Context context) {
        this(context, null);
    }

    public MenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    /**
     * 设置右侧文字
     *
     * @param str_right_tv
     */
    public void setRight_tv(String str_right_tv) {
        right_tv.setText(str_right_tv);
    }

    /**
     * 设置右侧文字颜色
     *
     * @param str_right_tv_color
     */
    public void setRight_tv_color(int str_right_tv_color) {
        right_tv.setTextColor(str_right_tv_color);
    }
}
