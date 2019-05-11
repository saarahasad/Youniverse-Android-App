package com.example.apple.Youniverse;

public class Twitter {
    private String name,  url;

    public Twitter() {
    }

    public Twitter(String name, String url) {
        this.name = name;
        this.url = url;

    }

    public String getPersonname() {
        return name;
    }

    public void setPersonname(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
