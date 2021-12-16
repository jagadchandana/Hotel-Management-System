package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.system.DataBaseAccessCode;
import lk.system.dto.CitemDTO;
import lk.system.views.tm.CItemTM;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Optional;

import static jdk.nashorn.internal.objects.NativeDate.setDate;

public class CacheImventoryFormController {
    public JFXTextField txtCode;
    public JFXTextField txtName;
    public JFXTextArea txtDescription;
    public TextField txtQuantity;
    public TableView tblCItem;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colBrand;
    public TableColumn colQuantity;
    public TableColumn colOption;
    public TextField txtSearch;
    public JFXTextField txtBrand;
    public JFXButton btnSave;

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllCItem("");
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllCItem(newValue);
        });
        tblCItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData((CItemTM) newValue);
            }
        });
    }
    public void setData(CItemTM tm){
        btnSave.setText("Update Item");
        txtCode.setText(tm.getCode());
        txtName.setText(tm.getName());
        txtQuantity.setText(String.valueOf(tm.getQty()));
        txtBrand.setText(tm.getBrand());
        txtDescription.setText(tm.getDescription());
    }

    public void saveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code=txtCode.getText();
        String name=txtName.getText();
        String brand=txtBrand.getText();
        int qty= Integer.parseInt(txtQuantity.getText());
        String description=txtDescription.getText();
        CitemDTO dto = new CitemDTO(code,name,qty,brand,description);

        if (btnSave.getText().equalsIgnoreCase("Save Item")){
            if (new DataBaseAccessCode().saveCacheItem(dto)){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved",ButtonType.OK).show();
                loadAllCItem("");
            }else {
                new Alert(Alert.AlertType.ERROR,"Item save Error",ButtonType.OK).show();
            }
        }else{
            if (new DataBaseAccessCode().updateCacheItem(dto)){
                new Alert(Alert.AlertType.INFORMATION,"Item Update",ButtonType.OK).show();
                loadAllCItem("");
            }else{
                new Alert(Alert.AlertType.ERROR,"Item Update Error",ButtonType.OK).show();
            }
        }
    }

    public void useItemOnAction(ActionEvent actionEvent) {
    }

    public void newItemOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Item");
        clearField();
    }
    public void clearField(){
        txtCode.clear();
        txtName.clear();
        txtQuantity.clear();
        txtBrand.clear();
        txtDescription.clear();
    }

    public void loadAllCItem(String text){
        ObservableList<CItemTM> observableList = FXCollections.observableArrayList();

        try {
            for (CitemDTO tempdto:new DataBaseAccessCode().getAllCItem("%"+text+"%")
                 ) {
                Button btn = new Button("Delete");
                CItemTM cItemTM = new CItemTM(
                        tempdto.getCode(),
                        tempdto.getName(),
                        tempdto.getQuantity(),
                        tempdto.getBrand(),
                        tempdto.getDescription(),
                        btn
                );
                observableList.add(cItemTM);
                btn.setOnAction(e->{
                    Alert confiAlert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Shure?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> comfirmState = confiAlert.showAndWait();
                    if (comfirmState.get().equals(ButtonType.YES)){

                        try {
                            if (new DataBaseAccessCode().deleteCacheItem(tempdto.getCode())){
                                new Alert(Alert.AlertType.INFORMATION,"Item Deleted",ButtonType.OK).show();
                                clearField();
                                loadAllCItem("");
                            }else {
                                new Alert(Alert.AlertType.ERROR,"Item Delete Error",ButtonType.OK).show();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                    }
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblCItem.setItems(observableList);
    }
}
