package com.example.edgar.apiclient;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Edgar on 10/6/2017.
 */

public class Comment implements Parcelable{
    private int CommentID;
    private int PostID;
    private String Name;
    private String Email;
    private String Body;

    public Comment() {

    }

    public Comment(int commentID, int postID, String name, String email, String body) {
        CommentID = commentID;
        PostID = postID;
        Name = name;
        Email = email;
        Body = body;
    }

    protected Comment(Parcel in) {
        CommentID = in.readInt();
        PostID = in.readInt();
        Name = in.readString();
        Email = in.readString();
        Body = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(CommentID);
        parcel.writeInt(PostID);
        parcel.writeString(Name);
        parcel.writeString(Email);
        parcel.writeString(Body);
    }
}
