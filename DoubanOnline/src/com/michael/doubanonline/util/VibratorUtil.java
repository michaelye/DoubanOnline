package com.michael.doubanonline.util;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * 震动手机的工具类
 * */
public class VibratorUtil
{

	public static void Vibrate(Context context, long milliseconds)
	{
		Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(milliseconds);
	}

	public static void Vibrate(Context context, long[] pattern, boolean isRepeat)
	{
		Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(pattern, isRepeat ? 1 : -1);
	}
}
