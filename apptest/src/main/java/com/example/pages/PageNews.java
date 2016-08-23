package com.example.pages;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.apptest.HomeActivity;
import com.example.bean.Categories;
import com.example.fragment.LeftFragment;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Administrator on 2016/8/23/023.
 */
public class PageNews extends BasePage {

    private HomeActivity homeActivity;

    public PageNews(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tv_pagecontent_title.setText("新闻");

        TextView textView = new TextView(mActivity);
        textView.setText("新闻里的内容");
        textView.setGravity(Gravity.CENTER);
        ll_pagecontent_content.addView(textView);

        ib_pagecontent_list.setVisibility(View.VISIBLE);
        ib_pagecontent_grid.setVisibility(View.VISIBLE);

        homeActivity = (HomeActivity) mActivity;
        ib_pagecontent_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivity.toggle();
            }
        });

    }

    public void getDataFromServer()
    {
        String url="http://10.0.2.2:8080/zhbj/categories.json";
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.d("PageNews", responseInfo.result);
                String result = responseInfo.result;
                parseJson(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("PageNews", s);

            }
        });

    }

    private void parseJson(String result) {

        Gson gson=new Gson();
        Categories categories = gson.fromJson(result, Categories.class);
        Log.d("PageNews", categories.toString());
        LeftFragment leftFragment = homeActivity.getLeftFragment();
        leftFragment.setDataBean(categories);

    }


}
