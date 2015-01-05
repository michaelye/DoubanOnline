package com.michael.doubanonline.bean;

import java.util.List;

/**
 * 活动列表
 * */
public class OnlineList extends BeanBase
{

	/**
	 * 本次返回的数量
	 * */
	private String count;
	/**
	 * 起始
	 * */
	private String start;
	/**
	 * 服务器一共有多少
	 * */
	private String total;
	/**
	 * 活动列表
	 * */
	private List<OnlineObject> onlines;

	public String getCount()
	{
		return count;
	}

	public void setCount(String count)
	{
		this.count = count;
	}

	public String getStart()
	{
		return start;
	}

	public void setStart(String start)
	{
		this.start = start;
	}

	public String getTotal()
	{
		return total;
	}

	public void setTotal(String total)
	{
		this.total = total;
	}

	public List<OnlineObject> getOnlines()
	{
		return onlines;
	}

	public void setOnlines(List<OnlineObject> onlines)
	{
		this.onlines = onlines;
	}

}
