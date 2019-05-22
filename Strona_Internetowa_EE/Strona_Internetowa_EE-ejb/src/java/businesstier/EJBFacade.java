/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesstier;

import bussineslogic.Facade;
import bussineslogic.dto.Client_dto;
import bussineslogic.dto.Product_dto;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Juju
 */
@Stateless
public class EJBFacade implements EJBFacadeRemote {

   Facade facade=new Facade();
   
   @Override
   public String addProduct(String[] data){
       return facade.addProduct(data);
   }
   @Override
   public String addClient(String[] clientTable) {
        return facade.addClient(clientTable);
}

    @Override
    public void removeProduct_dto(Product_dto product_dto) {
         facade.removeProduct_dto(product_dto);
    }

    @Override
    public ArrayList<Client_dto> getClients() {
        return facade.getClients();
    }
   @Override
    public ArrayList<Product_dto> getProducts() {
        return facade.getProducts();
    }
    @Override
    public void modifyProductPrice(double price, String[] productTable) {
        facade.modifyProductPrice(price, productTable);
    }

    @Override
    public String addProductToBasket(String[] clientTable, String[] productTable) {
        return facade.addProductToBasket(clientTable, productTable);
    }

    @Override
    public ArrayList<Product_dto> getBasket(String[] clientTable) {
        return facade.getBasket(clientTable);
    }

    @Override
    public ArrayList<Product_dto> getFilteredBasket(String[] clientTable, String[] filters) {
        return facade.getFilteredBasket(clientTable, filters);
    }

    @Override
    public String removeFromBasket(String[] clientTable, String[] productTable) {
        return facade.removeFromBasket(clientTable, productTable);
    }

    @Override
    public String browseBasket(String[] clientTable, String[] filters) {
        return facade.browseBasket(clientTable, filters);
    }
}
