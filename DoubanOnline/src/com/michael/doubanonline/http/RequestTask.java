package com.michael.doubanonline.http;

import java.util.HashMap;

import org.apache.http.Header;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.michael.doubanonline.BaseActivity;
import com.michael.doubanonline.MyApplication;
import com.michael.doubanonline.bean.BaseResponse;
import com.michael.doubanonline.util.L;
import com.michael.doubanonline.util.ToastUtil;

/**
 * 封装一个线程，这个线程负责所有基本的网络请求。外部通过传递接口名和参数进来实现访问
 * 
 * */
public class RequestTask
{
	private Context context;
	private String logCatTip;
	private boolean isLoading;
	private AsyncHttpClient asyncHttpClient;
	private OnTaskResultListener task;
	private OnTaskResultListener2 task2;
	/** 超时的时间 */
	public static final int TIME_OUT = 30000;

	public RequestTask(Context context)
	{
		this.context = context;
		this.logCatTip = "未知请求";
	}

	public RequestTask(Context context, String logCatTip)
	{
		this.context = context;
		this.logCatTip = logCatTip;
	}

	/**
	 * 设置对所发起请求的回调监听
	 * */
	public void setOnTaskResultListener(OnTaskResultListener task)
	{
		this.task = task;
	}

	/**
	 * 回调接口
	 * */
	public interface OnTaskResultListener
	{
//		public void onSuccess(Object object, String jsonResponse);
		public void onSuccess(String jsonResponse);

		public void onFail(String message);
	}

	/**
	 * 设置对所发起请求的回调监听
	 * */
	public void setOnTaskResultListener2(OnTaskResultListener2 task2)
	{
		this.task2 = task2;
	}

	/**
	 * 回调接口
	 * */
	public interface OnTaskResultListener2
	{
		public void onStart();

//		public void onSuccess(Object object, String jsonResponse);
		public void onSuccess(String jsonResponse);

		public void onFail(String message);

		public void onFinish();
	}

	/**
	 * 发起请求
	 * 
	 * */
	public <T> T request(String action, HashMap<String, String> map)
	{
		request(action, new RequestParams(map));
		return null;
	}

	/**
	 * 发起请求
	 * 
	 * */
	public <T> T request(String action, RequestParams params)
	{
		if (isLoading || action == null || action.equals(""))
		{
			ToastUtil.show("参数异常：isLoading："+isLoading+" action:"+action);
			return null;
		}
		String url = MyApplication.IP + action + "?apikey="+MyApplication.apikey+"&";// +params.toString();
		L.e("*****URL*****", url+params.toString());
		asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.setCookieStore(MyApplication.myCookieStore);
		asyncHttpClient.setTimeout(TIME_OUT);
		asyncHttpClient.setMaxRetriesAndTimeout(1, TIME_OUT);
		asyncHttpClient.get(url, params, new AsyncHttpResponseHandler() {//这里用get才可以！！！用post提示时间格式错误

			@Override
			public void onStart()
			{
				if (context != null)
				{
//					if (showDialog)
//					{
//						if (context instanceof BaseActivity)
//						{
//							((BaseActivity) context).showDialog(dialogTip);
//						}
//						else if(context instanceof BaseActivity)
//						{
//							((BaseActivity) context).showDialog(dialogTip);
//						}
//						else
//						{
//							L.i("警告：", "对话框Context不是ActionBarBaseActivity或SlideMenuBaseActivity的实例！");
//						}
//					}
					L.e("*****onStart*****", "" + logCatTip);
					if (task2 != null)
					{
						task2.onStart();
					}
				}
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response)
			{
				if (context != null)
				{
					L.e("*****onSuccess*****", "" + logCatTip);
					String result = new String(response);
					L.e("*****onSuccess*****", result);
					if (statusCode == RequestTask.SUCCESS)
					{
//						BaseResponse<T> res = JSON.parseObject(result, new TypeReference<BaseResponse<T>>() {});
//						if (res.getStatus().equals(SUCCESS_STATE))
//						{
//							T type = JSON.parseObject(res.getData().toString(), t);
//							if (task != null)
//							{
//								task.onSuccess(type, result);
//							}
//							if (task2 != null)
//							{
//								task2.onSuccess(type, result);
//							}
//						}
//						else
//						{
//							String errorTip = "";
//							String state = res.getStatus();
//							if (state.equals(UNLOGIN_STATE))
//							{
//								errorTip = "未登录";
//							}
//							else if (state.equals(PROGRAM_ERROR))
//							{
//								errorTip = "服务器数据错误";
//							}
//							else if (state.equals(BUSINESS_ERROR))
//							{
//								errorTip = res.getMessage();
//							}
//							onLoadFail(errorTip);
//						}
						
						if (task != null)
						{
							task.onSuccess(result);
						}
						if (task2 != null)
						{
							task2.onSuccess(result);
						}
						
					}
					else
					{
						onLoadFail("网络请求失败，请检查网络");
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
			{
				if (context != null)
				{
//					L.e("*****onFailure*****", "statusCode:" + statusCode+" responseBody:"+new String(responseBody)+" error:"+error);
					onLoadFail("对不起，请求失败");
				}
			}

			@Override
			public void onFinish()
			{
				if (context != null)
				{
					L.e("*****onFinish*****", "" + logCatTip);
//					if (context instanceof BaseActivity)
//					{
//						((BaseActivity) context).dismissDialog();
//					}
//					else
//					{
//						L.i("警告：", "对话框Context不是BaseActivity的实例！");
//					}
					if (task2 != null)
					{
						task2.onFinish();
					}
				}
			}

			/**
			 * 加载失败的时候界面所做的调整
			 * */
			private void onLoadFail(String message)
			{
				ToastUtil.show(message);
				if (task != null)
				{
					task.onFail(message);
				}
				if (task2 != null)
				{
					task2.onFail(message);
				}
			}
		});
		return null;
	}
	
	/**
	 * 取消线程
	 * */
	public void cancelRequest()
	{
		asyncHttpClient = null;
	}

	/** 服务器异常提醒的内容 */
	public static String SERVER_ERROR = "服务器异常";
	/** 服务器正常的状态码 */
	public static int SUCCESS = 200;
}
