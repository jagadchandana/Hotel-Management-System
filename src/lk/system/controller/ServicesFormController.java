package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lk.system.db.DBConnection;

import java.io.IOException;

public class ServicesFormController {
    public JFXTextField txtId;
    public TableView tblServices;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colFood;
    public TableColumn colBar;
    public TableColumn colTransport;
    public TableColumn colPool;
    public TableColumn colPark;
    public TableColumn colBeach;
    public TableColumn colType;
    public TableColumn colPackPrice;
    public JFXCheckBox cbxFood;
    public JFXCheckBox cbxBar;
    public JFXCheckBox cbxTransport;
    public JFXCheckBox cbxPool;
    public JFXCheckBox cbxPark;
    public JFXCheckBox cbxBeach;
    public JFXComboBox txtType;
    public JFXTextField txtName;
    public JFXButton btnSave;

    public void changePriceOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ChangeServicesPriceForm.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error");
        }
        Stage stage = new Stage();
        stage.setTitle("Price Change");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void newServicesOnAction(ActionEvent actionEvent) {
        btnSave.setText("New Services");

    }

    public void saveServicesOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String type= String.valueOf(txtType.getValue());
      //  Double food=cbxFood.selectedProperty();
        if (btnSave.getText().equalsIgnoreCase("New Services")){

        }

    }

    public void deleteServicesOnAction(ActionEvent actionEvent) {
    }
}
