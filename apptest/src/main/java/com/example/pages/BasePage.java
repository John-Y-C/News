package com.example.pages;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apptest.HomeActivity;
import com.example.apptest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/23/023.
 */
public abstract class BasePage {

    public Activity mActivity;
    @BindView(R.id.ib_pagecontent_list)
    ImageButton ib_pagecontent_list;
    @BindView(R.id.tv_pagecontent_title)
    TextView tv_pagecontent_title;
    @BindView(R.id.ib_pagecontent_grid)
    ImageButton ib_pagecontent_grid;
    @BindView(R.id.ll_pagecontent_content)
    LinearLayout ll_pagecontent_content;
    public View mViewPage;

    public BasePage(Activity activity) {
        mActivity = activity;
        initView();
        initData();
    }

    public abstract void initData();

    public void initView() {
        mViewPage = View.inflate(mActivity, R.layout.page_content, null);
        ButterKnife.bind(this, mViewPage);

    }

    public void setSlidingEnable(boolean enable)
    {
        HomeActivity homeActivity= (HomeActivity) mActivity;
        homeActivity.setSlidingMenuEnable(enable);
    }
}
