package com.michael.doubanonline.bean;

import java.util.List;

/**
 * 照片列表
 * 
 * */
public class PhotoList extends BeanBase
{

	/**
	 * 本次返回的数量
	 * */
	private String count;
	/**
	 * 本次返回的起始位置
	 * */
	private String start;
	/**
	 * 按时间排序还是热度排序
	 * */
	private String sortby;
	/**
	 * 所有照片的数量
	 * */
	private String total;
	/**
	 * 排序方式
	 * */
	private String order;
	/**
	 * 照片列表
	 * */
	private List<Photo> photos;

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

	public String getSortby()
	{
		return sortby;
	}

	public void setSortby(String sortby)
	{
		this.sortby = sortby;
	}

	public String getTotal()
	{
		return total;
	}

	public void setTotal(String total)
	{
		this.total = total;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public List<Photo> getPhotos()
	{
		return photos;
	}

	public void setPhotos(List<Photo> photos)
	{
		this.photos = photos;
	}

}
