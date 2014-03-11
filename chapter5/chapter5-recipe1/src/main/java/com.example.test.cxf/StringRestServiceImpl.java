package com.example.test.cxf;

/**
 * Implementation of the Features REST service.
 */
public class StringRestServiceImpl implements StringRestService {
    @Override
    public String getRecipeOne() throws Exception {
        System.out.println("RECIPE 1 :: restful endpoint hit.");
        return "<packt><chapter5><recipe1>THIS IS A COOK BOOK RECIPE ONE.... BABY!!!</recipe1></chapter5></packt>";
    }
}
