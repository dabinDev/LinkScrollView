package com.example.linkagelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Project :  Linkagelayout.
 * Package name: com.example.linkagelayout
 * Created by :  dabin.
 * Created time: 4/11/2023 12:55 PM
 * Changed by :  dabin.
 * Changed time: 4/11/2023 12:55 PM
 * Class description:
 */
public class ObservableScrollView extends HorizontalScrollView {
    private ScrollViewListener scrollViewListener = null;


    public ObservableScrollView(Context context) {
        super(context);
    }


    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }


    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setOnScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }


    }

    public interface ScrollViewListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    }
}


