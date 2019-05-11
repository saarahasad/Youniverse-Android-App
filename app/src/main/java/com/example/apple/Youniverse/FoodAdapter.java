package com.example.apple.Youniverse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private List<Food> foodList;
    Context context;
    ImageView imageViewIcon;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rating, address, locality,cost, url,cuisines;
        public  ImageView imimageViewIcon;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            rating = (TextView) view.findViewById(R.id.rating);
            address = (TextView) view.findViewById(R.id.address);
            locality = (TextView) view.findViewById(R.id.locality);
            cost = (TextView) view.findViewById(R.id.cost);
            url = (TextView) view.findViewById(R.id.url);
            cuisines = (TextView) view.findViewById(R.id.cuisines);

        }
    }


    public FoodAdapter(List<Food> foodList, Context context) {
        this.context=context;
        this.foodList = foodList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Food food = foodList.get(position);
       // Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+movie.getImage()).into(imageViewIcon);
        holder.name.setText(food.getResname());
        holder.cost.setText( "Price for 2: "+ food.getCost());
        holder.url.setText("Url: " + food.getUrl());
        holder.rating.setText("Rating: " + food.getRating());
        holder.address.setText(food.getAddress());
        holder.locality.setText( food.getLocality());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}