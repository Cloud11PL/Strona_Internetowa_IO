/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import bussineslogic.model.Client;
import bussineslogic.model.Product;
import categories.Test_Entity;
import java.util.Arrays;
import java.util.Collection;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.Parameterized;
import testdata.Data;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@Category(Test_Entity.class)
@RunWith(Parameterized.class)
public class ProductTest {
    
    static Data data;
    
    @BeforeClass
    public static void setUpClass(){
        data = new Data();
    }
    
    @Parameterized.Parameter
    public int number1;
    
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data1 = new Object[][]{{0},{1},{2},{3}};
        return Arrays.asList(data1);
    }
    
    @Test
    public void testEquals(){
        System.out.println("equals");
        int j = 0;
        for(Product product : data.productData){
            if(number1 == j)
                assertTrue(product.equals(data.productData[number1]));
            else
                assertFalse(product.equals(data.productData[number1]));
            j++;
        }
    }
    
    @Test
    public void isValid(){
        System.out.println("isValid");
        int j = 0;
        for(Product product : data.productData){
            Product prodAfterFilter = product.isValid(data.filters[number1]);
            if (number1 == j) {
                assertTrue(product.equals(prodAfterFilter));
            }else{
                assertNull(prodAfterFilter);
            }
            j++;
        }
    }
}
