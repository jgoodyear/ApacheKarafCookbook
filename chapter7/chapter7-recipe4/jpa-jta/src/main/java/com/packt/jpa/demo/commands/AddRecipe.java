package com.packt.jpa.demo.commands;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import com.packt.jpa.demo.api.RecipeBookService;

/**
 * Displays the last log entries
 */
@Command(scope = "test", name = "addrecipe", description = "add recipe to service")
public class AddRecipe extends OsgiCommandSupport { 

    @Argument(index=0, name="title", required=true, description="Title", multiValued=false) 
    String title;

    @Argument(index=1, name="ingredients", required=true, description="Ingredients for Recipe", multiValued=false) 
    String ingredients;

    private RecipeBookService rbs;

    public void setBookService(RecipeBookService rbs) {
        this.rbs = rbs;
    }
 
    protected Object doExecute() throws Exception {
         System.out.println("Executing command addrecipe");
         rbs.addRecipe(title, ingredients);
         System.out.println("Recipe added!");
         return null;
    }
}
