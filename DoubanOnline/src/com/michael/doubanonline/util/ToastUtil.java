package com.michael.doubanonline.util;

import android.widget.Toast;

import com.michael.doubanonline.MyApplication;

/**
 * 
 * Toast工具类
 * 
 * @author Michael
 * 
 */
public class ToastUtil
{

	public static final String ERROR_NET_DISABLE = "请检查您的网络";
	public static final String ERROR_SERVER_FAIL = "无法连接到服务器";
	public static final String ERROR_PAGE_CODE = "请求的页码有误";

	public static void show(String tip, boolean isLongTime)
	{

		Toast.makeText(MyApplication.applicationContext, tip, isLongTime == true ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	public static void show(String tip)
	{

		ToastUtil.show(tip, false);
	}
	
}
