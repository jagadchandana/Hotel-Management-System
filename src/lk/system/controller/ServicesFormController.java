package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.ServicesDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

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
    public JFXComboBox cmbType;

    public void initialize(){
        cmbType.getItems().addAll("Luxury","semi Luxury","Class A","Class B","Class C","Normal","Permenent");
    }

    public void changePriceOnAction(ActionEvent actionEvent) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ChangeServicesPriceForm.fxml"));
        Parent root = null;
        root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Price Change");
        stage.setScene(new Scene(root));
        root.getStylesheets().add(String.valueOf(getClass().getResource("../assert/Styles.css")));
        stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void newServicesOnAction(ActionEvent actionEvent) {
        btnSave.setText("New Services");

    }

    public void saveServicesOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id=txtId.getText();
        String name=txtName.getText();
        String type=String.valueOf(cmbType.getValue());
        System.out.println("ok");
        double food;
        double bar;
        double transport;
        double pool;
        double kidsPark;
        double beach;
        double[]  values=new DataBaseAccessCode().getPrices();
        System.out.println(Arrays.toString(values));
      /*  if (cbxFood.selectedProperty().getValue()){
            food=values[0];
        }else{
            food=0;
        }
        if (cbxBar.selectedProperty().getValue()){
            bar=values[1];
        }else{
            bar=0;
        }
        if (cbxTransport.selectedProperty().getValue()){
            transport=values[2];
        }else{
            transport=0;
        }
        if (cbxPool.selectedProperty().getValue()){
            pool=values[3];
        }else{
            pool=0;
        }
        if (cbxPark.selectedProperty().getValue()){
            kidsPark=values[4];
        }else{
            kidsPark=0;
        }
        if (cbxBeach.selectedProperty().getValue()){
            beach=values[5];
        }else{
            beach=0;
        }
        System.out.println(food);
        ServicesDTO dto = new ServicesDTO(id,name,type,food,bar,transport,pool,kidsPark,beach);
        if (btnSave.getText().equalsIgnoreCase("New Services")){
            if (new DataBaseAccessCode().saveServices(dto)){

            }
        }*/

    }

    public void deleteServicesOnAction(ActionEvent actionEvent) {
    }
}
