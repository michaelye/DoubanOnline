package com.michael.doubanonline.http;

import java.util.HashMap;

import org.apache.http.Header;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.michael.doubanonline.MyApplication;
import com.michael.doubanonline.util.L;
import com.michael.doubanonline.util.ToastUtil;

/**
 * 封装一个线程，这个线程负责所有基本的网络请求。外部通过传递接口名和参数进来实现访问
 * 
 * */
public class RequestTask
{
	/** 服务器异常提醒的内容 */
	public static String SERVER_ERROR = "服务器异常";
	/** 服务器正常的状态码 */
	public static int SUCCESS = 200;
	/** 超时的时间 */
	public static final int TIME_OUT = 30000;
	private Context context;
	private String logCatTip;
	private AsyncHttpClient asyncHttpClient;
	private OnTaskResultListener task;
	
	public RequestTask(Context context, String logCatTip)
	{
		this.context = context;
		this.logCatTip = logCatTip;
	}

	/**
	 * 设置对所发起请求的回调监听
	 * */
	public void setOnTaskResultListener2(OnTaskResultListener task2)
	{
		this.task = task2;
	}

	/**
	 * 回调接口
	 * */
	public interface OnTaskResultListener
	{
		public void onStart();

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
		if (action == null || action.equals(""))
		{
			ToastUtil.show("接口不能为空："+action);
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
					L.e("*****onStart*****", "" + logCatTip);
					if (task != null)
					{
						task.onStart();
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
						if (task != null)
						{
							task.onSuccess(result);
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
					if(responseBody != null)
					{
						L.e("*****onFailure*****", "statusCode:" + statusCode+" responseBody:"+new String(responseBody)+" error:"+error);
					}
					onLoadFail("对不起，请求失败 StatusCode:"+statusCode);
				}
			}

			@Override
			public void onFinish()
			{
				if (context != null)
				{
					L.e("*****onFinish*****", "" + logCatTip);
					if (task != null)
					{
						task.onFinish();
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


}
