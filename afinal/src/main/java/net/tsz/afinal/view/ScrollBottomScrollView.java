package net.tsz.afinal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Finder丶畅畅 on 2017/5/30 22:59
 * QQ群481606175
 */

public class ScrollBottomScrollView extends ScrollView {

    private OnScrollBottomListener _listener;
    private int _calCount;

    public interface OnScrollBottomListener {
        void srollToBottom();
    }

    public void setOnScrollBottomListener(OnScrollBottomListener l) {
        _listener = l;
    }

    public void unRegisterOnScrollViewScrollToBottom() {
        _listener = null;
    }

    public ScrollBottomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = this.getChildAt(0);
        if (this.getHeight() + this.getScrollY() == view.getHeight()) {
            _calCount++;
            if (_calCount == 1) {
                if (_listener != null) {
                    _listener.srollToBottom();
                }
            }
        } else {
            _calCount = 0;
        }
    }
}
