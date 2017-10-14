package com.example.edgar.apiclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.edgar.apiclient.Util.CommentAdapter;

import java.util.ArrayList;

public class NewCommentActivity extends AppCompatActivity {
    CommentAdapter oCommentAdapter;
    ListView oListView;
    ArrayList<Comment> CommentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);
        oListView = (ListView) findViewById(R.id.ListView_Comments);
        oCommentAdapter = new CommentAdapter(this);
        oListView.setAdapter(oCommentAdapter);
        CommentList  = getIntent().getParcelableArrayListExtra("data");

        oCommentAdapter.clear();
        for (Comment comment: CommentList){
            oCommentAdapter.add(comment);
        }
        oCommentAdapter.notifyDataSetChanged();
    }
}
