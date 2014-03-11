package com.example.test.cxf;

/**
 * Implementation of the Features REST service.
 */
public class StringRestServiceImpl implements StringRestService {
    @Override
    public String getRecipeTwo() throws Exception {
        System.out.println("RECIPE 2 :: restful endpoint hit.");
        return "<packt><chapter5><recipe2>THIS IS A COOK BOOK RECIPE TWO.... BABY!!!</recipe2></chapter5></packt>";
    }
}
