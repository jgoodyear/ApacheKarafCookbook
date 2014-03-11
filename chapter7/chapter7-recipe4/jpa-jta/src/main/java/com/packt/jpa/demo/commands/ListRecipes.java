package com.packt.jpa.demo.commands;

import java.util.Collection;
import java.util.Iterator;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import com.packt.jpa.demo.api.RecipeBookService;

/**
 * Displays the last log entries
 */
@Command(scope = "test", name = "listrecipes", description = "list recipes in service")
public class ListRecipes extends OsgiCommandSupport { 

    private RecipeBookService rbs;

    public void setRecipeBookService(RecipeBookService rbs) {
        this.rbs = rbs;
    }
 
    protected Object doExecute() throws Exception {
         System.out.println("Executing command list recipes");
         Collection recipes = rbs.getRecipes();
         String result = "";
         Iterator it = recipes.iterator(); 
         while (it.hasNext()) {
            result += " " + it.next().toString() + "\n" ;
         }
         System.out.println(result);
         return null;
    }
}
