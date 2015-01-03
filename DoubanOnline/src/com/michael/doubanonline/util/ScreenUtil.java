package com.michael.doubanonline.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 这个类可以获得屏幕的相关信息，宽和高
 * 
 * @author Michael
 * */
public class ScreenUtil {

	/**
	 * 获取屏幕的宽度
	 * 
	 * @param Context
	 *            context
	 * 
	 * @return int width
	 * */
	public static int getScreenWidth(Context context) {

		DisplayMetrics dm = context.getApplicationContext().getResources()
				.getDisplayMetrics();

		return dm.widthPixels;
	}

	/**
	 * 获取屏幕的宽度
	 * 
	 * @param Context
	 *            context
	 * 
	 * @return int width
	 * */
	public static int getScreenHeight(Context context) {

		DisplayMetrics dm = context.getApplicationContext().getResources()
				.getDisplayMetrics();

		return dm.heightPixels;

	}
}
