package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.CustomerDTO;
import lk.system.dto.PersonalDetailsDTO;
import lk.system.views.tm.CustomerTM;
import lk.system.views.tm.PersonDetailsTM;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;

public class PersonFormController {
    public JFXComboBox cmbId;
    public TextField txtAdult;
    public TextField txtKids;
    public JFXTextArea txtPersonInfo;
    public JFXButton btnClose;

    public void initialize(){
        loadAllIds();
    }
    public void setData(PersonDetailsTM tm){
        //txtAdult.setText(String.valueOf(tm.getNumOfAdult()));
       // txtKids.setText(String.valueOf(tm.getNumOfKids()));
        txtPersonInfo.setText(tm.getDetails());
    }

    public void saveOnAction(ActionEvent actionEvent){
        PersonalDetailsDTO dto = new PersonalDetailsDTO(
                cmbId.getId(),
                Integer.valueOf(txtAdult.getText()),
                Integer.valueOf(txtKids.getText()),
                txtPersonInfo.getText()
        );
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
    }

    public void updateOnAction(ActionEvent actionEvent) {
        PersonalDetailsDTO dto = new PersonalDetailsDTO(
                cmbId.getId(),
                Integer.valueOf(txtAdult.getText()),
                Integer.valueOf(txtKids.getText()),
                txtPersonInfo.getText()
        );
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
    public void loadAllDetails(String text){
       // ObservableList<PersonDetailsTM> obList = FXCollections.observableArrayList();
        ArrayList<PersonDetailsTM>obList = new ArrayList<>();
        try {

            for (PersonalDetailsDTO tempdto: new DataBaseAccessCode().getAllDetails("%"+text+"%")
            ) {
                PersonDetailsTM personDetailsTM = new PersonDetailsTM(
                        tempdto.getId(),
                        tempdto.getNumOfAdult(),
                        tempdto.getNumOfKids(),
                        tempdto.getDetails()
                );
                obList.add(personDetailsTM);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    public void closeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


}
