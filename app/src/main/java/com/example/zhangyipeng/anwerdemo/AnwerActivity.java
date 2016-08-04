package com.example.zhangyipeng.anwerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.zhangyipeng.anwerdemo.adapter.LayoutAdapter;
import com.example.zhangyipeng.anwerdemo.adapter.TopicAdapter;
import com.example.zhangyipeng.anwerdemo.bean.AnwerInfo;
import com.example.zhangyipeng.anwerdemo.bean.QuestionEntry;
import com.example.zhangyipeng.anwerdemo.bean.QuestionList;
import com.example.zhangyipeng.anwerdemo.view.FlipperLayout;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AnwerActivity extends AppCompatActivity implements FlipperLayout.OnSlidePageListener {

    private RecyclerViewPager mRecyclerView;
    private LayoutAdapter layoutAdapter;
    private SlidingUpPanelLayout mLayout;
    private TopicAdapter topicAdapter;
    private RecyclerView recyclerView;
    private List<AnwerInfo.DataBean.SubDataBean> datas;
    private FlipperLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anwer);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream in = getAssets().open("test_1.json");
                    List<QuestionEntry> questionEntries = JSON.parseArray(inputStream2String(in), QuestionEntry.class);
                    int size = questionEntries.size();
                    Log.i("AA",size+"");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("data.size=", e.toString());
                }
            }
        });

        Button bt_pre = (Button) findViewById(R.id.bt_pre);
        Button bt_next = (Button) findViewById(R.id.bt_next);

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootLayout.autoNextPage();
            }
        });

        bt_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootLayout.autoPrePage();
            }
        });


        AnwerInfo anwerInfo = getAnwer();

        datas = anwerInfo.getData().getData();
        Log.i("data.size=", "" + datas.size());


        initPage();


        initSlidingUoPanel();


        initList();



    }

    private void initPage() {
        if(datas.size()>0) {
            rootLayout = (FlipperLayout) findViewById(R.id.container);
            rootLayout.removeAllViews();
            rootLayout.setIndex(1);


            View recoverView = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);
            View view1 = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);
            View view2 = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);

            rootLayout.initFlipperViews(AnwerActivity.this, view2, view1, recoverView);

            final TextView readView1 = (TextView) view1.findViewById(R.id.tv_anwer);
            final TextView readView2 = (TextView) view2.findViewById(R.id.tv_anwer);

            if (datas.size() == 1) {
                //填充第一页的文本
                setText(readView1,datas.get(0));
            } else if (datas.size() >= 2) {
                //填充第一页的文本
                setText(readView1,datas.get(0));

                //填充第二页的文本
                setText(readView2,datas.get(1));
            }
        }

    }

    private void setText(TextView textView, AnwerInfo.DataBean.SubDataBean subDataBean){
        textView.setText(subDataBean.getQuestionid() + ". " + subDataBean.getQuestion()
                + "\n\nA." + subDataBean.getOptiona()
                + "\nB." + subDataBean.getOptionb()
                + "\nC." + subDataBean.getOptionc()
                + "\nD." + subDataBean.getOptiond()
                + "\n\n\n答案解析：" + subDataBean.getExplain()
        );
    }

    private void initLastPage() {

        rootLayout = (FlipperLayout) findViewById(R.id.container);
        rootLayout.removeAllViews();
        int position = datas.size() - 1;
        rootLayout.setIndex(position+1);

        View recoverView = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);
        View view1 = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);
        View view2 = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);

        rootLayout.initFlipperViews(AnwerActivity.this, view2, view1, recoverView);

        final TextView recoverReadView = (TextView) recoverView.findViewById(R.id.tv_anwer);
        final TextView readView1 = (TextView) view1.findViewById(R.id.tv_anwer);
        final TextView readView2 = (TextView) view2.findViewById(R.id.tv_anwer);

        AnwerInfo.DataBean.SubDataBean subDataBean = datas.get(position-1);

        //填充第一页的文本
        setText(recoverReadView,subDataBean);

        subDataBean = datas.get(position);
        //填充最后一页一页的文本
        setText(readView1,subDataBean);



    }


    @Override
    public View createView(int direction, int index) {
        Log.i("createView-index=",index+"");
        View newView = null;
        if (direction == FlipperLayout.OnSlidePageListener.MOVE_TO_LEFT && index<datas.size()) { //下一页
            AnwerInfo.DataBean.SubDataBean subDataBean = datas.get(index);

            newView = LayoutInflater.from(this).inflate(R.layout.anwer_item, null);
            TextView readView = (TextView) newView.findViewById(R.id.tv_anwer);
            setText(readView,subDataBean);



        } else if(direction == FlipperLayout.MOVE_TO_RIGHT && index>=2){//上一页
            AnwerInfo.DataBean.SubDataBean subDataBean = datas.get(index-2);
            newView = LayoutInflater.from(this).inflate(R.layout.anwer_item, null);
            TextView readView = (TextView) newView.findViewById(R.id.tv_anwer);
            setText(readView,subDataBean);

        }

        return newView;
    }



    @Override
    public void currentPosition(int index) {
        Log.i("@@@",index+"");
        curPosition = index-1;
        topicAdapter.notifyCurPosition(curPosition);
        topicAdapter.notifyPrePosition(prePosition);
        recyclerView.smoothScrollToPosition(curPosition);

        prePosition = curPosition;
    }

    private int index = 1;
    @Override
    public boolean currentIsLastPage() {
        index = rootLayout.getIndex();
        if(datas.size()==index){
            return false;
        }
        return true;
    }

    @Override
    public boolean whetherHasNextPage() {
        index = rootLayout.getIndex();
//        Log.i("whetherHasNextPage size= ",datas.size()+"=="+index);

        if(datas.size()==index){
            return false;
        }
       return true;
    }




    private void choosePage(int recover,int one,int two) {

        rootLayout = (FlipperLayout) findViewById(R.id.container);
        rootLayout.removeAllViews();
        rootLayout.setIndex(two);

        View recoverView = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);
        View view1 = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);
        View view2 = LayoutInflater.from(AnwerActivity.this).inflate(R.layout.anwer_item, null);

        rootLayout.initFlipperViews(AnwerActivity.this, view2, view1, recoverView);

        final TextView recoverReadView = (TextView) recoverView.findViewById(R.id.tv_anwer);
        final TextView readView1 = (TextView) view1.findViewById(R.id.tv_anwer);
        final TextView readView2 = (TextView) view2.findViewById(R.id.tv_anwer);


        AnwerInfo.DataBean.SubDataBean subDataBean = datas.get(recover);
        //左边一个
        setText(recoverReadView,subDataBean);

        subDataBean = datas.get(one);
        //填充第一页的文本
        setText(readView1,subDataBean);

        subDataBean = datas.get(two);
        //填充第二页的文本
        setText(readView2,subDataBean);

    }


    private int prePosition;
    private int curPosition;

    private void initList() {
        recyclerView = (RecyclerView) findViewById(R.id.list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);

        topicAdapter = new TopicAdapter(this);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(topicAdapter);

        if (topicAdapter != null) {
            topicAdapter.setDataNum(datas.size());
        }


        topicAdapter.setOnTopicClickListener(new TopicAdapter.OnTopicClickListener() {
            @Override
            public void onClick(TopicAdapter.TopicViewHolder holder, int position) {
                curPosition = position;
                Log.i("点击了==>", position + "");
                if(position==0){
                    initPage();
                }else if(position==datas.size()-1){
                    initLastPage();
                }else{
                    choosePage(position - 1, position, position + 1);
                }


                if (mLayout != null &&
                        (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }

                topicAdapter.notifyCurPosition(curPosition);
                topicAdapter.notifyPrePosition(prePosition);

                prePosition = curPosition;
            }
        });


    }



    private void initSlidingUoPanel() {
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i("", "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i("", "onPanelStateChanged " + newState);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    public String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    private AnwerInfo getAnwer() {

        try {
            InputStream in = getAssets().open("anwer.json");
            AnwerInfo anwerInfo = JSON.parseObject(inputStream2String(in), AnwerInfo.class);

            return anwerInfo;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("data.size=", e.toString());
        }

        return null;
    }
}
