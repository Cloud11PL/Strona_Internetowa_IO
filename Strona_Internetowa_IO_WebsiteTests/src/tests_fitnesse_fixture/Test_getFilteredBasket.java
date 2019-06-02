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

/**
 *
 * @author Wojciech
 */
public class Test_getFilteredBasket extends ColumnFixture{
        
    String clientdata[], filter[];
    ArrayList<Product_dto> result, data;
    int number;
    
    
    public boolean getFilteredBasket_ () {
        result = null;
        result = SetUp.facade.getFilteredBasket(clientdata, filter);
        data = new ArrayList();
        data.add(SetUp.data.productDtoData[number]);
        //Niespecjalnie wiem jak to zrobić.
        return data.equals(result); 
    }
}
