package com.techscl.lovechat.db.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.techscl.lovechat.base.News;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 15/7/17.
 */
public class SQLiteDataBaseTools {

    private Context context;
    private SQLiteDatabase database;// 定义一个数据库对象

    public SQLiteDataBaseTools(Context context) {

        this.context = context;
        init();// 调用初始化方法
    }

    private void init() {// 初始化方法

        File file = context.getFilesDir();// 获得文件路径
        String path = file.getAbsolutePath() + SQLiteDataBaseManager.DATA_BASE_NAME;// 定义数据库名称
        SQLiteDataBaseManager manager = new SQLiteDataBaseManager(context, path, null, 1);//实例化SQLiteOpenHelper对象

        database = manager.getWritableDatabase();// 从SQLiteOpenHelper对象中获得一个可读写的SQLiteDataBase对象
    }

    /**
     * 插入数据方法
     * @param id
     * @param title
     * @param image
     * @param hitCount
     * @param commentCount
     * @param tableName
     * @return
     */
    public long insert(String id, String title, String image, String hitCount, String commentCount, String tableName) {// 向数据库中插入数据,return行号

        ContentValues values = new ContentValues();
        values.put(SQLiteDataBaseManager.NEWSID, id);
        values.put(SQLiteDataBaseManager.TITLE, title);
        values.put(SQLiteDataBaseManager.IMAGE, image);
        values.put(SQLiteDataBaseManager.HITCOUNT, hitCount);
        values.put(SQLiteDataBaseManager.COMMENTCOUNT, commentCount);

        long Id = database.insert(tableName, null, values);// 插入数据

        return Id;
    }

    /**
     * 查询某个表中的所有数据
     * @param tableName
     * @return
     */
    public List<News> query(String tableName) {// 查询所有信息
        String sql = "select * from " + tableName;
        Cursor cursor = database.rawQuery(sql, null);
        List<News> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            News news = new News();

            int newsId_index = cursor.getColumnIndex(SQLiteDataBaseManager.NEWSID);
            int title_index = cursor.getColumnIndex(SQLiteDataBaseManager.TITLE);
            int image_index = cursor.getColumnIndex(SQLiteDataBaseManager.IMAGE);
            int hitCount_index = cursor.getColumnIndex(SQLiteDataBaseManager.HITCOUNT);
            int commentCount_index = cursor.getColumnIndex(SQLiteDataBaseManager.COMMENTCOUNT);

            int newsId_type = cursor.getType(newsId_index);
            if (newsId_type == Cursor.FIELD_TYPE_STRING) {
                String newsId = cursor.getString(newsId_index);
                news.setNewsid(newsId);
            }
            int title_type = cursor.getType(title_index);
            if (title_type == Cursor.FIELD_TYPE_STRING) {
                String title = cursor.getString(title_index);
                news.setTitle(title);
            }
            int image_type = cursor.getType(image_index);
            if (image_type == Cursor.FIELD_TYPE_STRING) {
                String image = cursor.getString(image_index);
                news.setImage(image);
            }
            int hitCount_type = cursor.getType(hitCount_index);
            if (hitCount_type == Cursor.FIELD_TYPE_STRING) {
                String hitCount = cursor.getString(hitCount_index);
                news.setHitcount(hitCount);
            }
            int comment_type = cursor.getType(commentCount_index);
            if (comment_type == Cursor.FIELD_TYPE_STRING) {
                String comment = cursor.getString(commentCount_index);
                news.setCommentcount(comment);
            }
            list.add(news);
        }
        return list;
    }

    /**
     * 通过newsId查询数据
     * @param tableName
     * @param Id
     * @return
     */
    public List<News> queryById(String tableName, String Id) {
        String sql = "select * from " + tableName + " where 新闻id=" + Id;
        Cursor cursor = database.rawQuery(sql, null);
        List<News> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            News news = new News();

            int newsId_index = cursor.getColumnIndex(SQLiteDataBaseManager.NEWSID);
            int title_index = cursor.getColumnIndex(SQLiteDataBaseManager.TITLE);
            int image_index = cursor.getColumnIndex(SQLiteDataBaseManager.IMAGE);
            int hitCount_index = cursor.getColumnIndex(SQLiteDataBaseManager.HITCOUNT);
            int commentCount_index = cursor.getColumnIndex(SQLiteDataBaseManager.COMMENTCOUNT);

            int newsId_type = cursor.getType(newsId_index);
            if (newsId_type == Cursor.FIELD_TYPE_STRING) {
                String newsId = cursor.getString(newsId_index);
                news.setNewsid(newsId);
            }
            int title_type = cursor.getType(title_index);
            if (title_type == Cursor.FIELD_TYPE_STRING) {
                String title = cursor.getString(title_index);
                news.setTitle(title);
            }
            int image_type = cursor.getType(image_index);
            if (image_type == Cursor.FIELD_TYPE_STRING) {
                String image = cursor.getString(image_index);
                news.setImage(image);
            }
            int hitCount_type = cursor.getType(hitCount_index);
            if (hitCount_type == Cursor.FIELD_TYPE_STRING) {
                String hitCount = cursor.getString(hitCount_index);
                news.setHitcount(hitCount);
            }
            int comment_type = cursor.getType(commentCount_index);
            if (comment_type == Cursor.FIELD_TYPE_STRING) {
                String comment = cursor.getString(commentCount_index);
                news.setCommentcount(comment);
            }
            list.add(news);
        }
        return list;
    }

    /**
     * 删除某个表中所有数据
     * @param tableName
     */
    public void delete(String tableName) {
        String sql = "delete from " + tableName;
        database.execSQL(sql);
    }

    /**
     * 通过newsId删除
     * @param tableName
     * @param Id
     */
    public void deleteById(String tableName, String Id) {
        String sql = "delete from " + tableName + " where 新闻id=" + Id;
        database.execSQL(sql);
    }

}
