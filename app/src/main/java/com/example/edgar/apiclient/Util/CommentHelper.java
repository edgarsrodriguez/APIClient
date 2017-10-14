package com.example.edgar.apiclient.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.edgar.apiclient.Comment;
import com.example.edgar.apiclient.Post;

import java.util.ArrayList;

/**
 * Created by Edgar on 10/6/2017.
 */

public class CommentHelper {
    private DBUtil dbHelper;
    private SQLiteDatabase database;
    private String[] Comments_Table_Columns = {DBUtil.Comment_ID, DBUtil.Post_ID, DBUtil.Name, DBUtil.Email, DBUtil.Body};

    public CommentHelper(Context context){
        dbHelper = new DBUtil(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Comment addComment(int commentID, int postID, String name, String email, String body) {
        ContentValues values = new ContentValues();
        values.put(DBUtil.Comment_ID,commentID);
        values.put(DBUtil.Post_ID,postID);
        values.put(DBUtil.Name,name);
        values.put(DBUtil.Email,email);
        values.put(DBUtil.Body,body);
        long lCommentID =  database.insert(DBUtil.Table_Comments, null, values);

        Cursor cursor = database.query(DBUtil.Table_Comments, Comments_Table_Columns,DBUtil.Comment_ID + " = " + lCommentID,null,null,null,null);
        cursor.moveToFirst();
        Comment oComment = parseComment(cursor);
        cursor.close();
        return oComment;
    }

    public int deleteComment(int nCommentID)
    {
        return database.delete(DBUtil.Table_Comments,DBUtil.Comment_ID + " = " + nCommentID, null);
    }

    public int deleteAllComments()
    {
        return database.delete(DBUtil.Table_Comments, null, null);
    }

    public ArrayList<Comment> getAllComments() {
        ArrayList<Comment> oLComments = new ArrayList<>();
        Cursor cursor = database.query(DBUtil.Table_Comments, Comments_Table_Columns,null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            oLComments.add(parseComment(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oLComments;
    }

    public Comment parseComment(Cursor cursor) {
        Comment oComment = new Comment();
        oComment.setCommentID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBUtil.Post_ID))));
        oComment.setPostID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBUtil.Post_ID))));
        oComment.setName(cursor.getString(cursor.getColumnIndex(DBUtil.Name)));
        oComment.setEmail(cursor.getString(cursor.getColumnIndex(DBUtil.Email)));
        oComment.setBody(cursor.getString(cursor.getColumnIndex(DBUtil.Body)));
        return oComment;
    }
}
