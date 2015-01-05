package com.michael.doubanonline.base;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

/**
 * 所有Activity的基类
 * 
 * 
 * @author Michael
 * */
public class BaseActivity extends SherlockActivity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	/**
	 * */
	public void onResume()
	{
		super.onResume();
	}

	/**
	 * 
	 * */
	public void onPause()
	{
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

}
