package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.PersonalDetailsDTO;
import lk.system.views.tm.PersonDetailsTM;

import java.sql.SQLException;

public class PersonFormController {
    public JFXComboBox cmbId;
    public TextField txtAdult;
    public TextField txtKids;
    public JFXTextArea txtPersonInfo;
    public JFXButton btnClose;
    public JFXButton btnSave;
    public TextField txtQty;

    public void initialize(){
        loadAllIds();
        cmbId.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                showDetails();
            }
        });
    }

    private void showDetails(){
       String id = String.valueOf(cmbId.getSelectionModel().getSelectedItem());
        try {
            PersonalDetailsDTO dto = new DataBaseAccessCode().getAllDetails(id);
            if (dto!=null){
                btnSave.setText("Update Info.");
                txtAdult.setText(String.valueOf(dto.getNumOfAdult()));
                txtKids.setText(String.valueOf(dto.getNumOfKids()));
                txtQty.setText(String.valueOf(dto.getRoomQty()));
                txtPersonInfo.setText(dto.getDetails());
            }else{
                btnSave.setText("Save Info.");
                txtAdult.clear();
                txtKids.clear();
                txtPersonInfo.clear();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   /* public void setData(PersonDetailsTM tm){
        txtAdult.setText(String.valueOf(tm.getNumOfAdult()));
        txtKids.setText(String.valueOf(tm.getNumOfKids()));
        txtPersonInfo.setText(tm.getDetails());
    }*/

    public void saveOnAction(ActionEvent actionEvent){
        PersonalDetailsDTO dto = new PersonalDetailsDTO(
                (String) cmbId.getSelectionModel().getSelectedItem(),
                Integer.valueOf(txtAdult.getText()),
                Integer.valueOf(txtKids.getText()),
                Integer.valueOf(txtQty.getText()),
                txtPersonInfo.getText()
        );
        if (btnSave.getText().equalsIgnoreCase("Save info.")){
            try {
                if(new DataBaseAccessCode().savePDetails(dto)){
                    new Alert(Alert.AlertType.INFORMATION,"Information Saved", ButtonType.OK).show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Information Save Error",ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
           /* PersonalDetailsDTO dto = new PersonalDetailsDTO(
                    cmbId.getId(),
                    Integer.valueOf(txtAdult.getText()),
                    Integer.valueOf(txtKids.getText()),
                    txtPersonInfo.getText()
            );*/
            try {
                if (new DataBaseAccessCode().updatePDetails(dto)){
                    new Alert(Alert.AlertType.INFORMATION,"Information updated",ButtonType.OK).show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Information Update Error",ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void loadAllIds(){
        try {
            for (String temp:new DataBaseAccessCode().getAllCustomerIds()
                 ) {
                cmbId.getItems().addAll(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public void loadAllDetails(String text) {

        try {
            PersonalDetailsDTO dto = new DataBaseAccessCode().getAllDetails(text);
            txtAdult.setText(String.valueOf(dto.getNumOfAdult()));
            txtKids.setText(String.valueOf(dto.getNumOfKids()));
            txtPersonInfo.setText(dto.getDetails());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }*/


    public void closeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

   }
