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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movies> movieList;
    Context context;
    ImageView imageViewIcon;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rank, overview, release;
        public  ImageView imimageViewIcon;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            rank = (TextView) view.findViewById(R.id.rank);
            overview = (TextView) view.findViewById(R.id.overview);
            release = (TextView) view.findViewById(R.id.release);
            imageViewIcon= (ImageView) itemView.findViewById(R.id.imageMovie);

        }
    }


    public MovieAdapter(List<Movies> movieList, Context context) {
        this.context=context;
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movies movie = movieList.get(position);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+movie.getImage()).into(imageViewIcon);
        holder.name.setText(movie.getMoviename());
        holder.overview.setText( movie.getOverview());
        holder.rank.setText("Rating: " + movie.getRank());
        holder.release.setText("Artist:  "+ movie.getRelease());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}