package com.michael.doubanonline.http;

/**
 * 接口库
 * 
 * @author michael
 *
 */
public class InterfaceLib
{
	
	/**
	 * 获取线上活动列表
	 * 
	 * https://api.douban.com/v2/onlines?apikey=0e4b060dd9b8b2382ae112298d2a16a8&count=10&start=100&cate=day
	 * 
	 * */
	public static class GetOnlineList
	{
		/**接口名*/
		public static String apiActionName = "onlines";
		/***/
		public static String start = "start";
		/***/
		public static String count = "count";
		/***/
		public static String cate = "cate";
		
		/** 今日*/
		public static final String CATE_TYPE_DAY = "day";
		/** 本周*/
		public static final String CATE_TYPE_WEEK = "week";
		/** 最新*/
		public static final String CATE_TYPE_LATEST = "latest";
	}

	
	/**
	 * 根据目录Id获取图片
	 * 
	 * https://api.douban.com/v2/online/11454257/photos?apikey=0e4b060dd9b8b2382ae112298d2a16a8&start=10&count=10&order=desc&sortby=vote
	 * 
	 * http://developers.douban.com/wiki/?title=photo_v2#photo_list
	 *
	 * */
	public static class GetPhotos
	{
		/**接口名*/
		public static String apiActionName = "photos";
		/**
		 * 从0开始，默认为0
		 * */
		public static String start = "start";
		/**
		 * 请求的数量
		 * */
		public static String count = "count";
		/**
		 * asc, desc, 默认为相册本身的排序
		 * */
		public static String order = "order";
		/**
		 * time 上传时间，vote 推荐数，comment 回复数，默认为time
		 * */
		public static String sortby = "sortby";
		
		public static final String SORT_BY_TIME = "time";
		public static final String SORT_BY_VOTE = "vote";
		public static final String SORT_BY_COMMENT = "comment";
	}
	
	/**
	 * 获取照片评论
	 * 
	 * https://api.douban.com/v2/photo/1871677026/comments
	 * 
	 * */
	public static class GetPhotoComment
	{
		/**接口名*/
		public static String apiActionName = "comments";
		/**
		 * 从0开始，默认为0
		 * */
		public static String start = "start";
		/**
		 * 请求的数量
		 * */
		public static String count = "count";
	}
}
