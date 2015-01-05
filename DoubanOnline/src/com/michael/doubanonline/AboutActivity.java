package com.michael.doubanonline;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.michael.doubanonline.base.BaseActivity;
import com.michael.doubanonline.base.BaseCompatableAdapter;
import com.michael.doubanonline.util.ToastUtil;

/**
 * 关于的对话框
 * */
public class AboutActivity extends BaseActivity
{

	private static final String MY_APP_URL = "market://details?id=com.eu.exe";// 评价
	private static final String MY_DOUBAN_URL = "http://www.douban.com/people/67175724/";// 我的站点

	private static final String KEY_ID = "KEY_ID";// 方便setOnClickListener
	private static final String KEY_CONTENT = "KEY_CONTENT";
	private static final String KEY_TYPE = "KEY_TYPE";
	private static final String TYPE_COMMON = "TYPE_COMMON";// 普通的
	private static final String TYPE_DECLARE_RIGHT = "TYPE_DECLARE_RIGHT";// 声明权利
	private static final String TYPE_DECLARE_OPEN_SOURCE = "TYPE_DECLARE_OPEN_SOURCE";// 声明开源

	ArrayList<HashMap<String, Object>> data;

	private LinearLayout llBg;
	private ImageView ivIcon;
	private ListView lvList;

	private void iniComponent()
	{
		llBg = (LinearLayout) findViewById(R.id.llBg);
		ivIcon = (ImageView) findViewById(R.id.ivIcon);
		lvList = (ListView) findViewById(R.id.lvList);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);// 需要添加的语句
		iniComponent();

		// this.setTitle("关于豆瓣Online");
		this.setTitle("");

