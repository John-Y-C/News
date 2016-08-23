package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

public class SplashActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.fl_splashactivity_animo)
    FrameLayout fl_splashactivity_animo;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        butterknife.ButterKnife.bind(this);
        getSupportActionBar().hide();

        animationSet = new AnimationSet(false);

        //float fromX, float toX, float fromY, float toY,
        //int pivotXType, float pivotXValue, int pivotYType, float pivotYValue
        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);

        RotateAnimation rotateAnimation=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);

        fl_splashactivity_animo.setAnimation(animationSet);
        animationSet.start();

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            //动画启动的时候
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画结束的时候
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                //动画结束后要finish掉当前页面，否则不会呈现目标页面
                finish();
            }

            //动画循环的时候
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
