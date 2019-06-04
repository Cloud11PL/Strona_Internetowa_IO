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
    private TableColumn<Product_dto, String> clmName;

    @FXML
    private TableColumn<Product_dto, Double> clmPrice;

    @FXML
    private TableColumn<Product_dto, Category_dto> clmCategory;

    @FXML
    private TableColumn<Product_dto, Gender_dto> clmGender;

    @FXML
    private TableColumn<Product_dto, String> clmSize;

    @FXML
    private TableColumn<Product_dto, String> clmBrand;

    @FXML
    private TextField txtNewPrice;

    @FXML
    private TableView<Product_dto> tblProduct;
    

    @FXML
    void btnChangePriceClicked(ActionEvent event) {
        Double price = content_validate(txtNewPrice);
        String[] productTable = getProduct();
        if (price != null || productTable != null) {
            ClientGUI.getFacade().changePrice(price, productTable);
            refresh_table();
            Alert alert = new Alert(AlertType.INFORMATION, "Cena została poprawnie zmieniona");
            alert.showAndWait();

        }
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Home.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clmName.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Product_dto, Double>("price"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Product_dto, Category_dto>("category"));
        clmGender.setCellValueFactory(new PropertyValueFactory<Product_dto, Gender_dto>("gender"));
        clmSize.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("size"));
        clmBrand.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("brand"));
        tblProduct.getColumns().clear();
        tblProduct.getColumns().addAll(clmName, clmPrice, clmCategory, clmGender, clmSize, clmBrand);
        refresh_table();
    }

    private void refresh_table() {
        tblProduct.getItems().removeAll(tblProduct.getItems());
        ObservableList<Product_dto> data = FXCollections.observableArrayList(ClientGUI.getFacade().getProducts());
        tblProduct.setItems(data);
    }

    public Double content_validate(TextField val) {
        Double i = null;
        try {
            i = Double.parseDouble(val.getText().replace(",", "."));
            return i;
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR, "Należy podać poprawną cenę");
            alert.showAndWait();
        };
        return i;
    }

    private String[] getProduct() {
        Product_dto product = tblProduct.getSelectionModel().getSelectedItem();
        if (product != null) {
            String[] productTable = new String[]{product.getName(), String.valueOf(product.getPrice()), product.getCategory().name(), product.getGender().name(), product.getSize(), product.getBrand()};
            return productTable;
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Wybierz produkt z tabeli");
            alert.showAndWait();
            return null;
        }
    }

}
