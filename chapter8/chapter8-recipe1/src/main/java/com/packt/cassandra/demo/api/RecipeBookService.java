package com.packt.cassandra.demo.api;

import java.util.Collection;

import com.packt.cassandra.demo.entity.Recipe;

public interface RecipeBookService {

    public Collection<Recipe> getRecipes();

    public void addRecipe(String title, String ingredients);

    public void deleteRecipe(String title);
}
