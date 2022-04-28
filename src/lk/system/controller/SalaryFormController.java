package lk.system.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.system.DataBaseAccessCode;

public class SalaryFormController {
    public TextField txtOT;
    public TextField txtLeaves;
    public JFXComboBox cmbId;
    public TextField txtName;
    public TextField txtDesignation;
    public TextField txtDays;
    public TableView tblDetails;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colJob;
    public TableColumn colOT;
    public TableColumn colDays;
    public TableColumn colBonus;
    public TableColumn colSalary;
    public TableColumn colOption;
    public CheckBox chkBxMonth;
    public Label lblSalary;

    public void initialize(){


    }

    public void printPaySheetOnAction(ActionEvent actionEvent) {
    }

    public void showPaySheetOnAction(ActionEvent actionEvent) {
    }

    public void ShowOnAction(ActionEvent actionEvent) {
    }

    public void printBillOnAction(ActionEvent actionEvent) {
    }

    private void loadAllIds(){
        for (String tempid:new DataBaseAccessCode().loadAll()
        ) {
            cmbId.getItems().addAll(tempid);

        }
    }
}
