/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests_fitnesse_fixture;

import fit.ColumnFixture;
import java.util.IllegalFormatCodePointException;

/**
 *
 * @author Wojciech
 */
public class Test_removeFromBasket extends ColumnFixture{
    String clientdata[], productdata[], result, data;
    int number;
    
        public boolean removeFromBasket_() {
        try {
            result = SetUp.facade.removeFromBasket(clientdata, productdata);
            data = "Usunięto: " + SetUp.data.productDtoData[number].getName() + " z koszyka";
        } catch (IllegalFormatCodePointException e) {
            data = "Brak takiego produktu w koszyku";
            return false;
        }
        return data.equals(result);
    }
    
}
