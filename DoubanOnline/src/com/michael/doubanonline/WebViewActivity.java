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
 * 显示网页
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

		/**
		 * wvShow.setDownloadListener(new DownloadListener() {
		 * 
		 * @Override public void onDownloadStart(String url, String userAgent,
		 *           String contentDisposition, String mimetype, long
		 *           contentLength) { // 监听下载功能，当用户点击下载链接的时候，直接调用系统的浏览器来下载
		 * 
		 *           Uri uri = Uri.parse(url); Intent intent = new
		 *           Intent(Intent.ACTION_VIEW, uri); startActivity(intent); }
		 *           });
		 */
//		final Handler handler = new Handler();
		wvShow.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url)
			{
				// 页面加载完毕
				super.onPageFinished(view, url);
//				handler.post(new Runnable() {
//					@Override
//					public void run()
//					{
//						// 调用js，隐藏页面的上面的元素，这段js要放到服务器上面去
//						 if(null != hiddenJS && !hiddenJS.equals(""))
//						 {
//						 //
//						 wvShow.loadUrl("javascript:(function(){var banner = document.getElementById('j-banner');var footer = document.getElementsByTagName('footer');var header = document.getElementsByTagName('header');var groupLast = document.getElementsByClassName('group-last');var groupOther = document.getElementsByClassName('J_group-other');var moreDetail = document.getElementsByClassName('J_more-detail');var infoA = document.getElementsByClassName('info-a');if (banner) banner.style.display = 'none';if (footer[0]) footer[0].style.display = 'none';if (header[0]) header[0].style.display = 'none';if (groupLast[0]) groupLast[0].style.display = 'none';if (groupOther[0]) groupOther[0].style.display = 'none';if (moreDetail[0]) moreDetail[0].style.display = 'none';if (infoA[0]) infoA[0].parentNode.parentNode.parentNode.style.display = 'none';})()");
//						 wvShow.loadUrl(hiddenJS);
//						 }
//
//					}
//				});
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
//		wvShow.getSettings().setJavaScriptEnabled(true);// 设置支持脚本
//		wvShow.getSettings().setBuiltInZoomControls(true);// 设置支持缩放
//		wvShow.getSettings().setDefaultZoom(ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
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
