package com.example.apple.Youniverse;

/**
 * Created by apple on 07/05/18.
 */

public class Food {
    private String name,  url,  cuisines,  address,  locality, cost, rating;

    public Food() {
    }

    public Food(String name, String url, String cuisines, String address, String locality,String cost,String rating) {
        this.name = name;
        this.url = url;
        this.cuisines = cuisines;
        this.address = address;
        this.locality = locality;
        this.cost = cost;
        this.rating = rating;
    }

    public String getResname() {
        return name;
    }

    public void setResname(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String overview) {
        this.url = url;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
