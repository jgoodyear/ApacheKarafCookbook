package com.packt.jpa.demo.api;

import java.util.Collection;
import com.packt.jpa.demo.entity.Recipe;

public interface RecipeBookService {

   public Collection<Recipe> getRecipes();

   public void addRecipe(String title, String ingredients);

   public void deleteRecipe(String title);

}
