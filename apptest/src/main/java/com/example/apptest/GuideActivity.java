package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    private final int PAGECOUNT = 3;
    @BindView(R.id.vp_guideactivity_guide)
    ViewPager vp_guideactivity_guide;
    @BindView(R.id.ll_guideactivity_addindicator)
    LinearLayout ll_guideactivity_addindicator;
    @BindView(R.id.bt_guideactivity_enter)
    Button bt_guideactivity_enter;
    @BindView(R.id.rp_guideactivity_view)
    View rp_guideactivity_view;
    private List<ViewPageInfo> viewPageInfoList;
    private int[] imgId = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ViewPageInfo viewPageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        viewPageInfo = new ViewPageInfo();

        viewPageInfoList = new ArrayList<>();

        vp_guideactivity_guide.setAdapter(new MyVPAdapter());

        addViewPage();

        initIndicator();

        pageChange();
    }

    private void pageChange() {
        vp_guideactivity_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动的时候调用
            //position,当前页面位置
            //positionOffset下个页面拖出来的比例值
            //positionOffsetPixels下个页面拖出来的像素值
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("GuideActivity", position + "---" + positionOffset + "---" + positionOffsetPixels);
                //默认返回值是ViewGroup.LayoutParams，此返回值没有leftmargin，要转换为FrameLayout
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) rp_guideactivity_view.getLayoutParams();
                //由于positionOffset到新的页面后会恢复为0，所以要加上position*40
                layoutParams.leftMargin = (int) (positionOffset * 40) + position * 40;
                rp_guideactivity_view.setLayoutParams(layoutParams);

            }

            //当前页面
            @Override
            public void onPageSelected(int position) {
                Log.d("GuideActivity", "onPageSelected:" + position);
                if (position == PAGECOUNT - 1) {
                    bt_guideactivity_enter.setVisibility(View.VISIBLE);
                } else {
                    bt_guideactivity_enter.setVisibility(View.INVISIBLE);
                }
            }

//            * @param state The new scroll state.
//            * @see ViewPager#SCROLL_STATE_IDLE    静止
//            * @see ViewPager#SCROLL_STATE_DRAGGING    拉着拖动的时候
//            * @see ViewPager#SCROLL_STATE_SETTLING    松开手之后回位的状态
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("GuideActivity", "state:" + state);
            }
        });
    }

    //Indicator指示器
    private void initIndicator() {
        for (int i = 0; i < PAGECOUNT; i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.graypoint);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);//默认像素
            if (i != 0) {
                layoutParams.leftMargin = 20;
            }
            view.setLayoutParams(layoutParams);
            ll_guideactivity_addindicator.addView(view);
        }
    }

    private void addViewPage() {
        for (int i = 0; i < PAGECOUNT; i++) {
            //每次都要new 一个新的类来设置各属性
            viewPageInfo = new ViewPageInfo();
            viewPageInfo.pageview = new ImageView(GuideActivity.this);
            viewPageInfo.pageview.setBackgroundResource(imgId[i]);
            viewPageInfo.title = "title" + i;
            viewPageInfoList.add(viewPageInfo);
        }
    }

    @OnClick(R.id.bt_guideactivity_enter)
    public void onClick() {
        startActivity(new Intent(GuideActivity.this,HomeActivity.class));
        finish();
    }

    class MyVPAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return PAGECOUNT;
        }

        //默认写法view==object
        //view：当前页面上显示的视图
        //object：instantiateItem的一个返回值
        //实现者需要去判断这两东西是不是有关联
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //判断当前页面显示的视图是否为指定视图
            ViewPageInfo viewPageInfo = (ViewPageInfo) object;
            return view == viewPageInfo.pageview;
        }

        //instantiateItem-->给一个position去实例化一个item
        //默认返回view
        //instantiate实例化
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //返回当前位置的view
            ViewPageInfo viewPageInfo = viewPageInfoList.get(position);
            ImageView pageview = viewPageInfo.pageview;
            //-----区别于Listview，不是仅仅返回就行，还要加入到ViewGroup中-----
            container.addView(pageview);
            return viewPageInfo;//super.instantiateItem(container, position);
        }

        //把对应的page上的view从container中移除
        //注：继承的destroyItem要注释掉，否则会报异常（继承的方法就是报异常）
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            //每次只去新建当前显示的和下一个显示的
            //container中只会保存三个view，当前的，前一个，后一个
            //把对应page上的view从container中移除
            ViewPageInfo viewPageInfo = viewPageInfoList.get(position);
            ImageView pageview = viewPageInfo.pageview;
            //此处的参数为上面instantiateItem实例化的view，position相同
            container.removeView(pageview);
            //super.destroyItem(container, position, object);

        }
    }

    class ViewPageInfo {
        ImageView pageview;
        String title;
    }
}
