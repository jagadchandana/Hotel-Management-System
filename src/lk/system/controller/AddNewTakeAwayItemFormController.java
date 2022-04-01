package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.bo.custom.TitemBO;
import lk.system.bo.custom.impl.TitemBoImpl;
import lk.system.dto.RoomDTO;
import lk.system.dto.TitemDTO;
import lk.system.views.tm.RoomTM;
import lk.system.views.tm.TItemTM;

import java.sql.SQLException;
import java.util.Optional;

public class AddNewTakeAwayItemFormController {
    public TextField txtSearch;
    TitemBO bo = new TitemBoImpl();

    public ImageView imgClose;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtUnitePrice;
    public TableView tblItem;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colUnitPrice;
    public TableColumn colOption;
    public JFXButton btnSave;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        loadAllItem("");
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllItem(newValue);
        });
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData((TItemTM) newValue);
            }
        });
        imgClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) imgClose.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void setData(TItemTM tm){
        btnSave.setText("Update Item");
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtUnitePrice.setText(String.valueOf(tm.getPrice()));
    }

    public void SaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        TitemDTO dto = new TitemDTO(txtId.getText(),txtName.getText(),Double.parseDouble(txtUnitePrice.getText()));
        if (btnSave.getText().equalsIgnoreCase("Save Item")){
            if (bo.saveTitem(dto)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Saved", ButtonType.OK).show();
                loadAllItem("");
                clearField();
            }else{
                new Alert(Alert.AlertType.ERROR,"Item Save Error").show();
            }
        }else {
            if (bo.updateTitem(dto)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Saved", ButtonType.OK).show();
                loadAllItem("");
                clearField();
                btnSave.setText("Updated Item");
            }else{
                new Alert(Alert.AlertType.ERROR,"Item Update Error").show();
            }
        }

    }
    public void loadAllItem(String text){
        ObservableList<TItemTM> obList = FXCollections.observableArrayList();
        try {
            for (TitemDTO tempdto:bo.getAllTitem("%"+text+"%")
            ) {
                Button btn = new Button("Delete");
                TItemTM tItemTM = new TItemTM(
                        tempdto.getId(),
                        tempdto.getName(),
                        tempdto.getPrice(),
                        btn
                );
                obList.add(tItemTM);
                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Shure?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> confirmState = alert.showAndWait();
                    if (confirmState.get().equals(ButtonType.YES)){
                        try {
                            if (bo.deleteTitem(tempdto.getId())){
                                new Alert(Alert.AlertType.INFORMATION,"Item Deleted",ButtonType.OK).show();
                                loadAllItem("");
                                clearField();
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Delete error",ButtonType.OK).show();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblItem.setItems(obList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearField(){
        txtId.clear();
        txtName.clear();
        txtUnitePrice.clear();
    }

    public void NewItemOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Item");
        clearField();
    }
}
