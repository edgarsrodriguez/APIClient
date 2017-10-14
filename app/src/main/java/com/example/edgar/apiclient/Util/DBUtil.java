package com.example.edgar.apiclient.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Edgar on 10/6/2017.
 */

public class DBUtil extends SQLiteOpenHelper {
    public static final String DBName = "PojosHermanos";
    public static final int DBVersion = 1;

    public static final String Table_Posts = "Posts";
    public static final String Post_ID = "PostID";
    public static final String User_ID = "UserID";
    public static final String Title = "Title";
    public static final String Body = "Body";

    public static final String Table_Comments = "Comments";
    public static final String Comment_ID = "CommentID";
    public static final String Name = "Name";
    public static final String Email = "Email";

    public static final String PostsDBCreate = "CREATE TABLE " + Table_Posts + " (" +
            Post_ID + " integer primary key, " +
            User_ID + " integer not null, " +
            Title + " text not null, " +
            Body + " text not null)";

    public static final String CommentsDBCreate = "CREATE TABLE " + Table_Comments + " (" +
            Comment_ID + " integer primary key, " +
            Post_ID + " integer not null, " +
            Name + " text not null, " +
            Email + " text not null, " +
            Body + " text not null)";

    public DBUtil(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PostsDBCreate);
        sqLiteDatabase.execSQL(CommentsDBCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Posts);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Comments);
        onCreate(sqLiteDatabase);
    }
}
