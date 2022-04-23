package lk.system.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.AccessibleAttribute;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import lk.system.bo.custom.TitemBO;
import lk.system.bo.custom.impl.TitemBoImpl;
import lk.system.dto.TitemDTO;
import lk.system.views.tm.TOrderTM;
import sun.misc.resources.Messages;

import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

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
    public Label lblTotal;
    public TextField txtCode;
    public TextField txtName;

    TitemBO tbo = new TitemBoImpl();
    TitemDTO sdto = new TitemDTO();
    public void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        txtCode.textProperty().addListener((observable, oldValue, newValue) -> {
            loadItem(newValue);
        });



        cmbItemName.valueProperty().addListener(observable -> {
            String str = String.valueOf(cmbItemName.getSelectionModel().getSelectedItem());
            String[] arr=str.split(":",2);
            try {
                sdto = tbo.getTitem(arr[0]);
                txtUnitPrice.setText(String.valueOf(sdto.getPrice()));
                txtQty.clear();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        txtQty.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    boolean empty = cmbItemName.getValue()==null;
                    if (txtQty.getText().matches("\\d+") && txtQty.getText()!=("") && !empty){
                        tableLoad();
                    }else{
                        new Alert(Alert.AlertType.WARNING,"Enter Valid Quntity Or Not Select Item",ButtonType.OK).show();
                    }

                }
            }
        });
        loadAllItem();
    }
    ///////////////////////////////////////////////////////

    ObservableList<TOrderTM> obList = FXCollections.observableArrayList();

    public void tableLoad() {

    double unitPrice = Double.parseDouble(txtUnitPrice.getText());
    int qtyForCustomer = Integer.parseInt(txtQty.getText());
    double total = unitPrice * qtyForCustomer;
    Button btn = new Button("Delete");
    String code = String.valueOf(cmbItemName.getSelectionModel().getSelectedItem());

   TOrderTM tm = new TOrderTM(
            code,
            unitPrice,
            qtyForCustomer,
            total,
            btn
    );

        btn.setOnAction((e) -> {
        obList.remove(tm);
        tblDetails.refresh();
        calculateTotal();
    });
    int rowNumber = isExists(tm);
        if (rowNumber == -1) {
        if (Integer.parseInt(txtQty.getText()) >= qtyForCustomer) {
            obList.add(tm);
            tblDetails.setItems(obList);
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
        }
    } else {
        if (Integer.parseInt(txtQty.getText()) >= obList.get(rowNumber).getQty()){
            obList.get(rowNumber).setQty(obList.get(rowNumber).getQty() + qtyForCustomer);
            obList.get(rowNumber).setPrice(obList.get(rowNumber).getPrice() + total);
            tblDetails.refresh();
        } else {
            obList.get(rowNumber).setQty(qtyForCustomer);
            obList.get(rowNumber).setPrice(total);
            tblDetails.refresh();
        }
    }
    calculateTotal();
}
////////////////////////////////////////////

    private int isExists(TOrderTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (obList.get(i).getName().equals(tm.getName())) {
                return i;
            }
        }
        return -1;
    }

    double totalCost = 0.0;
    private void calculateTotal() {
        totalCost = 0;
        for (TOrderTM temp : obList
        ) {
            totalCost += temp.getPrice();
        }
        lblTotal.setText(": "+totalCost+" LKR");
    }

    /////////////////////////////////////////////////






    public void showBillOnAction(ActionEvent actionEvent) {

        if((txtCash.getText() != ("")) && ((txtCash.getText().matches("\\d+\\.\\d+") || (txtCash.getText().matches("\\d+"))))){
            txtDetails.clear();
            txtDetails.setText("\t-----------------------------------\n"+"\t---Moana Hotel - Take Away---\n"+"-------------------------------"+"\n"+"  Item\t"+"|Qty\t"+"|QtyPrice\t"+"|Price\n\n");
            for (int i = 0; i < obList.size(); i++) {
                obList.get(i).getName();
                obList.get(i).getUnitPrice();
                txtDetails.setText(txtDetails.getText()+"#"+(i+1)+". "+obList.get(i).getName()+"|\t"+obList.get(i).getQty()+"|\t"+ obList.get(i).getUnitPrice()+"|\t"+obList.get(i).getPrice()+"\n");
            }
            double balance = (Double.parseDouble(txtCash.getText()) - totalCost);
            if (balance<0){
                new Alert(Alert.AlertType.INFORMATION,"Balance is Minace"+balance,ButtonType.OK).show();
            }
            txtDetails.setText(txtDetails.getText()+"--------------\n"+"Total  "+lblTotal.getText()+"\n"+"================\n"+"Cash  :  "+txtCash.getText()+"\n"+"--------------\n"+"Balance : "+balance+"\n===========\n");

        }else{
            new Alert(Alert.AlertType.WARNING,"Please Enter Valid Cash..",ButtonType.OK).show();
        }

    }

    public void printBillOnAction(ActionEvent actionEvent) {
        String s = txtDetails.getText();
        PrintJob p = new PrintJob();
        p.print(s);

        }

    //=================================

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
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    private void loadItem(String text){

        try {
           TitemDTO  dto =tbo.getTitem(text);
           if (dto==null){
               clearText();
           }
            txtName.setText(dto.getName());
            txtUnitPrice.setText(String.valueOf(dto.getPrice()));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    private void clearText(){
        txtName.clear();
        txtUnitPrice.clear();
        txtQty.clear();
    }
}




