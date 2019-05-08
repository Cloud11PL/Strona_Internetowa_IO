/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juju
 */
package site;

import bussineslogic.model.Category;
import bussineslogic.model.Gender;
import bussineslogic.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangePriceController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Button btnChangePrice;

    @FXML
    private Button btnGoBack;

    @FXML
    private TableColumn<Product, String> clmName;

    @FXML
    private TableColumn<Product, Double>clmPrice;

    @FXML
    private TableColumn<Product, Category> clmCategory;

    @FXML
    private TableColumn<Product, Gender> clmGender;

    @FXML
    private TableColumn<Product, String> clmSize;

    @FXML
    private TableColumn<Product, String> clmBrand;

    @FXML
    private TextField txtNewPrice;
    
    @FXML
    private TableView<Product> tblProduct;

    @FXML
    void btnChangePriceClicked(ActionEvent event) {
        Double price=content_validate(txtNewPrice);
        String [] productTable=getProduct();
        if(price!=null || productTable!=null){
        ClientTier.getFacade().modifyProductPrice(price, productTable);
        refresh_table();
        Alert alert = new Alert(AlertType.INFORMATION, "Cena została poprawnie zmieniona");
        alert.showAndWait();
        }
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        clmName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Product, Category>("category"));
        clmGender.setCellValueFactory(new PropertyValueFactory<Product, Gender>("gender"));
        clmSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
        clmBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        tblProduct.getColumns().clear();
        tblProduct.getColumns().addAll(clmName,clmPrice,clmCategory,clmGender,clmSize,clmBrand);
        refresh_table();
    }

    private void refresh_table() {
        tblProduct.getItems().removeAll(tblProduct.getItems());
        ObservableList<Product> data = FXCollections.observableArrayList(ClientTier.getFacade().getProductList());
        tblProduct.setItems(data);
    }
    
   public Double content_validate(TextField val) {
       Double i=null;
       try{
        i=Double.parseDouble(val.getText().replace(",","."));
        return i;
        }catch(Exception ex){
            Alert alert = new Alert(AlertType.ERROR, "Należy podać poprawną cenę");
            alert.showAndWait();
        };
        return i;
    }
   private String[] getProduct() {
        Product product=tblProduct.getSelectionModel().getSelectedItem();
        if(product!=null){
        String[] productTable = new String[]{product.getName(), String.valueOf(product.getPrice()), product.getCategory().name(), product.getGender().name(), product.getSize(), product.getBrand()};
        return productTable;
        }else{
            Alert alert = new Alert(AlertType.ERROR, "Wybierz produkt z tabeli");
            alert.showAndWait();
            return null;
        }
    }

}
