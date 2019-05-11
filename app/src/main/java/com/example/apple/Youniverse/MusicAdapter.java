package com.example.apple.Youniverse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    private List<Music> musicList;
    Context context;
    ImageView imageViewIcon;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rank, url, artist;
        public  ImageView imimageViewIcon;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            rank = (TextView) view.findViewById(R.id.rank);
            url = (TextView) view.findViewById(R.id.url);
            artist = (TextView) view.findViewById(R.id.artist);
            imageViewIcon= (ImageView) itemView.findViewById(R.id.imageMusic);

        }
    }


    public MusicAdapter(List<Music> musicList, Context context) {
        this.context=context;
        this.musicList = musicList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Music music = musicList.get(position);
        Picasso.with(context).load(music.getImage()).into(imageViewIcon);
        holder.name.setText(music.getSongname());
        holder.url.setText("Url:  "+ music.getUrl());
        holder.rank.setText("Rank: " + music.getRank());
        holder.artist.setText("Artist:  "+ music.getArtistname());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
}