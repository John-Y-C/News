package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.apptest.R;
import com.example.pages.BasePage;
import com.example.pages.PageGov;
import com.example.pages.PageHome;
import com.example.pages.PageNews;
import com.example.pages.PageSetting;
import com.example.pages.PageSmart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/22/022.
 */
public class ContentFragment extends Fragment {

    @BindView(R.id.vp_fragment_content)
    ViewPager vp_fragment_content;
    @BindView(R.id.rb_fragmentcontent_home)
    RadioButton rb_fragmentcontent_home;
    @BindView(R.id.rb_fragmentcontent_news)
    RadioButton rb_fragmentcontent_news;
    @BindView(R.id.rb_fragmentcontent_smart)
    RadioButton rb_fragmentcontent_smart;
    @BindView(R.id.rb_fragmentcontent_gov)
    RadioButton rb_fragmentcontent_gov;
    @BindView(R.id.rb_fragmentcontent_setting)
    RadioButton rb_fragmentcontent_setting;
    @BindView(R.id.rg_fragmentcontent_group)
    RadioGroup rg_fragmentcontent_group;

    private MyVPAdapter myVPAdapter;
    List<BasePage> basePageList= new ArrayList<>();
    private BasePage basePage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflate = View.inflate(getActivity(), R.layout.fragment_content, null);
        ButterKnife.bind(this, inflate);

        //集合添加顺序即为页面显示顺序
        basePageList.add(new PageHome(getActivity()));
        basePageList.add(new PageNews(getActivity()));
        basePageList.add(new PageSmart(getActivity()));
        basePageList.add(new PageGov(getActivity()));
        basePageList.add(new PageSetting(getActivity()));

        myVPAdapter = new MyVPAdapter();

        vp_fragment_content.setAdapter(myVPAdapter);

        final boolean[] flag = {true};

        rg_fragmentcontent_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_fragmentcontent_home:
                        //position可为范围内任意数，所有的page都按照一个page的设置来设置，当通过radiobutton切换到其他page时，
                        //又重新执行了一次setSlidingEnable方法，所有的page又根据新的设置来设置
                        basePageList.get(0).setSlidingEnable(false);
                        //radiobutton与各page绑定，第二个参数为设置滑动效果，true为平滑过渡
                        vp_fragment_content.setCurrentItem(0, false);
                        break;

                    case R.id.rb_fragmentcontent_news:
                        basePageList.get(1).setSlidingEnable(true);
                        vp_fragment_content.setCurrentItem(1, false);
//                        PageNews pageNews = new PageNews(getActivity());
//                        Log.d("ContentFragment", "flag[0]:" + flag[0]);
//                        if (flag[0]) {
//                            pageNews.getDataFromServer();
//                            flag[0] = false;
//                            Log.d("ContentFragment", "flag[0]:" + flag[0]);
//                        }

                        break;

                    case R.id.rb_fragmentcontent_smart:
                        basePageList.get(2).setSlidingEnable(false);
                        vp_fragment_content.setCurrentItem(2, false);
                        break;

                    case R.id.rb_fragmentcontent_gov:
                        basePageList.get(3).setSlidingEnable(false);
                        vp_fragment_content.setCurrentItem(3, false);
                        break;

                    case R.id.rb_fragmentcontent_setting:
                        basePageList.get(4).setSlidingEnable(false);
                        vp_fragment_content.setCurrentItem(4, false);
                        break;
                }
            }
        });

        rg_fragmentcontent_group.check(R.id.rb_fragmentcontent_home);

        return inflate;//super.onCreateView(inflater, container, savedInstanceState);
    }

    class MyVPAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return basePageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            Log.d("MyVPAdapter", "view==object:" + (view == object));
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            basePage = basePageList.get(position);
            container.addView(basePage.mViewPage);

            return basePage.mViewPage;//super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            basePage=basePageList.get(position);
            Log.d("MyVPAdapter", "remove page" + position);
            container.removeView(basePage.mViewPage);
            //container.removeView((View) object);

            //super.destroyItem(container, position, object);
        }
    }

    public PageNews getPageNews() {
        PageNews pageNews = null;
        if (basePageList != null && !basePageList.isEmpty()) {
            pageNews = (PageNews) basePageList.get(1);
        }
        return pageNews;
    }

}
