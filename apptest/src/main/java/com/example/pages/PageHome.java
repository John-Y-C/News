package com.example.pages;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/23/023.
 */
public class PageHome extends BasePage {

    public PageHome(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_pagecontent_title.setText("首页");

        TextView textView = new TextView(mActivity);
        textView.setText("首页里的内容");
        textView.setGravity(Gravity.CENTER);
        ll_pagecontent_content.addView(textView);
    }


}
