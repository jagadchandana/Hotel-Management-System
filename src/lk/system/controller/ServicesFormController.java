package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.ClassPackageDTO;
import lk.system.dto.CustomerDTO;
import lk.system.dto.ServicesDTO;
import lk.system.dto.ServicesPackageDTO;
import lk.system.views.tm.CustomerTM;
import lk.system.views.tm.ServicesTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colFood.setCellValueFactory(new PropertyValueFactory<>("food"));
        colBar.setCellValueFactory(new PropertyValueFactory<>("bar"));
        colTransport.setCellValueFactory(new PropertyValueFactory<>("transport"));
        colPool.setCellValueFactory(new PropertyValueFactory<>("pool"));
        colPark.setCellValueFactory(new PropertyValueFactory<>("kidsPark"));
        colBeach.setCellValueFactory(new PropertyValueFactory<>("beach"));
        colPackPrice.setCellValueFactory(new PropertyValueFactory<>("packagePrice"));

        loadAllServices("");
        tblServices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData((ServicesTM) newValue);
            }

        });
       /* txtId.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllServices(newValue);
        });*/

        cmbType.getItems().addAll("Luxury","semi Luxury","Class A","Class B","Class C","Normal","Permenent");
    }
    private  void setData(ServicesTM tm){
        btnSave.setText("Update Service");
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        cmbType.setValue(tm.getType());
        if (tm.getFood()==0){
            cbxFood.selectedProperty().setValue(false);
        }else{
            cbxFood.selectedProperty().setValue(true);
        }
        if (tm.getBar()==0){
            cbxBar.selectedProperty().setValue(false);
        }else{
            cbxBar.selectedProperty().setValue(true);
        }
        if (tm.getTransport()==0){
            cbxTransport.selectedProperty().setValue(false);
        }else{
            cbxTransport.selectedProperty().setValue(true);
        }
        if (tm.getPool()==0){
            cbxPool.selectedProperty().setValue(false);
        }else{
            cbxPool.selectedProperty().setValue(true);
        }
        if (tm.getKidsPark()==0){
            cbxPark.selectedProperty().setValue(false);
        }else{
            cbxPark.selectedProperty().setValue(true);
        }
        if (tm.getBeach()==0){
            cbxBeach.selectedProperty().setValue(false);
        }else{
            cbxBeach.selectedProperty().setValue(true);
        }

    }

    public void changePriceOnAction(ActionEvent actionEvent) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ChangeServicesPriceForm.fxml"));
        Parent root = null;
        root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Price Change");
        stage.setScene(new Scene(root));
        root.getStylesheets().add(String.valueOf(getClass().getResource("../assert/Stylesheet.css")));
        stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void newServicesOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Service");
        clearField();

    }

    public void saveServicesOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtId.getText();
        String name=txtName.getText();
        String type= String.valueOf(cmbType.getSelectionModel().getSelectedItem());
        double foodPrice=0;
        double barPrice=0;
        double beachPrice=0;
        double poolPrice=0;
        double transportPrice=0;
        double kidsParkPrice=0;
        double classPrice=0;
        double packagePrice=0;
        ClassPackageDTO tempdto=new DataBaseAccessCode().getAllClassPrice(1);
        ServicesPackageDTO dto = new DataBaseAccessCode().getAllServicesPrice(1);
        if(cbxFood.selectedProperty().getValue()){
            foodPrice=dto.getFoodprice();
        }
        if(cbxBar.selectedProperty().getValue()){
            barPrice=dto.getBarPrice();
        }
        if(cbxBeach.selectedProperty().getValue()){
            beachPrice=dto.getBeachPrice();
        }
        if(cbxPool.selectedProperty().getValue()){
            poolPrice=dto.getPoolPrice();
        }
        if(cbxTransport.selectedProperty().getValue()){
            transportPrice=dto.getTransportPrice();
        }
        if(cbxPark.selectedProperty().getValue()){
            kidsParkPrice=dto.getKidsPrice();
        }
        switch (type){
            case "Luxury":
                classPrice=tempdto.getLuxury();
                break;
            case "semi Luxury":
                classPrice=tempdto.getSemiLuxury();
                break;
            case "Class A":
                classPrice=tempdto.getClassA();
                break;
            case "Class B":
                classPrice=tempdto.getClassB();
                break;
            case "Class C":
                classPrice=tempdto.getClassC();
                break;
            case "Normal":
                classPrice= tempdto.getNormal();
                break;
            case "Permenent":
                classPrice= tempdto.getPermenent();
                break;
            default:
                classPrice=0;
                break;
        }
        packagePrice=(foodPrice+barPrice+transportPrice+beachPrice+poolPrice+kidsParkPrice)*classPrice;

        ServicesDTO sdto = new ServicesDTO(
                id,name,type,foodPrice,barPrice,transportPrice,poolPrice,kidsParkPrice,beachPrice,packagePrice);
        if (btnSave.getText().equalsIgnoreCase("Save service")){
            if (new DataBaseAccessCode().savePackage(sdto)){
                new Alert(Alert.AlertType.CONFIRMATION, "Package Saved", ButtonType.OK).show();
                loadAllServices("");
                clearField();
            }else {
                new Alert(Alert.AlertType.ERROR, "Package Save ERROR", ButtonType.OK).show();
            }
        }else{
                if (new DataBaseAccessCode().updatePackage(sdto)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Package Updated", ButtonType.OK).show();
                    loadAllServices("");
                    clearField();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Package Update Error",ButtonType.OK).show();
                }
        }





    }

    public void deleteServicesOnAction(ActionEvent actionEvent) {
        Alert confimation = new Alert(
                Alert.AlertType.CONFIRMATION,"Are You Shure?",
                ButtonType.YES,ButtonType.CLOSE
        );
        Optional<ButtonType> confirmation = confimation.showAndWait();
        if (confirmation.get().equals(ButtonType.YES)){
            try {

                if (new DataBaseAccessCode().deletePackage(txtId.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted", ButtonType.OK).show();
                    clearField();
                    loadAllServices("");
                } else{
                    new Alert(Alert.AlertType.WARNING, "Customr Delete Error", ButtonType.CANCEL).show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }
        }
    }

    public void loadAllServices(String searchText) {
        ObservableList<ServicesTM> obList = FXCollections.observableArrayList();
        try {

            for (ServicesDTO tempdto: new DataBaseAccessCode().getAllServices("%"+searchText+"%")
            ) {
                ServicesTM servicesTM = new ServicesTM(
                        tempdto.getId(),
                        tempdto.getName(),
                        tempdto.getType(),
                        tempdto.getFood(),
                        tempdto.getBar(),
                        tempdto.getTransport(),
                        tempdto.getPool(),
                        tempdto.getKidsPark(),
                        tempdto.getBeach(),
                        tempdto.getPackagePrice()

                );
                obList.add(servicesTM);

            }

            tblServices.setItems(obList);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    private void clearField(){
        txtId.clear();
        txtName.clear();
        cbxFood.selectedProperty().setValue(false);
        cbxBar.selectedProperty().setValue(false);
        cbxTransport.selectedProperty().setValue(false);
        cbxPool.selectedProperty().setValue(false);
        cbxPark.selectedProperty().setValue(false);
        cbxBeach.selectedProperty().setValue(false);
    }
}
