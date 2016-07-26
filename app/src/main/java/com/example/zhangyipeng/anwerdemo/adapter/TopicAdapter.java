package com.example.zhangyipeng.anwerdemo.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhangyipeng.anwerdemo.R;

/**
 * Created by zhangyipeng on 16/6/30.
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private Context mContext;
    private final LayoutInflater inflater;
    private final Resources resources;


    public TopicAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        resources = mContext.getResources();

    }

    @Override
    public TopicAdapter.TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TopicAdapter.TopicViewHolder holder, final int position) {

        holder.tv_id.setText((position + 1) + "");
        holder.tv_id.setTextColor(Color.parseColor("#b3afaf"));
        holder.tv_id.setBackgroundResource(R.drawable.bg_topic_no);
        if (prePosition == position) {
            holder.tv_id.setBackgroundResource(R.drawable.bg_topic_no);
            holder.tv_id.setTextColor(Color.parseColor("#b3afaf"));

        }
        if (curPosition == position) {
            holder.tv_id.setBackgroundResource(R.drawable.bg_topic_ok);
            holder.tv_id.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder, position);
            }
        });
    }

    private OnTopicClickListener listener;

    public void setOnTopicClickListener(OnTopicClickListener listener) {
        this.listener = listener;
    }

    private int curPosition;

    public void notifyCurPosition(int curPosition) {
        this.curPosition = curPosition;
        notifyItemChanged(curPosition);
    }

    private int prePosition;

    public void notifyPrePosition(int prePosition) {
        this.prePosition = prePosition;
        notifyItemChanged(prePosition);
    }


    public interface OnTopicClickListener {
        void onClick(TopicAdapter.TopicViewHolder holder, int position);
    }

    private int num;

    public void setDataNum(int num) {
        this.num = num;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return num;
    }


    public static class TopicViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_id;

        public TopicViewHolder(View itemView) {
            super(itemView);
            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
        }
    }
}
