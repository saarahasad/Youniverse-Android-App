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

public class TwitterAdapter extends RecyclerView.Adapter<TwitterAdapter.MyViewHolder> {

    private List<Twitter> friendsList;
    Context context;
    ImageView imageViewIcon;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rank, url, artist;
        public  ImageView imimageViewIcon;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue.ttf");
            name.setTypeface(typeface);

            imageViewIcon= (ImageView) itemView.findViewById(R.id.imageTwitter);

        }
    }


    public TwitterAdapter(List<Twitter> friendsList, Context context) {
        this.context=context;
        this.friendsList = friendsList;
    }

    @Override
    public TwitterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.twitter_list_row, parent, false);

        return new TwitterAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TwitterAdapter.MyViewHolder holder, int position) {
        Twitter f = friendsList.get(position);
        Picasso.with(context).load(f.getUrl()).into(imageViewIcon);
        holder.name.setText(f.getPersonname());

    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}