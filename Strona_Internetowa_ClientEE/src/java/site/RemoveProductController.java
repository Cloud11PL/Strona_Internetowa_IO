package site;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juju
 */

import client_tier.ClientGUI;
import bussineslogic.dto.Category_dto;
import bussineslogic.dto.Gender_dto;
import bussineslogic.dto.Product_dto;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RemoveProductController implements Initializable{

    @FXML
    private Pane pane;

    @FXML
    private Button btnRemoveProduct;

    @FXML
    private Button btnGoBack;

    @FXML
    private TableColumn<Product_dto, String> clmName;

    @FXML
    private TableColumn<Product_dto, Double>clmPrice;

    @FXML
    private TableColumn<Product_dto, Category_dto> clmCategory;

    @FXML
    private TableColumn<Product_dto, Gender_dto> clmGender;

    @FXML
    private TableColumn<Product_dto, String> clmSize;

    @FXML
    private TableColumn<Product_dto, String> clmBrand;
    
    @FXML
    private TableView<Product_dto> tblProducts;

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Home.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @FXML
    void btnRemoveProductClicked(ActionEvent event) {
        Product_dto product=content_validate();
        if(product!=null){
            ClientGUI.getFacade().removeProduct_dto(product);
            refresh_table();
            Alert alert = new Alert(AlertType.INFORMATION, "Produkt został poprawnie usunięty");
            alert.showAndWait();
        }
    }
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        clmName.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Product_dto, Double>("price"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Product_dto, Category_dto>("category"));
        clmGender.setCellValueFactory(new PropertyValueFactory<Product_dto, Gender_dto>("gender"));
        clmSize.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("size"));
        clmBrand.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("brand"));
        tblProducts.getColumns().clear();
        tblProducts.getColumns().addAll(clmName,clmPrice,clmCategory,clmGender,clmSize,clmBrand);
        refresh_table();
    }
    
    public void refresh_table(){
        tblProducts.getItems().removeAll(tblProducts.getItems());
        ObservableList<Product_dto> data = FXCollections.observableArrayList(ClientGUI.getFacade().getProducts());
        tblProducts.setItems(data);
    }
    
    public Product_dto content_validate() {
        Product_dto item=tblProducts.getSelectionModel().getSelectedItem();
        if( item !=null){
            return item ;
        }else{
            Alert alert = new Alert(AlertType.ERROR, "Wybierz produkt z tabeli");
            alert.showAndWait();
            return null;
        }
    }

}

