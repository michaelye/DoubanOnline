package com.michael.doubanonline.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.michael.doubanonline.MyApplication;

public class VersionUtil
{

	/**
	 * 获得版本号
	 * */
	public static int getVersionCode()
	{
		int version = 0;
		PackageInfo pInfo = null;
		try
		{
			pInfo = MyApplication.applicationContext.getPackageManager().getPackageInfo(MyApplication.applicationContext.getPackageName(), 0);
		} catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}

		if (pInfo != null)
		{
			version = pInfo.versionCode;
		}
		return version;
	}
}
