package com.example.pages;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/23/023.
 */
public class PageSmart extends BasePage {

    public PageSmart(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_pagecontent_title.setText("智慧服务");

        TextView textView = new TextView(mActivity);
        textView.setText("智慧服务里的内容");
        textView.setGravity(Gravity.CENTER);
        ll_pagecontent_content.addView(textView);
    }


}
