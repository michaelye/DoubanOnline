package com.michael.doubanonline.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.michael.doubanonline.CertificationActivity;
import com.michael.doubanonline.db.DBManager;
import com.michael.doubanonline.util.L;

/**
 * 获取用户信息
 * */
public class AccountHelper
{

	/**
	 * 获取用户的账户信息
	 * 
	 * 如果返回null，说明没有用户信息
	 * */
	public static UserInfo getUserInfo(Context context)
	{
		DBManager dbManager = DBManager.getInstance();
		String cache = dbManager.getCache(DBManager.USER_INFO);
		UserInfo userInfo = JSON.parseObject(cache, UserInfo.class);
		if(userInfo == null)
		{
			intentToLoginActivity(context);
			return null;
		}
		return userInfo;
	}
	
	/**
	 * 如果没有Token或者过期
	 * */
	public static String getAccessToken(Context context)
	{
		DBManager dbManager = DBManager.getInstance();
		String cache = dbManager.getCache(DBManager.USER_CERTIFICATION);
		Certification certification = JSON.parseObject(cache, Certification.class);
		if(certification == null)
		{
			intentToLoginActivity(context);
			return null;
		}
		String cacheTime = dbManager.getCacheTime(DBManager.USER_CERTIFICATION);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		long cacheTimeLong = -1;
		try
		{
			cacheTimeLong=sdf.parse(cacheTime).getTime();
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}
//		L.e("CurrentTime:", System.currentTimeMillis()+" cacheTimeLong:"+cacheTimeLong+" cacheTime:"+cacheTime);
		L.e("时间差："+(System.currentTimeMillis() - cacheTimeLong), "过期时间："+(Long.parseLong(certification.getExpires_in())*1000 - 86400000));
		if((System.currentTimeMillis() - cacheTimeLong) > (Long.parseLong(certification.getExpires_in())*1000 - 86400000))//提前一天去重新获取Token
		{
			AccountCerfitication accountCerfitication = new AccountCerfitication();
			accountCerfitication.getNewAccessTokenByRefreshToken(certification.getRefresh_token());
		}
		return certification.getAccess_token();
	}
	
	/**
	 * 退出登录
	 * */
	public static void logout()
	{
		DBManager dbManager = DBManager.getInstance();
		dbManager.deleteCache(DBManager.USER_CERTIFICATION);
		dbManager.deleteCache(DBManager.USER_INFO);
	}
	
	/**
	 * 跳转到登录界面
	 * */
	private static void intentToLoginActivity(Context context)
	{
		Intent intent = new Intent(context, CertificationActivity.class);
		context.startActivity(intent);
	}
}
