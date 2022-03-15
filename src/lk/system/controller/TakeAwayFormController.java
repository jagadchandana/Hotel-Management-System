package lk.system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TakeAwayFormController {

    public JFXComboBox cmbItemName;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public JFXTextArea txtDetails;
    public TableView tblDetails;
    public TableColumn colName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableColumn colOption;
    public TextField txtCash;

    public void initialize(){
        txtCash.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    ///////////////
                }
            }
        });
    }

    public void showBillOnAction(ActionEvent actionEvent) {
    }

    public void printBillOnAction(ActionEvent actionEvent) {
    }

    public void newItemOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/AddNewTakeAwayItemForm.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Add New Item");
        stage.setScene(new Scene(root));
        stage.show();
        System.out.println("lllllll");
    }
}
