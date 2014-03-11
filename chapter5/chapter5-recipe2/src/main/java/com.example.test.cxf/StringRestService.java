package com.example.test.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * REST service to manipulate Karaf features
 */
@Path("/")
public interface StringRestService {
    /**
     * Returns an explicit collection of all features in XML format in response to HTTP GET requests.
     * @return the collection of features
     * http://localhost:8181/cxf/chapter5/recipeTwo
     */
    @GET
    @Path("/recipeTwo")
    @Produces("application/xml")
    public String getRecipeTwo() throws Exception;
}