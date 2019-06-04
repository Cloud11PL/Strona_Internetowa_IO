/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationtier;

import bussineslogic.dto.Category_dto;
import bussineslogic.dto.Gender_dto;
import bussineslogic.dto.Product_dto;
import bussineslogic.model.Category;
import bussineslogic.model.Gender;
import bussineslogic.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juju
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "Strona_Internetowa_EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    public void addProduct(List<Product> products) {
        for (Product product : products) {
            if (product.getId() == null) {
                getEntityManager().persist(product);
            }
        }
    }

    public void changePrice(Product product) {
        getEntityManager().merge(product);
    }

    public void removeProduct(Product_dto product_dto) {
        
        Category cat = Category.values()[product_dto.getCategory().ordinal()];
        Gender gen = Gender.values()[product_dto.getGender().ordinal()];
        Product product = new Product(product_dto.getName(), product_dto.getPrice(), cat, gen, product_dto.getSize(), product_dto.getBrand());
        
        
        Query query = getEntityManager().createQuery("SELECT a FROM Product a");
        List<Product> productList = query.getResultList();
        
        for(Product pr : productList){
            if(pr.equals(product)){
                getEntityManager().remove(pr);
            }
        }
    }
    
    public List<Product_dto> getAllProducts_dto(){
        Query query = getEntityManager().createQuery("SELECT a FROM Product a");
        List<Product> productList = query.getResultList();
        List<Product_dto> product_dtos = new ArrayList<>();
        
        for(Product p: productList){
            Category_dto cat = Category_dto.values()[p.getCategory().ordinal()];
            Gender_dto gen = Gender_dto.values()[p.getGender().ordinal()];
            Product_dto product_dto = new Product_dto(p.getName(), p.getPrice(), cat, gen, p.getSize(), p.getBrand());
            
            product_dtos.add(product_dto);
        }
        
        return product_dtos;
    }
    
    public List<Product> getAllProducts(){
        Query query = getEntityManager().createQuery("SELECT a FROM Product a");
        return query.getResultList();
    }
}
