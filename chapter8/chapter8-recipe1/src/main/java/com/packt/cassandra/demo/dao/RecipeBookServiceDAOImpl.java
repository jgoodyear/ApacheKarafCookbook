package com.packt.cassandra.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.packt.cassandra.demo.api.RecipeBookService;
import com.packt.cassandra.demo.entity.Recipe;

public class RecipeBookServiceDAOImpl implements RecipeBookService {

    private Cluster cluster;
    private Session session;

    public void connect(String node) {
        cluster = Cluster.builder().addContactPoint(node).build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
        }
        session = cluster.connect("karaf_demo");
    }

    public void destroy() {
        cluster.close();
    }

    public void init() {
        connect("127.0.0.1");
    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> result = new ArrayList<Recipe>();
        ResultSet results = session.execute("SELECT * FROM karaf_demo.recipes;");

        for (Row row : results) {
            Recipe recipe = new Recipe();
            recipe.setTitle(row.getString("title"));
            recipe.setIngredients(row.getString("ingredients"));
            result.add(recipe);
        }
        return result;
    }

    @Override
    public void addRecipe(String title, String ingredients) {

        ResultSet resultSet = session.execute("INSERT INTO karaf_demo.recipes (title, ingredients) VALUES ('" + title + "', '" + ingredients + "');");
        System.out.println("Result = " + resultSet);
    }

    @Override
    public void deleteRecipe(String title) {
        ResultSet resultSet = session.execute("DELETE from karaf_demo.recipes where title='" + title + "';");
        System.out.println("Result = " + resultSet);
    }
}
