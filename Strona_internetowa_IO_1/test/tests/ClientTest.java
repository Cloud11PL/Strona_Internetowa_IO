/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import bussineslogic.model.Client;
import categories.Test_Entity;
import java.util.Arrays;
import java.util.Collection;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.Parameterized;
import testdata.Data;
import static org.junit.Assert.*;

@Category(Test_Entity.class)
public class ClientTest {
    
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
        for(Client client : data.clientData){
            if(number1 == j)
                assertTrue(client.equals(data.clientData[number1]));
            else
                assertFalse(client.equals(data.clientData[number1]));
            j++;
        }
    }
    
}
