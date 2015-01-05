package com.michael.doubanonline;

import java.io.File;

import android.app.Application;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

import com.loopj.android.http.PersistentCookieStore;
import com.michael.doubanonline.util.L;
import com.michael.doubanonline.util.VersionUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.URLConnectionImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

/**
 * 整个应用程序的配置
 * 
 * @author Michael
 * */
public class MyApplication extends Application
{
	/** 服务器异常提醒的内容 */
	public static String SERVER_ERROR = "服务器异常";
	/** 服务器正常的状态码 */
	public static int SUCCESS = 200;
	/** IP地址 */
	public static String IP = "https://api.douban.com/v2/";
	/** apikey */
	public static String apikey = "0e4b060dd9b8b2382ae112298d2a16a8";
	/** context */
	public static MyApplication applicationContext;
	/** 应用程序是否在运行 */
	public boolean APP_RUNNING = false;
	/** Cookie */
	public static PersistentCookieStore myCookieStore;
	/** 图片下载器 */
	public static ImageLoader imageLoader;
	/** 当前Apk的版本号 */
	public static int VERSION_CODE = 0;
	/** 下载的图片保存的路径*/
	public static final String ImageSavePath = Environment.getExternalStorageDirectory().getPath()+"/豆瓣Online/";
	/** 全局实例*/
	private static MyApplication instance;

	public static MyApplication getInstance()
	{
		return instance;
	}

	/**
	 * 由于百度Api库的原因，这个方法会被调用两次
	 * 
	 * */
	@Override
	public void onCreate()
	{
		super.onCreate();
		applicationContext = this;
		instance = this;
		APP_RUNNING = true;
		VERSION_CODE = VersionUtil.getVersionCode();
		myCookieStore = new PersistentCookieStore(MyApplication.applicationContext);
		iniImageLoader();
		L.isDebug = true;//是否开启日志调试。发布的时候需要关闭
	}

	public static ImageLoader getImageLoader()
	{
		return imageLoader;
	}

	/**
	 * http://seniorzhai.github.io/2014/10/14/UIL%E5%9B%BE%E7%89%87%E5%BC%82%E6%AD%A5%E5%8A%A0%E8%BD%BD%E5%BA%93%E7%9A%84%E4%BD%BF%E7%94%A8/
	 * */
	private void iniImageLoader()
	{
		File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(),"doubanonlineimageloader/Cache");
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		.memoryCache(new WeakMemoryCache())
		.denyCacheImageMultipleSizesInMemory()
		.offOutOfMemoryHandling()
		.discCacheExtraOptions(720, 480, CompressFormat.JPEG, 75)
		.discCache(new UnlimitedDiscCache(cacheDir))
		.imageDownloader(new URLConnectionImageDownloader(120 * 1000, 120 * 1000))
		.enableLogging()
		.build();
		ImageLoader.getInstance().init(config);
		imageLoader = ImageLoader.getInstance();
	}

}
