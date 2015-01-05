package com.michael.doubanonline.base;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.actionbarsherlock.widget.ShareActionProvider;
import com.actionbarsherlock.widget.ShareActionProvider.OnShareTargetSelectedListener;
import com.michael.doubanonline.R;
import com.michael.doubanonline.util.L;
import com.michael.doubanonline.util.ToastUtil;

/**
 * ActionBar上面带有分享功能的Activity
 * 
 * */
public class ShareActionBarActivity extends BaseActivity {

	private ShareActionProvider mShareActionProvider;
	private String subject;
	private String content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	/** 刷新 */
	MenuItem menuRefresh;
	/** 分享 */
	MenuItem menuShare;
	
	/** 参加了该活动*/
	MenuItem subItemCamera;
	/** 参加了该活动*/
	MenuItem subItemGallery;
	/** 参加了该活动*/
	MenuItem subItemJoin;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getSupportMenuInflater().inflate(R.menu.refresh_share_camera_items, menu);
		//刷新
		menuRefresh = menu.findItem(R.id.menu_refresh);
		menuRefresh.setActionView(R.layout.layout_menu_item_refresh);// 设置为一个ProgressBar
		setRefreshState(false);
		//分享
		mShareActionProvider = (ShareActionProvider) menu.findItem(
				R.id.menu_share).getActionProvider();
		mShareActionProvider.setShareHistoryFileName(null);// 最经常被使用到的，会记录在这里，首先将这个设置为null（1）
		mShareActionProvider.setShareIntent(getDefaultShareIntent());
		mShareActionProvider
				.setOnShareTargetSelectedListener(new OnShareTargetSelectedListener() {// 并且需要设置这个监听器，自己去启动Intent，就不会被记录到history了（2）

					@Override
					public boolean onShareTargetSelected(
							ShareActionProvider source, Intent intent) {

						ShareActionBarActivity.this.startActivity(intent);
						return true;
					}
				});
		//TODO 因为没有接口，无法实现，所以隐藏掉
		menu.findItem(R.id.menu_camera).setVisible(false);
		//是否已经参加了这个活动
		SubMenu subMenu = menu.findItem(R.id.menu_camera).getSubMenu();
		subItemCamera = subMenu.findItem(R.id.sub_item_camera);
		subItemCamera.setEnabled(false);
		subItemGallery = subMenu.findItem(R.id.sub_item_photo);
		subItemGallery.setEnabled(false);
		subItemJoin = subMenu.findItem(R.id.sub_item_join);
		subItemJoin.setEnabled(false);
		return super.onCreateOptionsMenu(menu);// 设置为不可点击
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.sub_item_camera:

			ToastUtil.show("照相");
			break;
		case R.id.sub_item_photo:

			ToastUtil.show("照片");
			break;
		case R.id.sub_item_join:

			ToastUtil.show("参加");
			break;
		}
		return true;
	}
	
	/**
	 * 处理ActionBar的返回按钮
	 * */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		L.e("onMenuItemSelected", "itemID:" + item.getGroupId()+" "+item.getItemId());
		switch (item.getItemId()) {
		case 16908332://这里只能先这样写了// TODO 
			
			finish();
			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * 返回手机中具有分享功能的Intent
	 * */
	private Intent getDefaultShareIntent() {

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		if (subject != null) {

			intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		}
		if (content != null) {

			intent.putExtra(Intent.EXTRA_TEXT, content);
		}
		return intent;
	}

	/**
	 * 设置当前刷新按钮是否可见，true设置为可见
	 * */
	public void setRefreshState(boolean isRefresh) {

		if (menuRefresh == null) {

			return;
		}
		if (isRefresh) {

			menuRefresh.setVisible(true);
		} else {

			menuRefresh.setVisible(false);
		}
	}
	
	/**
	 * 设置是否已经参加了该活动
	 * */
	public void setHasJoined(boolean hasJoin){
		
		if(hasJoin){
			
			subItemJoin.setEnabled(false);
		}else{
			
			subItemJoin.setEnabled(true);
		}
	}

}
