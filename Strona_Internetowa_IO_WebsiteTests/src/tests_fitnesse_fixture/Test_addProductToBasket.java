/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests_fitnesse_fixture;

import bussineslogic.Facade;
import fit.ColumnFixture;
import java.util.IllegalFormatCodePointException;

/**
 *
 * @author Wojciech
 */
public class Test_addProductToBasket extends ColumnFixture{
    String clientdata[], productdata[], result, data;
    int number;
    
    
    public boolean addProductToBasket_() {
        result = SetUp.facade.addProductToBasket(clientdata, productdata);
        data = "Dodano: " + SetUp.data.productDtoData[number].getName();
        return data.equals(result);
    }
}
