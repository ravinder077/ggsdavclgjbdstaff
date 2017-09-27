package com.example.ravinder077.ggsdav;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chugh on 8/3/2017.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {


    private List<NoticeData> postlist;
    private Context mcontext;

    public NoticeAdapter(List<NoticeData> postlist) {
        this.postlist = postlist;
    }


    public NoticeAdapter(List<NoticeData> postlist, Context context) {

        this.mcontext=context;
        this.postlist = postlist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lin, parent, false);

        return new NoticeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final NoticeData noticeData = postlist.get(position);


        holder.title1.setText(noticeData.getTitle());
        holder.desc.setText(noticeData.getDesc());
        holder.date.setText(noticeData.getDate());






    }



    @Override
    public int getItemCount() {
        return postlist.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView title1;
        public TextView desc;
        public TextView date;

        public MyViewHolder(View view) {
            super(view);

            title1=(TextView) view.findViewById(R.id.txt1);
            desc=(TextView) view.findViewById(R.id.txt2);
            date=(TextView) view.findViewById(R.id.txt3);

        }
    }

}

