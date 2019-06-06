/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests_fitnesse_fixture;

import bussineslogic.dto.Product_dto;
import bussineslogic.model.Product;
import fit.ColumnFixture;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Wojciech
 */
public class Test_getFilteredBasket extends ColumnFixture{
        
    String clientdata[], filter[], result, data;
    ArrayList<Product_dto> filteredBasket, testingData;
    int number;
    
    
    public boolean getFilteredBasket_ () {
        filteredBasket = new ArrayList();
        filteredBasket = SetUp.facade.getFilteredBasket(clientdata, filter);
        testingData = new ArrayList();
        data = SetUp.data.productDtoData[2].toString();
        testingData.add(SetUp.data.productDtoData[2]);
        result = filteredBasket.get(number).toString();
        
        return testingData.get(number).equals(filteredBasket.get(number));

    }
}
