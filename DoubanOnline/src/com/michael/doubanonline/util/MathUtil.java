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
	public static int getAbsoluteInt(int a, int b)
	{
		return (((double) a / (double) b) > (a / b) ? a / b + 1 : a / b);
	}
}
