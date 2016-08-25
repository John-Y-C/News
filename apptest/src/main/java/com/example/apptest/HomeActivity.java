package com.example.apptest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.fragment.ContentFragment;
import com.example.fragment.LeftFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {

    ViewPager viewPager;
    private SlidingMenu slidingMenu;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setBehindContentView(R.layout.slidingmenu);
        slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //原页面保留300dp的宽度，从左边拉出来的显示(屏款-300)的宽度
        slidingMenu.setBehindOffset(350);

        //另外写两个fragment类替代原本fragment布局，除了原fragment布局依然存在外，也方便在fragment类内写一些操作
        fragmentManager = getFragmentManager();
        //开始操作，办理
        //可以在创建类的时候直接选择Fragment，效果一样，可免去替代操作
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_homeactivity_contentmenu, new ContentFragment(), "contentmenu");
        fragmentTransaction.replace(R.id.fl_homeactivity_leftmenu, new LeftFragment(), "leftmenu");
        fragmentTransaction.commit();

    }

    public void setSlidingMenuEnable(boolean enable) {
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    public LeftFragment getLeftFragment() {
        LeftFragment leftmenu = (LeftFragment) fragmentManager.findFragmentByTag("leftmenu");
        return leftmenu;
    }

    public ContentFragment getContentFragment() {
        ContentFragment contentmenu = (ContentFragment) fragmentManager.findFragmentByTag("contentmenu");
        return contentmenu;
    }
}
