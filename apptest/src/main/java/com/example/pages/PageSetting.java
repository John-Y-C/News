package com.example.pages;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/23/023.
 */
public class PageSetting extends BasePage {

    public PageSetting(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_pagecontent_title.setText("设置");

        TextView textView = new TextView(mActivity);
        textView.setText("设置里的内容");
        textView.setGravity(Gravity.CENTER);
        ll_pagecontent_content.addView(textView);
    }


}
