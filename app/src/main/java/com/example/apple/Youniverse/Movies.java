package com.example.apple.Youniverse;

/**
 * Created by apple on 07/05/18.
 */

public class Movies {
    private String name,  overview,  rank,  release,  image;

    public Movies() {
    }

    public Movies(String name, String overview, String rank, String release, String image) {
        this.name = name;
        this.overview = overview;
        this.rank = rank;
        this.release = release;
        this.image = image;
    }

    public String getMoviename() {
        return name;
    }

    public void setMoviename(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
