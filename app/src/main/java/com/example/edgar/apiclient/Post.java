package com.example.edgar.apiclient;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Edgar on 10/6/2017.
 */

public class Post implements Parcelable{
    private int PostID;
    private int UserID;
    private String Title;
    private String Body;

    public Post() {

    }

    public Post(int postID, int userID, String title, String body) {
        PostID = postID;
        UserID = userID;
        Title = title;
        Body = body;
    }

    protected Post(Parcel in) {
        PostID = in.readInt();
        UserID = in.readInt();
        Title = in.readString();
        Body = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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
        parcel.writeInt(PostID);
        parcel.writeInt(UserID);
        parcel.writeString(Title);
        parcel.writeString(Body);
    }
}
