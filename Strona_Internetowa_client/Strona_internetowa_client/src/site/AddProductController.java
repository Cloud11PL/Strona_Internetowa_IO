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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddProductController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnGoBack;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtBrand;

    @FXML
    private ComboBox<Category> cboxCategory;

    @FXML
    private ComboBox<Gender> cboxGender;

    @FXML
    void btnAddProductClicked(ActionEvent event) {
        String[] data = form_product();
        if (data == null) {
            return;
        }
        ClientTier.getFacade().addProduct(data);
        clearAllFields();
        Alert alert = new Alert(AlertType.INFORMATION, "Produkt został poprawnie dodany");
        alert.showAndWait();
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));

    }
    
    private void clearAllFields(){
        txtBrand.clear();
        txtPrice.clear();
        txtSize.clear();
        txtname.clear();
        cboxCategory.setValue(null);
        cboxGender.setValue(null);
    }

    public String[] form_product() {

        if (content_validate(txtname) == null) {
            return null;
        }
        if (content_validate(txtPrice) == null) {
            return null;
        }
        if (content_validate(txtSize) == null) {
            return null;
        }
        if (content_validate(txtBrand) == null) {
            return null;
        }
        if (content_validate(cboxCategory) == null) {
            return null;
        }
        if (content_validate(cboxGender) == null) {
            return null;
        }

        try {
            Double.parseDouble(txtPrice.textProperty().getValue());
            String data[] = {(String) txtname.getText(),
                (String) txtPrice.getText(), cboxCategory.getSelectionModel().getSelectedItem().toString(), cboxGender.getSelectionModel().getSelectedItem().toString(), (String) txtSize.getText(),
                (String) txtBrand.getText()};
            return data;

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(AlertType.ERROR, "Cena musi być liczbą");
            alert.showAndWait();
        }

        return null;
    }

    public String content_validate(TextField val) {
        String s = val.getText();
        if (s.equals("")) {
            Alert alert = new Alert(AlertType.ERROR, "Uzupełnij dane");
            alert.showAndWait();
            return null;
        } else {
            s = s.replaceAll(" ", "_");
            val.setText(s);
            return s;
        }
    }

    public String content_validate(ComboBox val) {
        if (val.getSelectionModel().getSelectedItem() != null) {
            String s = val.getSelectionModel().getSelectedItem().toString();
            s = s.replaceAll(" ", "_");
            return s;
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Wybierz opcje z listy");
            alert.showAndWait();
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboxCategory.getItems().setAll(Category.values());
        cboxGender.getItems().setAll(Gender.values());
    }

}
