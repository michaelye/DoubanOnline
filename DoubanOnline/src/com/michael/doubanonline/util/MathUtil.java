package com.michael.doubanonline.util;

/**
 * 
 * @author Michael
 * @version create time：2013-3-19 下午8:17:07
 */
public class MathUtil {

	/**
	 * 取得行数
	 * */
	public static int getAbsoluteInt(int a, int b) {
		
		return (((double) a / (double) b) > (a / b) ? a / b + 1 : a / b);
	}
	
	/**
	 * 根据现在所有的图片和已经下载的图片，以及每次下载图片的数量，得到这次要加载的数量
	 * 
	 * @param allCount 服务器上当前有多少张图片
	 * @param currentCount 当前已经下载了多少张图片
	 * @param wannaCount 每次下载的图片的数量
	 * @return 本次可以下载的图片的数量<=countPerTime，返回0，说明没有需要加载的了。
	 */
	public static int getWannaCount(int allCount, int currentCount, int countPerTime){
		
		int gap = allCount - currentCount;
		if(gap < 0){
			
			throw new IllegalArgumentException("allCount:"+allCount+"can not big than currentCount:"+currentCount);
		}else if(gap == 0){
			
			return 0;
		}else if(gap >= countPerTime){
			
			return countPerTime;
		}else{
			
			return gap;
		}
	}
}
