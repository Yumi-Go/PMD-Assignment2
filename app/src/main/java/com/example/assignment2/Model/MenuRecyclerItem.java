package com.example.assignment2.Model;

public class MenuRecyclerItem {

    String category, name, description;
    int price, image;

    public MenuRecyclerItem(String category, String name, int price, String description, int image) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
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

    public int getPrice() { return price; }

    public void setPrice(int price) {
        this.price = price;
    }

    public String priceToString(int price) {
        return getPrice() + " €";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}



