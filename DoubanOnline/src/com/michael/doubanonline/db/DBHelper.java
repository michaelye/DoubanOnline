package com.michael.doubanonline.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库生成类
 * 
 * @author Michael
 * */
public class DBHelper extends SQLiteOpenHelper
{

	private static final String DATABASE_NAME = "doubanonline.db";
	private static final int DATABASE_VERSION = 1;

	public DBHelper(Context context)
	{
		// CursorFactory设置为null,使用默认值
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// 数据库第一次被创建时onCreate会被调用
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		//存储的是其它的所有的缓存
		db.execSQL("CREATE TABLE IF NOT EXISTS main_cache (_id INTEGER PRIMARY KEY AUTOINCREMENT, cacheType INTEGER, content TEXT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
		//下次打开应用的时候清空这个表，该表存储的是看过的评论的信息
		db.execSQL("CREATE TABLE IF NOT EXISTS comments_cache (_id INTEGER PRIMARY KEY AUTOINCREMENT, cacheType INTEGER, content TEXT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
	}

	// 如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
	}
}
