package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.system.DataBaseAccessCode;
import lk.system.dto.RoomDTO;
import lk.system.views.tm.RoomTM;

import java.sql.SQLException;

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

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        loadAllRooms("");
    }

    public void newRoomOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Room");
        txtId.clear();
        txtRoomName.clear();
        txtRoomType.clear();
        txtDescription.clear();
    }
    public void SaveRoomOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Save Room")) {
            RoomDTO dto = new RoomDTO(
                    txtId.getText(),
                    txtRoomName.getText(),
                    txtRoomType.getText(),
                    txtDescription.getText()
            );
            try {
                if (new DataBaseAccessCode().saveRoom(dto)) {
                    new Alert(Alert.AlertType.INFORMATION, "Room Saved", ButtonType.OK).show();
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
                    txtDescription.getText()
            );
            try {
                if (new DataBaseAccessCode().updateRoom(dto)){
                    new Alert(Alert.AlertType.INFORMATION,"Room Saved",ButtonType.OK).show();
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
            for (RoomDTO tempdto:new DataBaseAccessCode().getAllRoom("%"+text+"%")
                 ) {
                Button btn = new Button("Delete");
                RoomTM roomTM = new RoomTM(
                        tempdto.getId(),
                        tempdto.getName(),
                        tempdto.getType(),
                        tempdto.getDescription(),
                        btn
                );
                obList.add(roomTM);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
