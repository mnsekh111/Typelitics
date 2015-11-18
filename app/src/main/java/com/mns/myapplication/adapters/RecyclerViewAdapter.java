package com.mns.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mns.myapplication.R;
import com.mns.myapplication.pojo.LeaderBoardItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
    private List<LeaderBoardItem> leaderBoardItemList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<LeaderBoardItem> leaderBoardItemList) {
        this.leaderBoardItemList = leaderBoardItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_leaderboard, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        LeaderBoardItem leaderBoardItem = leaderBoardItemList.get(i);

        //Download image using picasso library
        Picasso.with(mContext).load(leaderBoardItem.getProfilePicUrl())
                .into(customViewHolder.imageView);

        //Setting text view title
        customViewHolder.textView.setText(leaderBoardItem.getName());
    }

    @Override
    public int getItemCount() {
        return (null != leaderBoardItemList ? leaderBoardItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }
}