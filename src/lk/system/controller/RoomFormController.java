package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.system.DataBaseAccessCode;
import lk.system.bo.custom.RoomBO;
import lk.system.bo.custom.impl.RoomBoImpl;
import lk.system.dto.RoomDTO;
import lk.system.views.tm.RoomTM;

import java.sql.SQLException;
import java.util.Optional;

public class RoomFormController {
    public JFXTextField txtId;
    public JFXTextField txtRoomName;
    public TableView tblRoom;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colType;
    public TableColumn colDescription;
    public TableColumn colOption;
    public JFXTextField txtDescription;
    public JFXButton btnSave;
    public JFXTextField txtRoomType;
    public TextField txtSearch;
    public JFXTextField txtPrice;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TextField txtQty;

    RoomBO bo = new RoomBoImpl();

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        loadAllRooms("");
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadAllRooms(newValue);
        });
        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData((RoomTM) newValue);
            }
        });
    }

    public void setData(RoomTM tm){
        btnSave.setText("Update Room");
        txtId.setText(tm.getId());
        txtRoomName.setText(tm.getName());
        txtRoomType.setText(tm.getType());
        txtPrice.setText(String.valueOf(tm.getPrice()));
        txtQty.setText(String.valueOf(tm.getQty()));
        txtDescription.setText(tm.getDescription());
    }

    public void newRoomOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Room");
        clearField();
    }
    public void clearField(){
        txtId.clear();
        txtRoomName.clear();
        txtRoomType.clear();
        txtDescription.clear();
        txtPrice.clear();
        txtQty.clear();
    }
    public void SaveRoomOnAction(ActionEvent actionEvent) {
        Double price= Double.valueOf(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        if (btnSave.getText().equalsIgnoreCase("Save Room")) {
            RoomDTO dto = new RoomDTO(
                    txtId.getText(),
                    txtRoomName.getText(),
                    txtRoomType.getText(),
                     price,
                    qty,
                    txtDescription.getText()
            );
            try {
                if (bo.saveRoom(dto)) {
                    new Alert(Alert.AlertType.INFORMATION, "Room Saved", ButtonType.OK).show();
                    loadAllRooms("");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Room Nt Save", ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            RoomDTO dto = new RoomDTO(
                    txtId.getText(),
                    txtRoomName.getText(),
                    txtRoomType.getText(),
                   price,
                    qty,
                    txtDescription.getText()
            );
            try {
                if (bo.updateRoom(dto)){
                    new Alert(Alert.AlertType.INFORMATION,"Room Saved",ButtonType.OK).show();
                    loadAllRooms("");
                }else{
                    new Alert(Alert.AlertType.ERROR,"Room Save Error",ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void loadAllRooms(String text){
        ObservableList<RoomTM> obList = FXCollections.observableArrayList();
        try {
            for (RoomDTO tempdto:bo.getAllRoom("%"+text+"%")
                 ) {
                Button btn = new Button("Delete");
                RoomTM roomTM = new RoomTM(
                        tempdto.getId(),
                        tempdto.getName(),
                        tempdto.getType(),
                        tempdto.getPrice(),
                        tempdto.getQty(),
                        tempdto.getDescription(),
                        btn
                );
                obList.add(roomTM);
                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Shure?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> confirmState = alert.showAndWait();
                    if (confirmState.get().equals(ButtonType.YES)){
                        try {
                            if (bo.deleteRoom(tempdto.getId())){
                                new Alert(Alert.AlertType.INFORMATION,"Room Deleted",ButtonType.OK).show();
                                loadAllRooms("");
                                clearField();
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Delete eroror",ButtonType.OK).show();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblRoom.setItems(obList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
