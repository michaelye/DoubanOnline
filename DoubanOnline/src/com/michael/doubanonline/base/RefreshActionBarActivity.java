package com.michael.doubanonline.base;

import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.michael.doubanonline.R;

/**
 * ActionBar上面带有刷新ProgressBar的Activity
 * 
 * */
public class RefreshActionBarActivity extends BaseActivity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	/** 刷新 */
	private MenuItem menuRefresh;

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getSupportMenuInflater().inflate(R.menu.refresh_item, menu);
		// 刷新
		menuRefresh = menu.findItem(R.id.menu_refresh);
		menuRefresh.setActionView(R.layout.layout_menu_item_refresh);// 设置为一个ProgressBar
		setRefreshState(false);

		return super.onCreateOptionsMenu(menu);// 设置为不可点击
	}

	/**
	 * 设置当前刷新按钮是否可见，true设置为可见
	 * */
	public void setRefreshState(boolean isRefresh)
	{

		if (menuRefresh == null)
		{

			return;
		}
		if (isRefresh)
		{

			menuRefresh.setVisible(true);
		} else
		{

			menuRefresh.setVisible(false);
		}
	}

}
