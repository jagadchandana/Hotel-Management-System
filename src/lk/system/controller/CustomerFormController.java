package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.system.views.tm.CustomerTM;

import java.sql.*;
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

    public void initialize(){
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCusNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCusConNumber.setCellValueFactory(new PropertyValueFactory<>("contactNum"));
        colCusGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colCusRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colCusServiceId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));

        loadAllCustomers("");
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData(newValue);
            }

        });
        txtSearchbar.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllCustomers(newValue);
        });


    }
    private void setData(CustomerTM tm){
        btnSaveCustomer.setText("Update Customer");
        txtCustomerId.setText(tm.getCustomerId());
        txtCustomerName.setText(tm.getName());
        txtNICNum.setText(tm.getNic());
        txtContactNum.setText(tm.getContactNum());
        txtAddress.setText(tm.getAddress());
        txtRoomId.setText(tm.getRoomId());
        txtServiceId.setText(tm.getServiceId());
        //cmbGender.setText(String.valueOf( tm.getGender()));
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        btnSaveCustomer.setText("Save Customer");
        clearField();
    }
    private void clearField(){
        txtServiceId.clear();
        txtCustomerName.clear();
        txtNICNum.clear();
        txtContactNum.clear();
        txtAddress.clear();
        txtRoomId.clear();
        txtServiceId.clear();
    }

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        if (btnSaveCustomer.getText().equalsIgnoreCase("Save Customer")){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
                String SQL = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(SQL);
                statement.setObject(1, txtCustomerId.getText());
                statement.setObject(2, txtCustomerName.getText());
                statement.setObject(3, txtNICNum.getText());
                statement.setObject(4, txtAddress.getText());
                statement.setObject(5, txtContactNum.getText());
                // statement.setObject(6,cmbGender.getItems());-->munu button loader ex error
                statement.setObject(7, txtRoomId.getText());
                statement.setObject(8, txtServiceId.getText());
                Boolean isSaved = statement.executeUpdate() > 0;
                if (isSaved){
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
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
                String SQL = "UPDATE customer SET Name=?, NIC=?, Address=?, Contact_num=?, Gender=?, Room_Id=?, Services_Id=? WHERE Customer_ID=?";
                PreparedStatement statement = connection.prepareStatement(SQL);
                statement.setObject(1, txtCustomerName.getText());
                statement.setObject(2, txtNICNum.getText());
                statement.setObject(3, txtAddress.getText());
                statement.setObject(4, txtContactNum.getText());
               //  statement.setObject(6,cmbGender.getItems());-->munu button loader ex error
                statement.setObject(6, txtRoomId.getText());
                statement.setObject(7, txtServiceId.getText());
                statement.setObject(8, txtCustomerId.getText());
                Boolean isSaved = statement.executeUpdate() > 0;
                if (isSaved){
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
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
                String SQL1 = "DELETE FROM customer WHERE Customer_Id=?";
                PreparedStatement statement1 = connection1.prepareStatement(SQL1);
                statement1.setObject(1,txtCustomerId.getText());
               boolean isDeleted = statement1.executeUpdate()>0;
                if (isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved", ButtonType.OK).show();
                    loadAllCustomers("");
                } else{
                    new Alert(Alert.AlertType.WARNING, "Customr Save Error", ButtonType.CANCEL).show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            }
        }

        //---
    }

    public void loadAllCustomers(String searchText) {
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
            String SQL = "SELECT * FROM customer WHERE Customer_Id LIKE ? OR Name LIKE ? OR NIC LIKE ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,"%"+searchText+"%");
            statement.setObject(2,"%"+searchText+"%");
            statement.setObject(3,"%"+searchText+"%");

            ResultSet rst = statement.executeQuery();
            while (rst.next()){
                /*System.out.println(rst.getString(1));*/
                obList.add(new CustomerTM(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getString(7),
                        rst.getString(8)
                ));

            }
            tblCustomer.setItems(obList);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }


   // public void deleteCustomerOnAction(ActionEvent actionEvent) { }
}
