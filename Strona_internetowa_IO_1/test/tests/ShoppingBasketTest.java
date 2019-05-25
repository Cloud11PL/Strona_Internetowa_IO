package tests;

import bussineslogic.model.Client;
import bussineslogic.model.Product;
import categories.Test_Entity;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;
import testdata.Data;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Category(Test_Entity.class)
public class ShoppingBasketTest {
    
    static Data data;
    static Client client;
    static Map productMap;
    
    @BeforeClass
    public static void setUpClass(){
        data = new Data();
        client = data.clientData[0];
    }
    
    @Test
    public void testAddProductToShoppingBasket() {
        System.out.println("addProductToShoppingBasket");
        double sum = 0;
        for (Product product : data.productData) {
            sum += product.getPrice();
            client.addToShoppingBasket(product);    
            productMap = client.getShoppingBasket().getProductMap();
            assertTrue(productMap.containsKey(product));
            assertNotNull(productMap.get(product));
            assertNotNull(client.getShoppingBasket().getTotalPrice());
        }
        productMap = client.getShoppingBasket().getProductMap();
        assertEquals(productMap.size(), 4);
        assertEquals(sum, client.getShoppingBasket().getTotalPrice(), 0.0001 );
    }
    
    @Test
    public void testBrowseBasket() {
        System.out.println("browseBasket");
        for (int i = 0; i< data.filters.length; i++){
            client.browseShoppingBasket(data.filters[i]);
            assertNotEquals(client.getShoppingBasket().getFilteredMap(), productMap);
            Map<Product, Integer> checkMap = new HashMap();
            checkMap.put(data.productData[i], 1);
            assertThat(client.getShoppingBasket().getFilteredMap(), is(checkMap));
        }        
    }
    
    @Test
    public void testRemoveProductFromShoppingBasket() {
        System.out.println("removeProductFromShoppingBasket");
        for (Product product : data.productData) {
            client.removeFromShoppingBasket(product);
            productMap = client.getShoppingBasket().getProductMap();
            assertFalse(productMap.containsKey(product));
            assertNull(productMap.get(product));
        }
        productMap = client.getShoppingBasket().getProductMap();
        assertEquals(productMap.size(), 0);
    }
    
 }

