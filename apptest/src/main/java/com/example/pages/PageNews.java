package com.example.pages;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.apptest.HomeActivity;
import com.example.bean.Categories;
import com.example.fragment.LeftFragment;
import com.example.menupage.BaseMenuPage;
import com.example.menupage.MenuPageInteract;
import com.example.menupage.MenuPageNews;
import com.example.menupage.MenuPagePicture;
import com.example.menupage.MenuPageTopic;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/23/023.
 */
public class PageNews extends BasePage {

    private HomeActivity homeActivity;
    //boolean firstRun = true;
    List<BaseMenuPage> baseMenuPageList;

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

        //--------------高-----亮--------------------------
        //页面初始化的时候获取数据，不可设置点击获取，
        //否则会导致进行页面布局和获取数据的顺序不对，致使崩溃
        //不用设置判断，若不销毁，初始化仅执行一次
        getDataFromServer();
        //--------------高-----亮--------------------------


    }

    public void getDataFromServer() {
        //注：换成手机调试时要更换url
        String url = "http://10.0.2.2:8080/zhbj/categories.json";
        HttpUtils httpUtils = new HttpUtils();
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

        Gson gson = new Gson();
        Categories categories = gson.fromJson(result, Categories.class);
        Log.d("PageNews", categories.toString());
        LeftFragment leftFragment = homeActivity.getLeftFragment();
        leftFragment.setDataBean(categories);

        initMenuPage(categories);

    }

    private void initMenuPage(Categories categories) {

        baseMenuPageList = new ArrayList<>();

/*        for (int i = 0; i < categories.data.size(); i++) {
            TextView textView = new TextView(homeActivity);
            textView.setText(categories.data.get(i).title);
            textView.setGravity(Gravity.CENTER);

            baseMenuPageList.add(textView);
            Log.d("PageNews", "baseMenuPageList.size():" + baseMenuPageList.size());
        }*/

        baseMenuPageList.add(new MenuPageNews(mActivity,categories.data.get(0)));
        baseMenuPageList.add(new MenuPageTopic(mActivity,categories.data.get(1)));
        baseMenuPageList.add(new MenuPagePicture(mActivity,categories.data.get(2)));
        baseMenuPageList.add(new MenuPageInteract(mActivity,categories.data.get(3)));

        //设置初始界面
        changeMenuPage(0);

    }

    public void changeMenuPage(int position) {

        Log.d("PageNews", "position:" + position);
        ll_pagecontent_content.removeAllViews();
        Log.d("PageNews", "baseMenuPageList.size():---" + baseMenuPageList.size());
        BaseMenuPage baseMenuPage = baseMenuPageList.get(position);
        baseMenuPage.initData();
        ll_pagecontent_content.addView(baseMenuPage.mView);

    }

}
