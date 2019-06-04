/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import bussineslogic.Factory;
import bussineslogic.model.Client;
import bussineslogic.model.Product;
import categories.Test_Control;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testdata.Data;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


@Category(Test_Control.class)
public class FactoryTest {
    
    static Data data;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @BeforeClass
    public static void setUpClass(){
        data = new Data();
    }
    
    @Test
    public void testCreateProduct(){
        System.out.println("createProduct");
        Factory factory = new Factory();
        
        for(int i = 0; i < 3; i++){
            Product product = factory.createProduct(data.productString[i]);
            assertEquals(product, data.productData[i]);
        }
        
        exception.expect(NumberFormatException.class);
        String illegalNumber = "\"45,23\"";
        exception.expectMessage("For input string: " + illegalNumber);
        factory.createProduct(data.productString[4]);
       
        exception.expect(NumberFormatException.class);
        factory.createProduct(data.productString[5]);
    }
    
    @Test
    public void testCreateClient(){
        System.out.println("createClient");
        Factory factory = new Factory();
        
        for(int i = 0; i < data.clientData.length; i++){
            Client client = factory.createClient(data.clientString[i]);
            assertEquals(client, data.clientData[i]);
        }
    }
    
}
