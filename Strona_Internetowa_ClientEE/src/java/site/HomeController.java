/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site;

/**
 *
 * @author Juju
 */

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Pane pane;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnBrowseBasket;

    @FXML
    private Button btnModifyBasket;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnRemoveProduct;

    @FXML
    private Button btnChangePrice;

    @FXML
    void btnAddClientClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/AddClient.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @FXML
    void btnAddProductClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/AddProduct.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @FXML
    void btnBrowseBasketClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/BrowseBasket.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @FXML
    void btnChangePriceClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/ChangePrice.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @FXML
    void btnModifyBasketPressed(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/ModifyBasket.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @FXML
    void btnRemoveProductClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/RemoveProduct.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }


}
