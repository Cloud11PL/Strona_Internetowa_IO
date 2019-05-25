package testsuite;

import categories.Test_Entity;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import tests.ClientTest;
import tests.FactoryTest;
import tests.ProductTest;
import tests.ShoppingBasketTest;

@Categories.SuiteClasses({FactoryTest.class, ProductTest.class, ClientTest.class, ShoppingBasketTest.class})
@RunWith(Categories.class)
@Categories.IncludeCategory(Test_Entity.class)
public class SuiteTestEntity {
    
}
