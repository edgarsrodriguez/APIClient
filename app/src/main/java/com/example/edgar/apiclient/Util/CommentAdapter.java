package com.example.edgar.apiclient.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.edgar.apiclient.Comment;
import com.example.edgar.apiclient.Post;
import com.example.edgar.apiclient.R;

import java.util.ArrayList;

/**
 * Created by Edgar on 10/13/2017.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {
    public CommentAdapter(Context context) {
        super(context, R.layout.comment_row,R.id.text_commentID);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);
        TextView text_commentID = (TextView) oView.findViewById(R.id.text_commentID);
        TextView text_postID = (TextView) oView.findViewById(R.id.text_postID);
        TextView text_name = (TextView) oView.findViewById(R.id.text_name);
        TextView text_email = (TextView) oView.findViewById(R.id.text_email);
        TextView text_body = (TextView) oView.findViewById(R.id.text_body);

        Comment comment =this.getItem(position);
        text_commentID.setText(comment.getCommentID() + "");
        text_postID.setText(comment.getPostID() + "");
        text_name.setText(comment.getName());
        text_email.setText(comment.getEmail());
        text_body.setText(comment.getBody());
        return oView;
    }
}
