package com.techscl.lovechat.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dllo on 15/7/17.
 */
public class SQLiteDataBaseManager extends SQLiteOpenHelper {
    public static final String NEWS = "最新资讯", RANK = "排行榜", ANDROID = "安卓新闻", IOS = "苹果新闻", WP = "WP新闻", DIGITAL = "数码新闻", WINDOWS = "Windows新闻", LIKE = "收藏",LOG="登录记录";// 定义表名
    public static final String NEWSID = "新闻id", TITLE = "新闻标题";// 定义列名
    public static final String IMAGE = "标题图片地址", HITCOUNT = "热度";// 定义列名
    public static final String COMMENTCOUNT = "评论数";
    public static final String DATA_BASE_NAME = "/NewsList.db";// 定义数据库名称常量

    public SQLiteDataBaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);// Activity对象, 数据库名称, 工厂类对象null,当前版本号
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {// 第一次创建实例时调用的方法,sqLiteDatabase指的是在构造方法中传递的参数创建的数据库对象
        String news = "create table "
                + NEWS + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(news);// 执行创建

        String rank = "create table "
                + RANK + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(rank);// 执行创建

        String android = "create table "
                + ANDROID + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(android);// 执行创建

        String ios = "create table "
                + IOS + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(ios);// 执行创建

        String wp = "create table "
                + WP + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(wp);// 执行创建

        String digital = "create table "
                + DIGITAL + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(digital);// 执行创建

        String windows = "create table "
                + WINDOWS + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(windows);// 执行创建
        String like = "create table "
                + LIKE + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(like);// 执行创建
        String log = "create table "
                + LOG + " (_id integer primary key autoincrement, "
                + NEWSID + " varchar(255)," + TITLE + " varchar(255),"
                + IMAGE + " varchar(255)," + HITCOUNT + " varchar(255),"
                + COMMENTCOUNT + " varchar(255))";// 创建sql语句
        sqLiteDatabase.execSQL(log);// 执行创建
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {// 当数据库版本发生变化时,系统自动回调的升级方法; sqLiteDatabase对应的数据库对象, 之前的版本号, 当前最新的版本号


    }

}
