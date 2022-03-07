package lk.system.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.CustomerDTO;
import lk.system.views.tm.CustomerTM;
import sun.util.resources.LocaleData;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class CustomerFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtNICNum;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNum;
    public JFXTextField txtRoomId;
    public JFXTextField txtServiceId;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colCusNIC;
    public TableColumn colCusGender;
    public TableColumn colCusAddress;
    public TableColumn colCusConNumber;
    public TableColumn colCusRoomId;
    public TableColumn colCusServiceId;
    public ComboBox cmbGender;
    public TextField txtSearchbar;
    public JFXButton btnSaveCustomer;
    public JFXComboBox cmbState;
    public JFXDatePicker dtonDate;
    public JFXDatePicker dtoffDate;
    public JFXTimePicker tpOnTime;
    public JFXTimePicker tpOffTime;
    public JFXComboBox cmbRoomId;
    public JFXComboBox cmbServiceId;
    public TableColumn colOnDate;
    public TableColumn colState;


    public void initialize(){
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCusNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCusConNumber.setCellValueFactory(new PropertyValueFactory<>("contactNum"));
        colCusRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colCusServiceId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        colOnDate.setCellValueFactory(new PropertyValueFactory<>("onDate"));

        loadAllIds();
        loadAllCustomers("");
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData(newValue);
            }

        });
        txtSearchbar.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllCustomers(newValue);
        });
        cmbState.getItems().addAll("Mr.", "Mrs.", "Ms.", "Miss");


    }
    private void setData(CustomerTM tm){
        btnSaveCustomer.setText("Update Customer");
        txtCustomerId.setText(tm.getCustomerId());
        cmbState.setValue(tm.getState());
        txtCustomerName.setText(tm.getName());
        txtNICNum.setText(tm.getNic());
        txtContactNum.setText(tm.getContactNum());
        txtAddress.setText(tm.getAddress());
        cmbRoomId.setValue(tm.getRoomId());
        cmbServiceId.setValue(tm.getServiceId());
        dtonDate.setValue(LocalDate.parse(tm.getOnDate()));
        dtoffDate.setValue(LocalDate.parse(tm.getOffDate()));
        tpOnTime.setValue(LocalTime.parse(tm.getOnTime()));
        tpOffTime.setValue(LocalTime.parse(tm.getOffTime()));

    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        btnSaveCustomer.setText("Save Customer");
        clearField();
    }
    public void clearField(){
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtNICNum.clear();
        txtContactNum.clear();
        txtAddress.clear();
        dtoffDate.getEditor().clear();
        dtonDate.getEditor().clear();
        tpOnTime.getEditor().clear();
        tpOffTime.getEditor().clear();
    }

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        if (btnSaveCustomer.getText().equalsIgnoreCase("Save Customer")){
            try {
                 String customerId=txtCustomerId.getText();
                 String State= String.valueOf(cmbState.getSelectionModel().getSelectedItem());
                 String name=txtCustomerName.getText();
                 String nic=txtNICNum.getText();
                 String contact=txtContactNum.getText();
                 String address=txtAddress.getText();
                 String roomId= String.valueOf(cmbRoomId.getSelectionModel().getSelectedItem());
                 String serviceId= String.valueOf(cmbServiceId.getSelectionModel().getSelectedItem());
                 String onDate= String.valueOf(dtonDate.getValue());
                 String offDate= String.valueOf(dtoffDate.getValue());
                 String onTime= String.valueOf(tpOnTime.getValue());
                 String offTime= String.valueOf(tpOffTime.getValue());

                 CustomerDTO dto = new CustomerDTO(customerId,State,name,nic,contact,address,roomId,serviceId,onDate,offDate,onTime,offTime);

                if (new DataBaseAccessCode().saveCustomer(dto)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved", ButtonType.OK).show();
                    loadAllCustomers("");
                } else{
                    new Alert(Alert.AlertType.WARNING, "Customr Save Error", ButtonType.CANCEL).show();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            try {


               String name=txtCustomerName.getText();
               String nic=txtNICNum.getText();
               String address=txtAddress.getText();
               String contact=txtContactNum.getText();
               String State= String.valueOf(cmbState.getSelectionModel().getSelectedItem());
               String roomId= String.valueOf(cmbRoomId.getSelectionModel().getSelectedItem());
               String servicesId= String.valueOf(cmbServiceId.getSelectionModel().getSelectedItem());
               String id=txtCustomerId.getText();
               String onDate= String.valueOf(dtonDate.getValue());
               String offDate= String.valueOf(dtoffDate.getValue());
               String onTime= String.valueOf(tpOnTime.getValue());
               String offTime= String.valueOf(tpOffTime.getValue());
               CustomerDTO dto=new CustomerDTO(id,State,name,nic,address,contact,roomId,servicesId,onDate,offDate,onTime,offTime);

                if (new DataBaseAccessCode().updateCustomer(dto)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer was Update", ButtonType.OK).show();
                    loadAllCustomers("");
                } else{
                    new Alert(Alert.AlertType.WARNING, "Customr Update Error", ButtonType.CANCEL).show();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) {
        //--Delete
        Alert confimation = new Alert(
                Alert.AlertType.CONFIRMATION,"Are You Shure?",
                ButtonType.YES,ButtonType.CLOSE
        );
        Optional<ButtonType> confirmation = confimation.showAndWait();
        if (confirmation.get().equals(ButtonType.YES)){
            try {

                if (new DataBaseAccessCode().deleteCustomer(txtCustomerId.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted", ButtonType.OK).show();
                    clearField();
                    loadAllCustomers("");
                } else{
                    new Alert(Alert.AlertType.WARNING, "Customr Delete Error", ButtonType.CANCEL).show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }
        }

        //---
    }

    public void otherPersonInfoOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/PersonForm.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Other Person");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void billOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/BillGenerateForm.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Generate Bill");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void loadAllCustomers(String searchText) {
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        try {

            for (CustomerDTO tempdto: new DataBaseAccessCode().getAllCustomer("%"+searchText+"%")
                 ) {
              CustomerTM customerTM = new CustomerTM(
                        tempdto.getCustomerId(),
                        tempdto.getState(),
                        tempdto.getName(),
                        tempdto.getNic(),
                        tempdto.getAddress(),
                        tempdto.getContact(),
                        tempdto.getRoomId(),
                        tempdto.getServiceId(),
                        tempdto.getOnDate(),
                        tempdto.getOffDate(),
                        tempdto.getOnTime(),
                        tempdto.getOffTime()

                );
              obList.add(customerTM);
                
            }

            tblCustomer.setItems(obList);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }
    //load room-----------------
    public void loadAllIds(){
        try {
            for (String tempid:new DataBaseAccessCode().loadAllRoomIds()
                 ) {
                cmbRoomId.getItems().addAll(tempid);

            }
            for (String tempid:new DataBaseAccessCode().loadAllServies()
                 ) {
                cmbServiceId.getItems().addAll(tempid);
            }
            new DataBaseAccessCode().loadAllRoomIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    //load room-----------------


}
























