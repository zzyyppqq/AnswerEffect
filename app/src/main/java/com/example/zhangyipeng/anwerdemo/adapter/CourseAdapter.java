package com.example.zhangyipeng.anwerdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangyipeng.anwerdemo.R;
import com.example.zhangyipeng.anwerdemo.bean.CourseSheetInfo;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangyipeng on 16/7/4.
 */
public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private final float density;
    private final LayoutInflater inflater;

    public CourseAdapter(Context context) {
        setHasStableIds(true);
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        density = mContext.getResources().getDisplayMetrics().density;

    }

    public static final int NOMAL = 100;
    public static final int HEAD = 101;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NOMAL) {
            View view = inflater.inflate(R.layout.course_item, parent, false);
            return new CourseViewHolder(view);
        } else if(viewType == HEAD){
            View view =inflater.inflate(R.layout.head_item, parent, false);
            return new HeaderViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CourseViewHolder) {
            Log.i("BBB", "==>" + position);
            CourseViewHolder courseViewHolder = (CourseViewHolder) holder;

            CourseSheetInfo.DataBean.ScheduleBean.DetailBean detailBean = items.get(position);
            String date = detailBean.getDate();
            String date_day = detailBean.getDate_day();
            String date_week = detailBean.getDate_week();
            courseViewHolder.tv_date.setText(date_day);
            courseViewHolder.tv_week.setText(date_week);
            List<CourseSheetInfo.DataBean.ScheduleBean.DetailBean.SubDetailBean> dataList = detailBean.getData();

            courseViewHolder.ll_all_course.removeAllViews();
            for (int i = 0; i < dataList.size(); i++) {
                CourseSheetInfo.DataBean.ScheduleBean.DetailBean.SubDetailBean subDetailBean = dataList.get(i);
                View view = LayoutInflater.from(mContext).inflate(R.layout.course_text, null, false);
                TextView textView = (TextView) view.findViewById(R.id.tv_text);
                textView.setText(subDetailBean.getText() + "\n" + subDetailBean.getText2());

                courseViewHolder.ll_all_course.addView(view);

            }

        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;


            long id = items.get(position).getId();
            Log.i("BBB", id + "==>" + position);
            headerViewHolder.tv_date.setText(id + "");


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (density * 60), (int) (3000 * density));
            headerViewHolder.itemView.setLayoutParams(params);


            List<String> scheduleList = datas.get((int) id).getSchedule();

            headerViewHolder.ll_all_times.removeAllViews();
            for (int i = 0; i < scheduleList.size(); i++) {
                String time = scheduleList.get(i);
                String[] split = time.split("-");
                View view = LayoutInflater.from(mContext).inflate(R.layout.course_text, null, false);
                TextView textView = (TextView) view.findViewById(R.id.tv_text);
                textView.setText(split[0] + "\n" + split[1]);
                headerViewHolder.ll_all_times.addView(view);
            }

            headerViewHolder.itemView.setBackgroundColor(getRandomColor());


        }
    }


    private int getRandomColor() {
        SecureRandom rgen = new SecureRandom();
        return Color.HSVToColor(360, new float[]{
                rgen.nextInt(359), 1, 1
        });
    }


    private List<CourseSheetInfo.DataBean.ScheduleBean> datas;
    private List<CourseSheetInfo.DataBean.ScheduleBean.DetailBean> items = new ArrayList<>();

    public void setData(List<CourseSheetInfo.DataBean.ScheduleBean> datas) {
        this.datas = datas;

        int size = datas.size();

        for (int i = 0; i < size; i++) {
            List<CourseSheetInfo.DataBean.ScheduleBean.DetailBean> dataList = datas.get(i).getData();

            items.addAll(dataList);
        }

        notifyDataSetChanged();
        Log.i("items.size()=", items.size() + "");
    }

    @Override
    public int getItemViewType(int position) {



        return NOMAL;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_date;
        private final TextView tv_week;
        private final LinearLayout ll_all_course;

        public CourseViewHolder(View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_week = (TextView) itemView.findViewById(R.id.tv_week);
            ll_all_course = (LinearLayout) itemView.findViewById(R.id.ll_all_course);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_date;
        private final TextView tv_time;
        private final LinearLayout ll_all_times;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            ll_all_times = (LinearLayout) itemView.findViewById(R.id.ll_all_times);
        }
    }

}