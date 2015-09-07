package com.hugo.lotteryassistant;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hugo.basecorelibrary.utilities.DensityUtil;

public class WelcomeActivity extends Activity {

    private ImageView imgBigPic;
    private ImageView imgLoadingLogo;
    private LinearLayout ll_content_text;
    private Button butStartLauncher;
    private boolean mAnimationFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initGuideView();
    }

    public void initGuideView() {
        imgBigPic = (ImageView) findViewById(R.id.im_loading_big_pic);
        imgLoadingLogo = (ImageView) findViewById(R.id.im_loading_logo);
        ll_content_text = (LinearLayout) findViewById(R.id.ll_content_text);
        butStartLauncher = (Button) findViewById(R.id.but_start_launcher);
        butStartLauncher.setVisibility(View.INVISIBLE);
        butStartLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        startAimation();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mAnimationFinished) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            return false;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }

    private void startAimation() {
        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("scaleX", 1.6f, 1f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("scaleY", 1.6f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(imgBigPic, p1, p2).setDuration(4000).start();
        p1 = PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1f);
        p2 = PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1f);
        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(imgLoadingLogo, p1, p2, p3).setDuration(4000).start();
        p1 = PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1f);
        p2 = PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1f);
        p3 = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1f);

        ObjectAnimator
            objectAnimator =
            ObjectAnimator.ofPropertyValuesHolder(ll_content_text, p1, p2, p3).setDuration(4000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startLauncherButton();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }

    private void startLauncherButton() {
        butStartLauncher.setVisibility(View.VISIBLE);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(butStartLauncher, "alpha",
                                                      0.0f, 1f);
        ObjectAnimator
            anim6 =
            ObjectAnimator.ofFloat(butStartLauncher, "y", butStartLauncher.getY(),
                                   butStartLauncher.getY() - DensityUtil.dip2px(this, 80)
            );
        AnimatorSet animSet = new AnimatorSet();
        animSet = new AnimatorSet();
        animSet.setDuration(1000);
        animSet.playTogether(anim5, anim6);
        animSet.start();
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationFinished = true;
            }
        });
    }
}
