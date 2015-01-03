package com.michael.doubanonline.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.michael.doubanonline.MyApplication;
import com.michael.doubanonline.util.L;

/**
 * 数据库管理类，将对数据库的各种操作封装为方法 
 * 
 * @author Michael
 * 
 * */
public class DBManager
{
	/**活动列表，本日最热*/
	public static final int ONLINE_LIST_DAY = 1000;
	/**活动列表，本周最热*/
	public static final int ONLINE_LIST_WEEK = 2000;
	/**活动列表，最新活动*/
	public static final int ONLINE_LIST_LATEST = 3000;
	/**活动列表，我的活动*/
	public static final int ONLINE_LIST_ME = 4000;
	/**用户授权信息*/
	public static final int USER_CERTIFICATION = 1111;
	/**用户个人信息*/
	public static final int USER_INFO = 2222;
	
	
	private static final String TAG = "DBManagers";
	
	private static DBHelper helper;

	private static DBManager uniqueInstance;

	/**
	 * 单例
	 * */
	public static DBManager getInstance()
	{
		if (uniqueInstance == null)
		{
			uniqueInstance = new DBManager();
		}
		return uniqueInstance;
	}

	private DBManager()
	{
		helper = new DBHelper(MyApplication.applicationContext);
	}
	
	/**
	 * 这里的CacheType就是唯一标识一个评论的
	 * */
	public void insertCommentCache(long cacheId, String content)
	{
		if(isCommentCacheExist(cacheId))
		{
			deleteCommentCache(cacheId);
		}
		String sql = "INSERT INTO comments_cache(cacheType, content)VALUES(?,?)";
		SQLiteDatabase db = helper.getWritableDatabase();
		SQLiteStatement insertStmt = db.compileStatement(sql);
		insertStmt.clearBindings();
		insertStmt.bindLong(1, cacheId);
		insertStmt.bindString(2, content);
		insertStmt.executeInsert();
		db.close();
		L.e(TAG, "插入缓存");
	}
	
	public String getCommentCache(long cacheId)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql = "SELECT * FROM comments_cache WHERE cacheType = " + cacheId;
		Cursor cursor = db.rawQuery(sql, new String[] {});
		String cache = null;
		if (cursor.moveToFirst())
		{
			cache = cursor.getString(2);
		}
		if (cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		db.close();
		if (cursor.getCount() == 0)
		{
			L.e(TAG, "当前缓存不存在，返回null");
			return null;
		}
		else
		{
			L.e(TAG, "当前缓存存在, 成功返回");
			return cache;
		}
	}
	
	public boolean isCommentCacheExist(long cacheId)
	{
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "SELECT * FROM comments_cache WHERE cacheType = " + cacheId;
		Cursor cursor = db.rawQuery(sql, new String[] {});
		int count = cursor.getCount();
		db.close();
		if (count > 0)
		{
			L.e(TAG, "缓存存在");
			return true;
		}
		else
		{
			L.e(TAG, "缓存不存在");
			return false;
		}
	}
	
	public void deleteCommentCache(long cacheId)
	{
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "DELETE FROM comments_cache WHERE cacheType = " + cacheId;
		db.execSQL(sql);
		db.close();
		L.e(TAG, "删除缓存");
	}
	
	/**
	 * 清空表数据
	 * */
	public void clearCommentCache()
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql = "DELETE FROM comments_cache";
		db.execSQL(sql);
		db.close();
		L.e(TAG, "清空所有缓存");
	}

	//------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------
	
	/**
	 * 插入缓存,如果存在就删除
	 * */
	public void insertCache(int cacheType, String content)
	{
		if(isCacheExist(cacheType))
		{
			deleteCache(cacheType);
		}
		String sql = "INSERT INTO main_cache(cacheType, content)VALUES(?,?)";
		SQLiteDatabase db = helper.getWritableDatabase();
		SQLiteStatement insertStmt = db.compileStatement(sql);
		insertStmt.clearBindings();
		insertStmt.bindLong(1, cacheType);
		insertStmt.bindString(2, content);
		insertStmt.executeInsert();
		db.close();
		L.e(TAG, "插入缓存");
	}
	
	/**
	 * 更新缓存
	 * */
	public void updateCache(int cacheType, String content)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues dataToInsert = new ContentValues();
		dataToInsert.put("cacheType", cacheType);
		dataToInsert.put("content", content);
		String where = " cacheType = " + "\"" + cacheType + "\"";
		db.update("main_cache", dataToInsert, where, null);
		db.close();
		L.e(TAG, "更新缓存");
	}

	/**
	 * 获取缓存
	 * */
	public String getCache(int cacheType)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql = "SELECT * FROM main_cache WHERE cacheType = " + cacheType;
		Cursor cursor = db.rawQuery(sql, new String[] {});
		String cache = null;
		if (cursor.moveToFirst())
		{
			cache = cursor.getString(2);
		}
		if (cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		db.close();
		if (cursor.getCount() == 0)
		{
			L.e(TAG, "当前缓存不存在，返回null");
			return null;
		}
		else
		{
			L.e(TAG, "当前缓存存在, 成功返回");
			return cache;
		}
	}
	
	/**
	 * 获取缓存的时间
	 * */
	public String getCacheTime(int cacheType)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql = "SELECT * FROM main_cache WHERE cacheType = " + cacheType;
		Cursor cursor = db.rawQuery(sql, new String[] {});
		String cache = null;
		if (cursor.moveToFirst())
		{
			cache = cursor.getString(3);
		}
		if (cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		db.close();
		if (cursor.getCount() == 0)
		{
			L.e(TAG, "当前缓存不存在，返回null");
			return null;
		}
		else
		{
			L.e(TAG, "当前缓存存在, 成功返回");
			return cache;
		}
	}

	/**
	 * 缓存是否存在
	 * */
	public boolean isCacheExist(long cacheType)
	{
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "SELECT * FROM main_cache WHERE cacheType = " + cacheType;
		Cursor cursor = db.rawQuery(sql, new String[] {});
		int count = cursor.getCount();
		db.close();
		if (count > 0)
		{
			L.e(TAG, "缓存存在");
			return true;
		}
		else
		{
			L.e(TAG, "缓存不存在");
			return false;
		}
	}

	/**
	 * 删除缓存
	 * */
	public void deleteCache(long cacheType)
	{
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "DELETE FROM main_cache WHERE cacheType = " + cacheType;
		db.execSQL(sql);
		db.close();
		L.e(TAG, "删除缓存");
	}

	/**
	 * 清空缓存
	 * */
	public void clearCache()
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql = "DELETE FROM main_cache";
		db.execSQL(sql);
		db.close();
		L.e(TAG, "清空所有缓存");
	}
}
