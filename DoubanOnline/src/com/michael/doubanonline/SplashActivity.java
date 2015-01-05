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

	/**
	 * 豆瓣Logo
	 * 
	 * 旋转动画结束的时候跳转到MainActivity
	 * 
	 * */
	private ImageView ivImage;

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_splash);

		ivImage = (ImageView) findViewById(R.id.ivImage);
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
				Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
				SplashActivity.this.startActivity(mainIntent);
				overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
				SplashActivity.this.finish();
			}
		});
		animation.setFillAfter(true);
		ivImage.startAnimation(animation);
	}

}
