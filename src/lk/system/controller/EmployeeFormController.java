package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    public void seveEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void newEmployerOnAction(ActionEvent actionEvent) {
    }

    public void deleteEmployeeOnAction(ActionEvent actionEvent) {
    }
}
