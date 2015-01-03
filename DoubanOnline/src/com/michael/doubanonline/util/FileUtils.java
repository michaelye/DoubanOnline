package com.michael.doubanonline.util;

import android.os.Environment;

public class FileUtils {

	/**
	 * 判断SD卡是否存在
	 */
	public static boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

}
