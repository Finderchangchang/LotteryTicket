package net.tsz.afinal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/5/21.
 */

public class OnlyMeasureGridView extends GridView {
    public OnlyMeasureGridView(Context context) {
        super(context);
    }

    public OnlyMeasureGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OnlyMeasureGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
