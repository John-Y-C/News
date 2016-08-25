package com.example.menupage;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.bean.Categories;

/**
 * Created by Administrator on 2016/8/24/024.
 */
public class MenuPagePicture extends BaseMenuPage {

    public MenuPagePicture(Activity activity, Categories.DataBean dataBean) {
        super(activity, dataBean);
    }

    @Override
    public View initView() {

        TextView textView = new TextView(mActivity);
        textView.setText("图片");
        textView.setTextColor(Color.DKGRAY);
        return textView;

    }

    @Override
    public void initData() {

    }
}
