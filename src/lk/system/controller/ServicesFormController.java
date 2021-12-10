package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.system.db.DBConnection;

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

    }

    public void newServicesOnAction(ActionEvent actionEvent) {
        btnSave.setText("New Services");

    }

    public void saveServicesOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        String name=txtName.getText();
        String type= String.valueOf(txtType.getValue());
        Double food=cbxFood.selectedProperty();
        if (btnSave.getText().equalsIgnoreCase("New Services")){

        }

    }

    public void deleteServicesOnAction(ActionEvent actionEvent) {
    }
}
