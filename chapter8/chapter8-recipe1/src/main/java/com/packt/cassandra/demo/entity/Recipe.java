package com.packt.cassandra.demo.entity;

public class Recipe {
    private String title;
    private String ingredients;
    public Recipe() {
    }
    public Recipe(String title, String ingredients) {
        super();
        this.title = title;
        this.ingredients = ingredients;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public String toString() {
        return "" + this.title + " " + this.ingredients;
    }
}

