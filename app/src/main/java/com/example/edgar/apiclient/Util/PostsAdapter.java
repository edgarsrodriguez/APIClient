package com.example.edgar.apiclient.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.edgar.apiclient.Post;
import com.example.edgar.apiclient.R;

/**
 * Created by Edgar on 10/13/2017.
 */

public class PostsAdapter extends ArrayAdapter<Post> {
    public PostsAdapter(Context context) {
        super(context, R.layout.post_row,R.id.text_postID);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);
        TextView text_postID = (TextView) oView.findViewById(R.id.text_postID);
        TextView text_userId = (TextView) oView.findViewById(R.id.text_userId);
        TextView text_title = (TextView) oView.findViewById(R.id.text_title);
        TextView text_body = (TextView) oView.findViewById(R.id.text_body);

        Post post =this.getItem(position);
        text_postID.setText(post.getPostID() + "");
        text_userId.setText(post.getUserID() + "");
        text_title.setText(post.getTitle());
        text_body.setText(post.getBody());
        return oView;
    }
}
