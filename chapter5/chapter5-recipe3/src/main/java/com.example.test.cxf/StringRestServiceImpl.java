package com.example.test.cxf;

/**
 * Implementation of the Features REST service.
 */
public class StringRestServiceImpl implements StringRestService {
    @Override
    public String getRecipeThree() throws Exception {
        System.out.println("RECIPE 3 :: restful endpoint hit.");
        return "<packt><chapter5><recipe3>THIS IS A COOK BOOK RECIPE THREE.... BABY!!!</recipe3></chapter5></packt>";
    }
}
