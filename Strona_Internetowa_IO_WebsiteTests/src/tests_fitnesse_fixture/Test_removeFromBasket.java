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
import java.util.IllegalFormatCodePointException;

/**
 *
 * @author Wojciech
 */
public class Test_removeFromBasket extends ColumnFixture {

    String clientdata[], productdata[], result, break1, break2;
    ArrayList<Product_dto> initBasket, finalBasket;
    int number;
    boolean output;

    public boolean removeFromBasket_() {

        initBasket = SetUp.facade.getBasket(clientdata);
        SetUp.facade.removeFromBasket(clientdata, productdata);
        finalBasket = SetUp.facade.getBasket(clientdata);
        output = true;

        for (int i = 0; i < initBasket.size(); i++) {
            if (!initBasket.get(i).equals(finalBasket.get(i))) {
                result = "Udało się usunąć!";
                return output;
            }
        }

        output = false;
        result = "Nie udało się usunąć!";
        return output;
    }

}
