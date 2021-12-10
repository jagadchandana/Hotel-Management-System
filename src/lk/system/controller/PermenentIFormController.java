package lk.system.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.SnapshotResult;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.system.DataBaseAccessCode;
import lk.system.dto.PItemDTO;
import lk.system.views.tm.PItemTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class PermenentIFormController {
    public JFXTextField txtCode;
    public JFXTextField txtName;
    public JFXTextField txtBrand;
    public TableView tblPermanentInventory;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colBrand;
    public TableColumn colCompany;
    public TableColumn colInputDate;
    public JFXDatePicker dpInput;
    public JFXTextField txtCompany;
    public JFXToggleButton tbtnWarranty;
    public JFXButton btnSave;
    public JFXTextArea txtDescription;
    public TextField txtSearch;
    public TextField txtQty;
    public TableColumn colQty;

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colInputDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadAllPItem("");
        tblPermanentInventory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData((PItemTM) newValue);
            }
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllPItem(newValue);
        });
    }

    public  void setData(PItemTM tm){
        btnSave.setText("Update Item");
        txtCode.setText(tm.getCode());
        txtName.setText(tm.getName());
        txtQty.setText(tm.getQty());
        txtBrand.setText(tm.getBrand());
        txtCompany.setText(tm.getCompany());
        dpInput.setValue(LocalDate.parse(tm.getDate()));
        txtDescription.setText(tm.getDescription());
        tbtnWarranty.setSelected(Boolean.parseBoolean(tm.getWarenty()));
    }

    public void saveItemOnAction(ActionEvent actionEvent) {
        String code=txtCode.getText();
        String name=txtName.getText();
        String qty=txtQty.getText();
        String brand=txtBrand.getText();
        String company=txtCompany.getText();
        String date= String.valueOf(dpInput.getValue());
        String description=txtDescription.getText();
        String  warrenty;
        if (tbtnWarranty.isSelected()){
            warrenty="true";
        }else{warrenty="false";}
        if (btnSave.getText().equalsIgnoreCase("Save Item")){

            PItemDTO dto = new PItemDTO(code,name,qty,brand,company,date,description,warrenty);

            try {
                if (new DataBaseAccessCode().savePItem(dto)){
                    new Alert(Alert.AlertType.INFORMATION, "Item Saved", ButtonType.OK).show();
                    loadAllPItem("");
                }else{
                    new Alert(Alert.AlertType.ERROR, "Item Save Error", ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            PItemDTO dto = new PItemDTO(code,name,qty,brand,company,date,description,warrenty);
            try {
                if (new DataBaseAccessCode().updatePItem(dto)){
                    new Alert(Alert.AlertType.INFORMATION, "Item Updated", ButtonType.OK).show();
                    loadAllPItem("");
                }else{
                    new Alert(Alert.AlertType.ERROR, "Item Update Error", ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void newItemOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Item");
        clearField();
    }
    public void clearField(){
        txtCode.clear();
        txtName.clear();
        txtBrand.clear();
        txtCompany.clear();
        txtDescription.clear();
        txtQty.clear();
        dpInput.getEditor().clear();
        tbtnWarranty.setDisable(false);
    }

    public void deleteItemOnAction(ActionEvent actionEvent) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Are you Shure?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> Confirmation = confirmation.showAndWait();
        if (Confirmation.get().equals(ButtonType.YES)){
            try {
                if (new DataBaseAccessCode().deletePItem(txtCode.getText())){
                    new Alert(Alert.AlertType.INFORMATION, "Item Delete", ButtonType.OK).show();
                    loadAllPItem("");
                }else{
                    new Alert(Alert.AlertType.ERROR, "Item Save Error", ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void loadAllPItem(String searchText){
        ObservableList<PItemTM> obList = FXCollections.observableArrayList();
        try {
            for (PItemDTO tempdto:new DataBaseAccessCode().getAllPItem("%"+searchText+"%")
                 ) {
                PItemTM pItemTM = new PItemTM(
                        tempdto.getCode(),
                        tempdto.getName(),
                        tempdto.getQty(),
                        tempdto.getBrand(),
                        tempdto.getCompany(),
                        tempdto.getDate(),
                        tempdto.getDescription(),
                        tempdto.getWarrenty()

                );
                obList.add(pItemTM);
            }
            tblPermanentInventory.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
