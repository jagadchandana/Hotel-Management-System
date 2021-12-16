package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import lk.system.dto.EmployeeDTO;
import lk.system.views.tm.EmployeeTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class EmployeeFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtNic;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TextField txtSearch;
    public JFXButton btnSave;
    public ComboBox cmbJPosition;
    public TableView tblEmployee;
    public TableColumn colId;
    public TableColumn colState;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colDOB;
    public TableColumn colAddress;
    public TableColumn colJobType;
    public JFXComboBox cmbStates;
    public JFXDatePicker dpDOB;
    public JFXDatePicker dpSettleDate;
    public TableColumn colsdate;


    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colsdate.setCellValueFactory(new PropertyValueFactory<>("sattleDate"));
        colJobType.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        loadAllJob();
        cmbStates.getItems().addAll("Mr.", "Mrs.", "Ms.", "Miss");
        loadAllEmployee("");
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData((EmployeeTM) newValue);
            }
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllEmployee(newValue);
        });
    }

    public void saveEmployeeOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Save Employer")){
            EmployeeDTO dto = new EmployeeDTO(txtId.getText(),
                    String.valueOf(cmbStates.getSelectionModel().getSelectedItem()),txtName.getText(),
                    txtNic.getText(),txtContact.getText(),txtAddress.getText(),String.valueOf(dpDOB.getValue()),
                    String.valueOf(dpSettleDate.getValue()),
                    String.valueOf(cmbJPosition.getSelectionModel().getSelectedItem()));
            try {
                if (new DataBaseAccessCode().saveEmployee(dto)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved", ButtonType.OK).show();
                    loadAllEmployee("");
                }else {
                    new Alert(Alert.AlertType.WARNING,"Employee save Error",ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            EmployeeDTO dto = new EmployeeDTO(txtId.getText(),String.valueOf(cmbStates.getSelectionModel().getSelectedItem()),
                    txtName.getText(),txtNic.getText(),txtContact.getText(),txtAddress.getText(),
                    String.valueOf(dpDOB.getValue()),String.valueOf(dpSettleDate.getValue()),
                    String.valueOf(cmbJPosition.getSelectionModel().getSelectedItem()));
            try {
                if(new DataBaseAccessCode().updateEmployee(dto)){
                    new Alert(Alert.AlertType.CONFIRMATION,"Employee Updated",ButtonType.OK).show();
                    loadAllEmployee("");
                }else{
                    new Alert(Alert.AlertType.WARNING,"Employee Update Error",ButtonType.OK).show();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void newEmployerOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Employer");
        clear();
    }

    public void deleteEmployeeOnAction(ActionEvent actionEvent) {
       Alert confimation = new Alert(Alert.AlertType.CONFIRMATION,"Are you Shure?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> confirmation = confimation.showAndWait();
        if (confirmation.get().equals(ButtonType.YES)){
            try {
                if (new DataBaseAccessCode().deleteEmployee(txtId.getText())){
                    new Alert(Alert.AlertType.INFORMATION,"Employee Deleted",ButtonType.OK).show();
                    loadAllEmployee("");
                }else{
                    new Alert(Alert.AlertType.WARNING,"Employee Delete Error",ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void loadAllEmployee(String text){
        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
        try {
            for (EmployeeDTO tempdto:new DataBaseAccessCode().getAllEmployee("%"+text+"%")
                 ) {
                EmployeeTM employeeTM = new EmployeeTM(
                        tempdto.getId(),
                        tempdto.getState(),
                        tempdto.getName(),
                        tempdto.getNic(),
                        tempdto.getOntact(),
                        tempdto.getAddress(),
                        tempdto.getDob(),
                        tempdto.getSattleDate(),
                        tempdto.getJobType()
                );
                obList.add(employeeTM);
            }
            tblEmployee.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void clear(){
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtSearch.clear();
        txtNic.clear();
        txtAddress.clear();
        dpSettleDate.getEditor().clear();
        dpDOB.getEditor().clear();

    }
    private void loadAllJob(){
        try {
            for (String tempid:new DataBaseAccessCode().loadAllJobs()
                 ) {
                cmbJPosition.getItems().addAll(tempid);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void setData(EmployeeTM tm){
        btnSave.setText("Update Employer");
        txtId.setText(tm.getId());
        cmbStates.setValue(tm.getState());
        cmbJPosition.setValue(tm.getJobType());
        txtName.setText(tm.getName());
        txtNic.setText(tm.getNic());
        txtContact.setText(tm.getContact());
        txtAddress.setText(tm.getAddress());
        dpDOB.setValue(LocalDate.parse(tm.getDob()));
        dpSettleDate.setValue(LocalDate.parse(tm.getSattleDate()));
    }

    public void newJobOnAction(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/JobManageForm.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Create New Job");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
