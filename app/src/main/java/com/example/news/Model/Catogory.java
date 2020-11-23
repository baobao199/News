package com.example.news.Model;

public class Catogory {
    private String image;
    private String name;

    public Catogory() {
    }

    public Catogory(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