		data = new ArrayList<HashMap<String, Object>>();
		setCommonDateSource();
		final AboutAdapter adapter = new AboutAdapter(this, data);
		lvList.setAdapter(adapter);
		lvList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{

				@SuppressWarnings("unchecked")
				HashMap<String, Object> map = (HashMap<String, Object>) parent.getItemAtPosition(position);
				switch (Integer.valueOf(map.get(KEY_ID).toString()))
				{
				case 0:

					openMyDoubanWebsiteOrEvaluateMyApp(MY_APP_URL);
					break;
				case 1:

					openMyDoubanWebsiteOrEvaluateMyApp(MY_DOUBAN_URL);
					break;
				case 2:

					ToastUtil.show("发送反馈");
					break;
				case 3:

					setDeclareDataSource();
					startDeclareFadeAnimation();
					break;
				case 4:
				case 5:

					setCommonDateSource();
					break;
				default:
					break;
				}
				adapter.notifyDataSetChanged();
			}
		});

		ivIcon.setOnLongClickListener(new OnLongClickListener()
		{

			@Override
			public boolean onLongClick(View v)
			{

				startHiddenAnimFadeIn();
				return false;
			}
		});
	}

	/**
	 * 应用程序图标被长按时候的动画
	 * */
	private void startHiddenAnimFadeIn()
	{

		int childCount = llBg.getChildCount();
		if (childCount > 0)
		{

			for (int i = 0; i < childCount; i++)
			{

				llBg.getChildAt(i).setVisibility(View.GONE);
			}
		}
		llBg.setBackgroundResource(R.drawable.forget_love);
		Animation animFadeIn = AnimationUtils.loadAnimation(AboutActivity.this, R.anim.about_hidden_info_fade_in);
		animFadeIn.setAnimationListener(new AnimationListener()
		{

			@Override
			public void onAnimationStart(Animation animation)
			{
				// do nothing

			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
				// do nothing

			}

			@Override
			public void onAnimationEnd(Animation animation)
			{
				llBg.postDelayed(new Runnable()
				{

					@Override
					public void run()
					{

						startHiddenAnimFadeOut();
					}
				}, 3000);
			}
		});
		llBg.startAnimation(animFadeIn);
	}

	/**
	 * 启动隐藏的动画
	 * */
	private void startHiddenAnimFadeOut()
	{

		Animation animFadeOut = AnimationUtils.loadAnimation(AboutActivity.this, R.anim.about_hidden_info_fade_out);
		animFadeOut.setFillAfter(true);
		animFadeOut.setAnimationListener(new AnimationListener()
		{

			@Override
			public void onAnimationStart(Animation animation)
			{

			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{

			}

			@Override
			public void onAnimationEnd(Animation animation)
			{

				finish();
			}
		});
		llBg.startAnimation(animFadeOut);
	}

	/**
	 * 声明被点击时候的动画
	 * */
	private void startDeclareFadeAnimation()
	{

		Animation animation = AnimationUtils.loadAnimation(this, R.anim.about_list_view_fade_in);
		lvList.startAnimation(animation);
	}

	/**
	 * 设置普通的数据源
	 * */
	private void setCommonDateSource()
	{

		data.clear();
		HashMap<String, Object> map;

		// map = new HashMap<String, Object>();
		// map.put(KEY_ID, 0);
		// map.put(KEY_CONTENT, "评价此应用");
		// map.put(KEY_TYPE, TYPE_COMMON);
		// data.add(map);

		map = new HashMap<String, Object>();
		map.put(KEY_ID, 1);
		map.put(KEY_CONTENT, "我的豆瓣主页");
		map.put(KEY_TYPE, TYPE_COMMON);
		data.add(map);

		// map = new HashMap<String, Object>();
		// map.put(KEY_ID, 2);
		// map.put(KEY_CONTENT, "意见反馈");
		// map.put(KEY_TYPE, TYPE_COMMON);
		// data.add(map);

		map = new HashMap<String, Object>();
		map.put(KEY_ID, 3);
		map.put(KEY_CONTENT, "声明");
		map.put(KEY_TYPE, TYPE_COMMON);
		data.add(map);
	}

	/**
	 * 设置声明数据源
	 * */
	private void setDeclareDataSource()
	{

		data.clear();
		HashMap<String, Object> map;

		map = new HashMap<String, Object>();
		map.put(KEY_ID, 4);
		map.put(KEY_CONTENT, "本应用非豆瓣出品。\n本人保留所有权利。");
		map.put(KEY_TYPE, TYPE_DECLARE_RIGHT);
		data.add(map);

		map = new HashMap<String, Object>();
		map.put(KEY_ID, 5);
		map.put(KEY_CONTENT, "感谢使用的开源项目：\nJackWharton--ActionBarSherlock\nchrisbanes--AndroidPullToRefresh\nloopj--AndroidAsyncHttp\nnostra13--UniversalImageLoader");
		map.put(KEY_TYPE, TYPE_DECLARE_OPEN_SOURCE);
		data.add(map);
	}

	/**
	 * 打开我的网站或者评价我的应用程序
	 * 
	 * */
	private void openMyDoubanWebsiteOrEvaluateMyApp(String url)
	{

		// Uri uri = Uri.parse(url);
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		Intent intent = new Intent(this, WebViewActivity.class);
		intent.putExtra(WebViewActivity.KEY_TITLE, "我的豆瓣主页");
		intent.putExtra(WebViewActivity.KEY_URL, url);
		startActivity(intent);
	}

	class AboutAdapter extends BaseCompatableAdapter<HashMap<String, Object>>
	{

		public AboutAdapter(Context context, ArrayList<HashMap<String, Object>> data)
		{
			super(context, data);
		}

		class ViewHolder
		{

			TextView tvContent;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder viewHolder;
			if (convertView == null)
			{

				viewHolder = new ViewHolder();
				convertView = super.layoutInflater.inflate(R.layout.list_item_about, parent, false);
				viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
				convertView.setTag(viewHolder);
			} else
			{

				viewHolder = (ViewHolder) convertView.getTag();
			}

			HashMap<String, Object> map = super.data.get(position);
			viewHolder.tvContent.setText(map.get(KEY_CONTENT).toString());
			if (map.get(KEY_TYPE).equals(TYPE_COMMON))
			{

				viewHolder.tvContent.setGravity(Gravity.LEFT);
				viewHolder.tvContent.setPadding(8, 6, 7, 6);
				viewHolder.tvContent.setGravity(Gravity.CENTER);
				viewHolder.tvContent.setTextColor(Color.WHITE);
				viewHolder.tvContent.setTextSize(16);

			} else if (map.get(KEY_TYPE).equals(TYPE_DECLARE_RIGHT))
			{

				viewHolder.tvContent.setGravity(Gravity.LEFT);
				viewHolder.tvContent.setPadding(0, 8, 0, 8);
				viewHolder.tvContent.setGravity(Gravity.CENTER);
				viewHolder.tvContent.setTextColor(Color.WHITE);
				viewHolder.tvContent.setTextSize(16);

			} else if (map.get(KEY_TYPE).equals(TYPE_DECLARE_OPEN_SOURCE))
			{

				viewHolder.tvContent.setGravity(Gravity.LEFT);
				viewHolder.tvContent.setPadding(0, 8, 0, 8);
				viewHolder.tvContent.setGravity(Gravity.CENTER);
				viewHolder.tvContent.setTextColor(Color.GRAY);
				viewHolder.tvContent.setTextSize(14);
			}

			return convertView;
		}

	}

}
