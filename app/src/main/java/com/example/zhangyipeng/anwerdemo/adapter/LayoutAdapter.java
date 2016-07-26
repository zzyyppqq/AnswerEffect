/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.zhangyipeng.anwerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangyipeng.anwerdemo.R;
import com.example.zhangyipeng.anwerdemo.bean.AnwerInfo;

import java.util.ArrayList;
import java.util.List;



public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private List<AnwerInfo.DataBean.SubDataBean> datas;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public LayoutAdapter(Context context, RecyclerView recyclerView) {

        mContext = context;
        mRecyclerView = recyclerView;
    }

    public void setDataList(List<AnwerInfo.DataBean.SubDataBean> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        AnwerInfo.DataBean.SubDataBean subDataBean = datas.get(position);
        holder.title.setText(subDataBean.getQuestionid()+". "+subDataBean.getQuestion()
        +"\n\nA."+subDataBean.getOptiona()
        +"\nB."+subDataBean.getOptionb()
        +"\nC."+subDataBean.getOptionc()
        +"\nD."+subDataBean.getOptiond()
        +"\n\n\n答案解析："+subDataBean.getExplain()
        );

        final View itemView = holder.itemView;

    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }
}
