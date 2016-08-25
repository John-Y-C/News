package com.example.menupage;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apptest.R;
import com.example.bean.Categories;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/24/024.
 */
public class MenuPageNews extends BaseMenuPage {

    List<TextView> menuPageNeesList;
    @BindView(R.id.tp_menupagenews_title)
    com.viewpagerindicator.TabPageIndicator tp_menupagenews_title;
    @BindView(R.id.vp_menupagenews_news)
    ViewPager vp_menupagenews_news;
    private MyMenuPageNewsAdapter myMenuPageNewsAdapter;

    public MenuPageNews(Activity activity, Categories.DataBean dataBean) {
        super(activity, dataBean);
    }

    @Override
    public View initView() {

//        TextView textView = new TextView(mActivity);
//        textView.setText("新闻");
//        textView.setTextColor(Color.BLUE);

        //此处仅做绑定布局处理，页面具体处理在initData（）中操作
        View menupage_news = View.inflate(mActivity, R.layout.menupage_news, null);
        ButterKnife.bind(this,menupage_news);

        return menupage_news;

    }

    @Override
    public void initData() {

        menuPageNeesList = new ArrayList<>();

        //部署在PageNews里的viewpager里的数据
        for (int i = 0; i < mDataBean.children.size(); i++) {
            TextView textView = new TextView(mActivity);
            Categories.DataBean.ChildrenBean childrenBean = mDataBean.children.get(i);
            textView.setText(childrenBean.title);
            textView.setTextSize(50);
            textView.setTextColor(Color.RED);
            textView.setGravity(Gravity.CENTER);

            menuPageNeesList.add(textView);
            Log.d("MenuPageNews", "menuPageNeesList.size():" + menuPageNeesList.size());
        }

        //新建的view在这个方法里，所以设置适配器也在此方法内
        myMenuPageNewsAdapter = new MyMenuPageNewsAdapter();
        vp_menupagenews_news.setAdapter(myMenuPageNewsAdapter);

        //tabindicator必须在viewpager之后使用
        tp_menupagenews_title.setViewPager(vp_menupagenews_news);
    }

    class MyMenuPageNewsAdapter extends PagerAdapter
    {

        @Override
        public int getCount() {
            return menuPageNeesList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            TextView textView = menuPageNeesList.get(position);
            container.addView(textView);

            return textView;//super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
            //super.destroyItem(container, position, object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mDataBean.children.get(position).title;//super.getPageTitle(position);
        }
    }
}
