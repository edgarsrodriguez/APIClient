package com.example.edgar.apiclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.edgar.apiclient.Util.PostsAdapter;

import java.util.ArrayList;

public class NewPostActivity extends AppCompatActivity {
    PostsAdapter oPostsAdapter;
    ListView oListView;
    ArrayList<Post> PostList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        oListView = (ListView) findViewById(R.id.ListView_Posts);
        oPostsAdapter = new PostsAdapter(this);
        oListView.setAdapter(oPostsAdapter);

        PostList  = getIntent().getParcelableArrayListExtra("data");

        oPostsAdapter.clear();
        for (Post oPost : PostList){
            oPostsAdapter.add(oPost);
        }
        oPostsAdapter.notifyDataSetChanged();
    }
}
