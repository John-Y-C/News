package com.example.menupage;

import android.app.Activity;
import android.view.View;

import com.example.bean.Categories;

/**
 * Created by Administrator on 2016/8/24/024.
 */
public abstract class BaseMenuPage {

    public Activity mActivity;
    public View mView;
    public Categories.DataBean mDataBean;

    public BaseMenuPage(Activity activity, Categories.DataBean dataBean)
    {
        mActivity=activity;
        mDataBean=dataBean;
        mView=initView();
    }

    public abstract View initView();

    public abstract void initData();

}
