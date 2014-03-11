package com.packt.jpa.demo.dao;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import java.lang.IllegalArgumentException;

import com.packt.jpa.demo.entity.Recipe;
import com.packt.jpa.demo.api.RecipeBookService;

public class RecipeBookServiceDAOImpl implements RecipeBookService {

    private EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }   

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> result = new ArrayList<Recipe>();
        result = em.createQuery("select r from RECIPE r", Recipe.class).getResultList();
        return result;
    }

    @Override
    public void addRecipe(String title, String ingredients) {
        em.persist(new Recipe(title, ingredients));
    }

    @Override
    public void deleteRecipe(String title) {
        em.remove(em.getReference(Recipe.class, title));
    }
}
