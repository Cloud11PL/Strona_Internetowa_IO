/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesstier;

import bussineslogic.Facade;
import bussineslogic.dto.Client_dto;
import bussineslogic.dto.Product_dto;
import bussineslogic.model.Client;
import bussineslogic.model.Product;
import integrationtier.ClientFacade;
import integrationtier.ProductFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Juju
 */
@Stateless
public class EJBFacade implements EJBFacadeRemote {

    @EJB
    private ProductFacade productFacade;

    @EJB
    private ClientFacade clientFacade;
    
    

   static Facade facade=new Facade();
   
   @Override
   public String addProduct(String[] data){
       return facade.addProduct(data);
   }

    @Override
    public void addProductList() {
        ArrayList<Product> productList = new ArrayList<Product>(getAllProducts());
        facade.setProductList(productList);
    }
   
   @Override
   public String addClient(String[] clientTable) {
       String message = facade.addClient(clientTable);
       addClient();
       return message;
}

    @Override
    public void removeProduct_dto(Product_dto product_dto) {
        removeProduct(product_dto);
        facade.removeProduct_dto(product_dto);
    }

    @Override
    public ArrayList<Client_dto> getClients() {
        return facade.getClients();
    }

    @Override
    public void addClientList() {
        ArrayList<Client> clientList = new ArrayList<Client>(getAllClients());
        facade.setClientList(clientList);
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
    @Override
    public void addClient(){
        clientFacade.addClient(facade.getClientList());
    }
    @Override
    public void addProduct(){
        productFacade.addProduct(facade.getProductList());
    }
    @Override
    public void changePrice(Double price, String []productTable){
        productFacade.changePrice(facade.modifyProductPrice(price, productTable));
    }
    @Override
    public void removeProduct(Product_dto product_dto){
        productFacade.removeProduct(product_dto);
    }
    @Override
    public List<Product> getAllProducts() {
        return productFacade.getAllProducts();
    }
    @Override
    public List<Product_dto> getAllProducts_dto() {
        return productFacade.getAllProducts_dto();
    }

    @Override
    public List<Client> getAllClients() {
        return clientFacade.getClientList();
    }
    
}
