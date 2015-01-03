package com.michael.doubanonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * 启动界面
 * 
 * */
public class SplashActivity extends Activity 
{

//    private final int SPLASH_DISPLAY_LENGTH = 1300;

    private ImageView ivImage;
    
    @Override
    public void onCreate(Bundle bundle) 
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
//                SplashActivity.this.startActivity(mainIntent);
//                SplashActivity.this.finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);
        
        ivImage = (ImageView)findViewById(R.id.ivImage);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_rotate);
        animation.setAnimationListener(new AnimationListener()
		{
			
			@Override
			public void onAnimationStart(Animation animation)
			{
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation)
			{
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation)
			{
				Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
              SplashActivity.this.startActivity(mainIntent);
              overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
              SplashActivity.this.finish();
			}
		});
        animation.setFillAfter(true);
        ivImage.startAnimation(animation);
    }
    
}

