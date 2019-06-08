package tests;

import bussineslogic.model.Client;
import bussineslogic.model.Product;
import categories.Test_Entity;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import testdata.Data;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Category(Test_Entity.class)
@RunWith(Parameterized.class)
public class ShoppingBasketTest {
    
    static Data data;
    static Client client ;
    static Map productMap;
    static double sum;
    
    @BeforeClass
    public static void setUpClass(){
        data = new Data();
        client = data.clientData[0];
        productMap = client.getShoppingBasket().getProductMap();
        sum = 0;
    }
    
    @Parameterized.Parameter
    public int number1;
    
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data1 = new Object[][]{{0},{1},{2},{3}};
        return Arrays.asList(data1);
    }
    
    @Test
    public void testAddProductToShoppingBasket() {
        System.out.println("addProductToShoppingBasket");
        Product product = data.productData[number1];
        sum += product.getPrice();
        productMap = client.getShoppingBasket().getProductMap();
        client.addToShoppingBasket(product);  
        productMap = client.getShoppingBasket().getProductMap();
        assertEquals(productMap, data.baskets[number1]);
        assertTrue(productMap.containsKey(product));
        assertNotNull(productMap.get(product));
        assertNotNull(client.getShoppingBasket().getTotalPrice());
        assertEquals(productMap.size(), number1+1);
        assertEquals(sum, client.getShoppingBasket().getTotalPrice(), 0.0001 );
    }
    
    @Test
    public void testBrowseBasket() {
        System.out.println("browseBasket");
        client.browseShoppingBasket(data.filters[number1]);
        assertEquals(client.getShoppingBasket().getFilteredMap(), data.filtredBaskets[number1]);     
    }
    
    @Test
    public void testRemoveProductFromShoppingBasket() {
        System.out.println("removeProductFromShoppingBasket");
        Product product = data.productData[number1];
        client.removeFromShoppingBasket(product);
        productMap = client.getShoppingBasket().getProductMap();
        assertEquals(productMap, data.reovedBaskets[number1]);
        assertFalse(productMap.containsKey(product));
        assertNull(productMap.get(product));
        assertEquals(productMap.size(), number1);
        client.addToShoppingBasket(product);
        productMap = client.getShoppingBasket().getProductMap();
    }
    
 }

