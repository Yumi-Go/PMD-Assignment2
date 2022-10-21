package com.example.assignment1;

public class Food_Tab1RecyclerItem {

    String category, name, district;
    int image;

    public Food_Tab1RecyclerItem(String category, String name, String district, int image) {
        this.category = category;
        this.name = name;
        this.district = district;
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}



