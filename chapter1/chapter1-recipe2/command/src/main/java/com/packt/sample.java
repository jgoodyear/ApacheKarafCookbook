
package com.packt;

import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.commands.Option;
import org.apache.karaf.shell.commands.Argument;

@Command(scope = "cookbook", name = "sample", description = "a sample custom command")
public class sample extends OsgiCommandSupport {

    @Option(name = "-o", aliases = { "--option" }, description = "An option to the command", required = false, multiValued = false)
    private String option;

    @Argument(name = "argument", description = "Argument to the command", required = false, multiValued = false)
    private String argument;

    protected Object doExecute() throws Exception {
         System.out.println("Executing command sample");
         System.out.println("Option: " + option);
         System.out.println("Argument: " + argument);
         return null;
    }
}
