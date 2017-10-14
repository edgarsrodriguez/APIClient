package com.example.edgar.apiclient.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.edgar.apiclient.Post;

import java.util.ArrayList;

/**
 * Created by Edgar on 10/6/2017.
 */

public class PostHelper {
    private DBUtil dbHelper;
    private SQLiteDatabase database;
    private String[] Posts_Table_Columns = {DBUtil.Post_ID, DBUtil.User_ID, DBUtil.Title, DBUtil.Body};

    public PostHelper(Context context){
        dbHelper = new DBUtil(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Post addPost(int postID, int userID, String title, String body) {
        ContentValues values = new ContentValues();
        values.put(DBUtil.Post_ID,postID);
        values.put(DBUtil.User_ID,userID);
        values.put(DBUtil.Title,title);
        values.put(DBUtil.Body,body);
        long lPostID =  database.insert(DBUtil.Table_Posts, null, values);

        Cursor cursor = database.query(DBUtil.Table_Posts, Posts_Table_Columns,DBUtil.Post_ID + " = " + lPostID,null,null,null,null);
        cursor.moveToFirst();
        Post oPost = parsePost(cursor);
        cursor.close();
        return oPost;
    }

    public int deletePost(int nPostID)
    {
        return database.delete(DBUtil.Table_Posts,DBUtil.Post_ID + " = " + nPostID, null);
    }

    public int deleteAllPosts()
    {
        return database.delete(DBUtil.Table_Posts, null, null);
    }

    public ArrayList<Post> getAllPosts() {
        ArrayList<Post> oLPosts = new ArrayList<>();
        Cursor cursor = database.query(DBUtil.Table_Posts, Posts_Table_Columns,null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            oLPosts.add(parsePost(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oLPosts;
    }

    public Post parsePost(Cursor cursor) {
        Post oPost = new Post();
        oPost.setPostID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBUtil.Post_ID))));
        oPost.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBUtil.User_ID))));
        oPost.setTitle(cursor.getString(cursor.getColumnIndex(DBUtil.Title)));
        oPost.setBody(cursor.getString(cursor.getColumnIndex(DBUtil.Body)));
        return oPost;
    }
}
