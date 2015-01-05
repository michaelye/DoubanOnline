package com.michael.doubanonline;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.michael.doubanonline.base.RefreshActionBarActivity;
import com.michael.doubanonline.util.L;
import com.michael.doubanonline.util.ToastUtil;

/**
 * 显示一个网页
 * */
public class WebViewActivity extends RefreshActionBarActivity
{

	/**标题显示的内容*/
	public static final String KEY_TITLE = "KEY_TITLE";
	/**要加载的地址*/
	public static final String KEY_URL = "KEY_URL";
	/** 显示团购的WebView */
	private WebView wvShow;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		setActionBar();
		iniComponent();
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
	
	private void setActionBar()
	{
		ActionBar actionBar = getSupportActionBar();
		String title = getIntent().getStringExtra(KEY_TITLE);
		if(title == null)
		{
			title = "";
		}
		actionBar.setTitle(title);
		actionBar.setIcon(this.getResources().getDrawable(R.drawable.ic_launcher));
		actionBar.setDisplayHomeAsUpEnabled(true);// 让icon可以点击，并在icon的右边加上一个<箭头在onMenuItemSelected方法中被监听
		actionBar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.action_bar_bg));
		actionBar.setDisplayShowCustomEnabled(true);
	}
	
	private void iniComponent()
	{
		wvShow = (WebView) findViewById(R.id.web_view);
		
		String url = getIntent().getStringExtra(KEY_URL);
		wvShow.setWebViewClient(new WebViewClient() {

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
				// 页面开始加载
				super.onPageStarted(view, url, favicon);
				setRefreshState(true);
			}

		});
		if (url != null && !url.equals(""))
		{
			wvShow.loadUrl(url);
		}
		else
		{
			ToastUtil.show("URL地址不能为空");
		}
	}
	
}
