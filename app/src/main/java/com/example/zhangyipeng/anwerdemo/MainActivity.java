package com.example.zhangyipeng.anwerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhangyipeng.anwerdemo.adapter.LayoutAdapter;
import com.example.zhangyipeng.anwerdemo.adapter.TopicAdapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewPager mRecyclerView;
    private LayoutAdapter layoutAdapter;
    private SlidingUpPanelLayout mLayout;
    private TopicAdapter topicAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void zeroClick(View view) {

        startActivity(new Intent(this,ReadActivity.class));
    }

    public void oneClick(View view) {

        startActivity(new Intent(this,AnwerActivity.class));
    }

    public void twoClick(View view) {

        startActivity(new Intent(this,AnswerReadActivity.class));
    }


    public void threeClick(View view) {
        startActivity(new Intent(this,CourseActivity.class));

    }

}
