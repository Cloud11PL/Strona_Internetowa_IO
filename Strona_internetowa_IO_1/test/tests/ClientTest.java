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
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Category(Test_Entity.class)
@RunWith(Parameterized.class)
public class ClientTest {

    @Parameterized.Parameter
    public int number1;
    public Client[] clientList;

    static Data data;

    @BeforeClass
    public static void setUpClass() {
        data = new Data();
    }

    @Before
    public void setUp() {
        clientList = data.clientData;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data1 = new Object[][]{{0}, {1}, {2}, {3}};
        return Arrays.asList(data1);
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        int j = 0;

        for (Client client : clientList) {
            if (number1 == j) {
                assertTrue(client.equals(data.clientData[number1]));
            } else {
                assertFalse(client.equals(data.clientData[number1]));
            }
            j++;
        }
    }

    @Test
    public void testAddToShoppingBasket() {
        System.out.println("addToShoppingBasket");
        int j = 0;
        for (Client client : clientList) {
            client.addToShoppingBasket(data.productData[j]);
            if (number1 == j) {
                assertTrue(client.getShoppingBasket().getProductMap().containsKey(data.productData[number1]));
            } else {
                assertFalse(client.getShoppingBasket().getProductMap().containsKey(data.productData[number1]));
            }
            j++;

        }
    }

    @Test
    public void testRemoveFromShoppingBasket() {
        System.out.println("removeFromShoppingBasket");
        int j = 0;
        for (Client client : clientList) {
            String message = client.removeFromShoppingBasket(data.productData[j]);
            assertFalse(client.getShoppingBasket().getProductMap().containsKey(data.productData[number1]));
            assertTrue(message.equals("Usunięto: " + data.productData[j].getName() + " z koszyka"));
            j++;
        }
    }
    
    @Test
    public void testBrowseShoppingBasket(){
        System.out.println("browseShoppingBasket");
        int j = 0;
        for (Client client : clientList) {
            String message = client.browseShoppingBasket(data.filters[j]);
            if(number1 == j){
                assertTrue(message.equals("ShoppingBasket{"
                + "productMap=" + data.clientData[number1].getShoppingBasket().getFilteredMap() + "}"));
            }else {
                assertFalse(message.equals("ShoppingBasket{"
                + "productMap=" + data.clientData[number1].getShoppingBasket().getFilteredMap() + "}"));
            }
            j++;
        }
    }

}
