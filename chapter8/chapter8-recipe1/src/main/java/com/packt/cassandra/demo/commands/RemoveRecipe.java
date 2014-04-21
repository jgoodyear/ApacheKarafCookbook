package com.packt.cassandra.demo.commands;

import com.packt.cassandra.demo.api.RecipeBookService;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 * Displays the last log entries
 */
@Command(scope = "test", name = "removerecipe", description = "remove recipe from service")
public class RemoveRecipe extends OsgiCommandSupport { 

    @Argument(index=0, name="title", required=true, description="Title", multiValued=false) 
    String title;

    private RecipeBookService rbs;

    public void setRecipeBookService(RecipeBookService rbs) {
        this.rbs = rbs;
    }
 
    protected Object doExecute() throws Exception {
         System.out.println("Executing command removerecipe");
         rbs.deleteRecipe(title);
         System.out.println("Recipe removed!");
         return null;
    }
}
