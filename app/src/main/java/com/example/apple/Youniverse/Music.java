package com.example.apple.Youniverse;

public class Music {
    private String name,  url,  rank,  artistname,  image;

    public Music() {
    }

    public Music(String name, String url, String rank, String artistname, String image) {
        this.name = name;
        this.url = url;
        this.rank = rank;
        this.artistname = artistname;
        this.image = image;
    }

    public String getSongname() {
        return name;
    }

    public void setSongname(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String genre) {
        this.rank = rank;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
