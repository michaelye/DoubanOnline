package com.michael.doubanonline.user;

import org.apache.http.Header;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.michael.doubanonline.MyApplication;
import com.michael.doubanonline.db.DBManager;
import com.michael.doubanonline.http.RequestTask;
import com.michael.doubanonline.util.L;
import com.michael.doubanonline.util.ToastUtil;

public class AccountCerfitication
{

	public static final String apiKey = "0e4b060dd9b8b2382ae112298d2a16a8";
	public static final String secret = "4c629eddc73f195e";
	public static final String callbackMethod = "https://github.com/michaelye/DoubanOnLine.git/";
	public static final String result_msg_head = callbackMethod + "back?code=";
	
	private DBManager dbManager;
	
	public AccountCerfitication()
	{
		dbManager = DBManager.getInstance();
	}
	
	/**
	 * AccessToken过期的时候可以通过这个方法获得新的AccessToken
	 * */
	public void getNewAccessTokenByRefreshToken(String refreshToken)
	{
		getAccessToken(refreshToken, true);
	}
	
	/**
	 * 根据用户授权码获取AccessToken
	 * */
	public void getAccessTokenByAuthorizationCode(String authorization_code)
	{
		getAccessToken(authorization_code, false);
	}
	
	private void getAccessToken(String code, boolean isByRefreshToken)
	{
		RequestParams params = new RequestParams();
		params.put("client_id", apiKey);
		params.put("client_secret", secret);
		params.put("redirect_uri", callbackMethod);
//		params.put("grant_type", "authorization_code");
		String key = "";
		if(isByRefreshToken)
		{
			params.put("grant_type", "refresh_token");
			key = "refresh_token";
		}
		else
		{
			params.put("grant_type", "authorization_code");
			key = "code";
		}
		params.put(key, code);
		
		L.e("TOKEN:params:", params+"");
		
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.setCookieStore(MyApplication.myCookieStore);
		asyncHttpClient.setTimeout(RequestTask.TIME_OUT);
		asyncHttpClient.setMaxRetriesAndTimeout(1, RequestTask.TIME_OUT);
		//这里只能用post！！！
		asyncHttpClient.post("https://www.douban.com/service/auth2/token?", params, new AsyncHttpResponseHandler() {//这里用get才可以！！！用post提示时间格式错误

			@Override
			public void onStart()
			{
				if(onAccessTokenStateListener != null)
				{
					onAccessTokenStateListener.onStart();
				}
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response)
			{
				String result = new String(response);
				L.e("用户授权：", result);
				Certification certification = JSON.parseObject(result, Certification.class);
				if(certification != null)
				{
					dbManager.insertCache(DBManager.USER_CERTIFICATION, result);
					if(onAccessTokenStateListener != null)
					{
						onAccessTokenStateListener.onSuccess(certification.getAccess_token());
					}
				}
				else
				{
					ToastUtil.show("数据解析失败");
					if(onAccessTokenStateListener != null)
					{
						onAccessTokenStateListener.onFailure();
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
			{
				if(responseBody != null)
				{
					L.e("onFailure:", new String(responseBody));
				}
				else
				{
					ToastUtil.show("获取用户授权失败，错误信息为空");
				}
				ToastUtil.show("获取用户授权失败");
				if(onAccessTokenStateListener != null)
				{
					onAccessTokenStateListener.onFailure();
				}
			}

			@Override
			public void onFinish()
			{
				if(onAccessTokenStateListener != null)
				{
					onAccessTokenStateListener.onFinish();
				}
			}

		});
	}
	
	private OnAccessTokenStateListener onAccessTokenStateListener;
	
	public void setOnAccessTokenStateListener(OnAccessTokenStateListener onAccessTokenStateListener)
	{
		this.onAccessTokenStateListener = onAccessTokenStateListener;
	}
	
	public interface OnAccessTokenStateListener
	{
		public void onStart();
		public void onSuccess(String accessToken);
		public void onFailure();
		public void onFinish();
	}
	
	
	/**
	 * 根据AccessToken获取用户信息
	 * */
	public void getUserInfoByAccessToken(String accessToken)
	{
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.setCookieStore(MyApplication.myCookieStore);
		asyncHttpClient.setTimeout(RequestTask.TIME_OUT);
		asyncHttpClient.setMaxRetriesAndTimeout(1, RequestTask.TIME_OUT);
		asyncHttpClient.addHeader("Authorization",  "Bearer " + accessToken);
		//注意这里只能用get!!!
		asyncHttpClient.get("https://api.douban.com/v2/user/~me", null, new AsyncHttpResponseHandler() {//这里用get才可以！！！用post提示时间格式错误

			@Override
			public void onStart()
			{
				if(onUserStateListener != null)
				{
					onUserStateListener.onStart();
				}
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response)
			{
				String result = new String(response);
				L.e("用户信息：", result);
				UserInfo userInfo = JSON.parseObject(result, UserInfo.class);
				if(userInfo != null)
				{
					ToastUtil.show("恭喜您登录成功~");
					dbManager.insertCache(DBManager.USER_INFO, result);
					if(onUserStateListener != null)
					{
						onUserStateListener.onSuccess(userInfo);
					}
				}
				else
				{
					ToastUtil.show("数据解析失败");
					if(onUserStateListener != null)
					{
						onUserStateListener.onFailure();
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
			{
				if(responseBody != null)
				{
					L.e("onFailure:", new String(responseBody));
				}
				else
				{
					ToastUtil.show("获取用户信息失败，错误信息为空");
				}
				ToastUtil.show("获取用户信息失败");
				if(onUserStateListener != null)
				{
					onUserStateListener.onFailure();
				}
			}

			@Override
			public void onFinish()
			{
				if(onUserStateListener != null)
				{
					onUserStateListener.onFinish();
				}
			}

		});
	}
	
	private OnUserStateListener onUserStateListener;
	
	public void setOnUserStateListener(OnUserStateListener onUserStateListener)
	{
		this.onUserStateListener = onUserStateListener;
	}
	
	public interface OnUserStateListener
	{
		public void onStart();
		public void onSuccess(UserInfo userInfo);
		public void onFailure();
		public void onFinish();
	}
}
