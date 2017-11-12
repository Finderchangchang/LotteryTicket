package net.tsz.afinal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/29.
 */
public class TotalScrollView extends ScrollView {
    TextView mView;
    RelativeLayout mView1;

    public TotalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.smoothScrollTo(0, 20);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mView1 != null) {
            int mipampHeght = mView1.getHeight() * 3;
            if (t < mipampHeght) {
                int alp = (int) ((t / (float) mipampHeght) * 255);
                mView1.getBackground().setAlpha(alp);
            } else {
                mView1.getBackground().setAlpha(255);
            }
        }

    }

    public TotalScrollView(Context context) {
        this(context, null);
    }

    public TotalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 设置顶部TitleBar显示隐藏
     *
     * @param
     */
    public void setTitleView(TextView view, RelativeLayout view1) {
        mView = view;
        mView1 = view1;
        mView1.getBackground().setAlpha(0);
    }
}

