package com.michael.doubanonline.util;

import android.util.Log;

/**
 * 调试日志类
 * 
 * @author Michael
 * */
public class L
{
	public static final boolean isDebug = true;// Log日志的开关

	public static void e(String tag, String message)
	{
		e(tag, message, null);
	}

	public static void e(String tag, String message, Throwable t)
	{
		if (isDebug)
			Log.e(tag, message, t);
	}

	public static void v(String tag, String message)
	{
		v(tag, message, null);
	}

	public static void v(String tag, String message, Throwable t)
	{
		if (isDebug)
			Log.v(tag, message, t);
	}

	public static void i(String tag, String message)
	{
		i(tag, message, null);
	}

	public static void i(String tag, String message, Throwable t)
	{
		if (isDebug)
			Log.i(tag, message, t);
	}

	public static void d(String tag, String message)
	{
		d(tag, message, null);
	}

	public static void d(String tag, String message, Throwable t)
	{
		if (isDebug)
			Log.d(tag, message, t);
	}

	public static void w(String tag, String message)
	{
		w(tag, message, null);
	}

	public static void w(String tag, String message, Throwable t)
	{
		if (isDebug)
			Log.w(tag, message, t);
	}
}
