package com.packt.jpa.demo.entity;;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity( name = "RECIPE" )
@Table( name = "RECIPES" )
public class Recipe {

    @Id
    @Column(nullable = false)
    private String title;

    @Column(length=10000)
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

