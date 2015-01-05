package com.michael.doubanonline;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.michael.doubanonline.base.RefreshActionBarActivity;
import com.michael.doubanonline.user.AccountCerfitication;
import com.michael.doubanonline.user.AccountCerfitication.OnAccessTokenStateListener;
import com.michael.doubanonline.user.AccountCerfitication.OnUserStateListener;
import com.michael.doubanonline.user.UserInfo;
import com.michael.doubanonline.util.L;

/**
 * 豆瓣授权登录
 * 只能获取用户信息，没有什么用了
 * 
 * */
public class CertificationActivity extends RefreshActionBarActivity
{

	private static final String authorization_code_url_head = "https://www.douban.com/service/auth2/auth";
	private WebView wvLogin;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_certification);
		setActionBar();
		iniComponent();
	}

	private void setActionBar()
	{
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("授权登录");
		actionBar.setIcon(this.getResources().getDrawable(R.drawable.ic_launcher));
		actionBar.setDisplayHomeAsUpEnabled(true);// 让icon可以点击，并在icon的右边加上一个<箭头在onMenuItemSelected方法中被监听
		actionBar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.action_bar_bg));
		actionBar.setDisplayShowCustomEnabled(true);
	}

	/**
	 * 处理ActionBar的返回按钮
	 * */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		L.e("onMenuItemSelected", "itemID:" + item.getGroupId() + " " + item.getItemId());
		switch (item.getItemId())
		{
		case 16908332:// 这里只能先这样写了// TODO
			finish();
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	
	
	private String authorization_code;
	

	private void iniComponent()
	{
		wvLogin = (WebView) findViewById(R.id.wvLogin);
		String URL = authorization_code_url_head + "?client_id=" + AccountCerfitication.apiKey + "&redirect_uri=" + AccountCerfitication.callbackMethod + "back&response_type=code&scope=shuo_basic_r,shuo_basic_w,douban_basic_common";
		wvLogin.setWebViewClient(new WebViewClient()
		{

			@Override
			public void onPageFinished(WebView view, String url)
			{
				// 页面加载完毕
				super.onPageFinished(view, url);
				setRefreshState(false);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon)
			{
				// 页面开始加载时
				super.onPageStarted(view, url, favicon);
				setRefreshState(true);

				L.e("URL:", url);
				if (url.startsWith(AccountCerfitication.result_msg_head))
				{
					// 用户授权成功，可以获取authorization_code
					authorization_code = url.substring(AccountCerfitication.result_msg_head.length(), url.length());
//					ToastUtil.show("authorization_code:" + authorization_code);
					wvLogin.stopLoading();// 停止链接的跳转
					if (authorization_code != null && !authorization_code.equals(""))
					{
						// 启动一个线程去获取access_token
						final AccountCerfitication accountCerfitication = new AccountCerfitication();
						accountCerfitication.setOnAccessTokenStateListener(new OnAccessTokenStateListener()
						{
							@Override
							public void onStart()
							{
								setRefreshState(true);
							}
							
							@Override
							public void onSuccess(String accessToken)
							{
								accountCerfitication.setOnUserStateListener(new OnUserStateListener()
								{
									
									@Override
									public void onSuccess(UserInfo userInfo)
									{
										
									}
									
									@Override
									public void onStart()
									{
										setRefreshState(true);
									}
									
									@Override
									public void onFinish()
									{
										setRefreshState(false);
										finish();
									}
									
									@Override
									public void onFailure()
									{
										
									}
								});
								accountCerfitication.getUserInfoByAccessToken(accessToken);
							}
							
							@Override
							public void onFinish()
							{
								setRefreshState(false);
							}
							
							@Override
							public void onFailure()
							{
								
							}
						});
						accountCerfitication.getAccessTokenByAuthorizationCode(authorization_code);
					}

				}
			}

		});
		wvLogin.loadUrl(URL);
	}

	
}
