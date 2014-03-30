package com.packt.jpa.demo.dao;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.PersistenceUnit; 
import javax.persistence.EntityManagerFactory; 
import javax.persistence.EntityTransaction; 
import javax.persistence.Persistence; 

import java.lang.IllegalArgumentException;

import com.packt.jpa.demo.entity.Recipe;
import com.packt.jpa.demo.api.RecipeBookService;


public class RecipeBookServiceDAOImpl implements RecipeBookService {

    @PersistenceUnit(unitName="recipe") 
    private EntityManagerFactory factory;

    public void setEntityManagerFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> result = new ArrayList<Recipe>();
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        result = entityManager.createQuery("select r from RECIPE r", Recipe.class).getResultList();
        entityTransaction.commit();
        return result;
    }

    @Override
    public void addRecipe(String title, String ingredients) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(new Recipe(title, ingredients));
        entityTransaction.commit();
    }

    @Override
    public void deleteRecipe(String title) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entityManager.getReference(Recipe.class, title));
        entityTransaction.commit();
    }
}
