package com.example.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/8/23/023.
 */
public class NoScrollViewPage extends ViewPager {
    public NoScrollViewPage(Context context) {
        super(context);
    }

    public NoScrollViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        //禁用viewpager的滑动效果，true的话，后续滑动事件仍然可用
        return false;
    }
}
