package com.example.apple.Youniverse;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TrendAdapter extends RecyclerView.Adapter<TrendAdapter.MyViewHolder> {

    private List<Twitter> friendsList;
    Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rank, url, artist;
        public  ImageView imimageViewIcon;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue.ttf");
            name.setTypeface(typeface);
            url = (TextView) view.findViewById(R.id.url);
            url.setTypeface(typeface);




        }
    }


    public TrendAdapter(List<Twitter> friendsList, Context context) {
        this.context=context;
        this.friendsList = friendsList;
    }

    @Override
    public TrendAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trend_list_row, parent, false);

        return new TrendAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrendAdapter.MyViewHolder holder, int position) {
        Twitter f = friendsList.get(position);

        holder.name.setText(f.getPersonname());
        holder.url.setText(f.getUrl());

    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}