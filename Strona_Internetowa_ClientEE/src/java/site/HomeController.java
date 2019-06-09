package site;

/**
 *
 * @author Juju, Werka
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import site.locale.Language;
import site.locale.LocaleService;

public class HomeController implements Initializable {

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
    private Button btnENG;

    @FXML
    private Button btnPL;
   
    
    @FXML
    void btnENGSelected(ActionEvent event) {
        LocaleService.INSTANCE.setLanguage(Language.EN);
        updateLabels();
    }

    @FXML
    void btnPLSelected(ActionEvent event) {
         LocaleService.INSTANCE.setLanguage(Language.PL);
         updateLabels();
    }

    
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateLabels();
    }
    
    public void updateLabels() {
        btnAddClient.setText(LocaleService.INSTANCE.getMessage("addClient"));
        btnBrowseBasket.setText(LocaleService.INSTANCE.getMessage("browseBasket"));
        btnModifyBasket.setText(LocaleService.INSTANCE.getMessage("modifyBasket"));
        btnAddProduct.setText(LocaleService.INSTANCE.getMessage("addProduct"));
        btnRemoveProduct.setText(LocaleService.INSTANCE.getMessage("removeProduct"));
        btnChangePrice.setText(LocaleService.INSTANCE.getMessage("chengeProductPrice"));
    }

}
