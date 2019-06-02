/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests_fitnesse_fixture;

import fit.ColumnFixture;

/**
 *
 * @author Wojciech
 */
public class Test_addProduct extends ColumnFixture{
    
    String dataproducts[], data, result;
    int number;
    
    public boolean addProduct_() {
        result = null;
        result = SetUp.facade.addProduct(dataproducts);
        data = SetUp.data.productsModel[number];
        return data.equals(result);
    }
    
}
