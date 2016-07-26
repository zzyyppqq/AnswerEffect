package com.example.zhangyipeng.anwerdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.zhangyipeng.anwerdemo.adapter.LayoutAdapter;
import com.example.zhangyipeng.anwerdemo.adapter.TopicAdapter;
import com.example.zhangyipeng.anwerdemo.bean.AnwerInfo;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
