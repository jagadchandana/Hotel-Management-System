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
import lk.system.DataBaseAccessCode;
import lk.system.bo.custom.TitemBO;
import lk.system.bo.custom.impl.TitemBoImpl;
import lk.system.dto.TitemDTO;

import java.io.IOException;
import java.sql.SQLException;

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
    public TextField txtId;

    TitemBO tbo = new TitemBoImpl();
    TitemDTO sdto = new TitemDTO();
    public void initialize(){

        cmbItemName.valueProperty().addListener(observable -> {
            String str = String.valueOf(cmbItemName.getSelectionModel().getSelectedItem());
            String[] arr=str.split(":",2);
            try {
                sdto = tbo.getTitem(arr[0]);
                txtUnitPrice.setText(String.valueOf(sdto.getPrice()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        txtQty.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    Double price = Double.parseDouble(txtUnitPrice.getText())*Double.parseDouble(txtQty.getText());
                    System.out.println(price);
                }
            }
        });
        loadAllItem();
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
    }
    private void loadAllItem(){
        try {

            for (TitemDTO dto: tbo.getAllTitem("")
            ) {
                cmbItemName.getItems().addAll(dto.getId()+": "+dto.getName());

            }
           /* for (String tempid:new DataBaseAccessCode().loadAllServies()
            ) {
                cmbServiceId.getItems().addAll(tempid);
            }
            new DataBaseAccessCode().loadAllRoomIds();*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
