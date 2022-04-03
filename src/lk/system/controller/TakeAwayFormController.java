package lk.system.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAttribute;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.system.bo.custom.TitemBO;
import lk.system.bo.custom.impl.TitemBoImpl;
import lk.system.dto.TitemDTO;
import lk.system.views.tm.TOrderTM;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;

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

    TitemBO tbo = new TitemBoImpl();
    TitemDTO sdto = new TitemDTO();
    public void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

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
                    addToCartOnAction();
                }
            }
                   /* Double price = Double.parseDouble(txtUnitPrice.getText())*Double.parseDouble(txtQty.getText());
                    TOrderTM tm = new TOrderTM();
                    tm.setName(String.valueOf(cmbItemName.getSelectionModel().getSelectedItem()));
                    tm.setUnitPrice(txtUnitPrice.getText());
                    tm.setQty(txtQty.getText());
                    tm.setPrice(String.valueOf(price));
                    tm.setBtn(new Button("Delete"));
                    Button btn = new Button("Delete");
                    //tblDetails.getItems().add(tm);
                   // System.out.println(tblDetails.getItems().size());
                   // TableCell cell =  (TableCell) tblDetails.queryAccessibleAttribute(AccessibleAttribute.CELL_AT_ROW_COLUMN,1,2);
                   // System.out.println(cell);
                    int qty = isAlareadyExite();
                    if (qty>0){
                        int newQty = qty+Integer.parseInt(txtQty.getText());
                        System.out.println(newQty);
                        double newPrice = newQty*Double.parseDouble(txtUnitPrice.getText());
                        System.out.println(newPrice);
                        tblDetails.edit(1,colQty);
                        setValue(1,1,newPrice);


                        System.out.println("same");
                    }else{
                        tblDetails.getItems().add(tm);
                        System.out.println("not");
                    }
                }
            }*/
        });

        loadAllItem();
    }
    public void setValue(int row, int col, Object val)
    {
        final TOrderTM selectedRow = (TOrderTM) tblDetails.getItems().get(row);
        final TableColumn<TOrderTM,?> selectedColumn = (TableColumn<TOrderTM, ?>) tblDetails.getColumns().get(col);
        // Lookup the propery name for this column
        final String propertyName =   ((PropertyValueFactory)selectedColumn.getCellValueFactory()).getProperty();
        try
        {
            // Use reflection to get the property
            final Field f = TOrderTM.class.getField(propertyName);
            final Object o = f.get(selectedRow);

            // Modify the value based on the type of property
            if (o instanceof SimpleStringProperty)
            {
                ((SimpleStringProperty)o).setValue(val.toString());
            }
            else if (o instanceof SimpleIntegerProperty)
            {

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private int isAlareadyExite(){
       int size = tblDetails.getItems().size();
        for (int i = 0; i < size; i++) {
            String str = String.valueOf(cmbItemName.getSelectionModel().getSelectedItem());
            String[] arr = str.split(":", 2);
            String code = arr[0];
            //  TOrderTM tm = tblDetails.getItems().get(i);
            String[] st = tblDetails.getItems().get(i).toString().split("'");
            String pt[] = tblDetails.getItems().get(i).toString().split("'");
            System.out.println("Qty ; "+st[5]);
            System.out.println( tblDetails.getItems().get(i).toString());
            System.out.println(st[1]);
            if (st[1].equals(str)) {
                return Integer.parseInt(st[5]);
            }
        }
                return 0;
    }

    ///////////////////////////////////////////////////////

    ObservableList<TOrderTM> obList = FXCollections.observableArrayList();

    public void addToCartOnAction() {

    double unitPrice = Double.parseDouble(txtUnitPrice.getText());
    int qtyForCustomer = Integer.parseInt(txtQty.getText());
    double total = unitPrice * qtyForCustomer;

    Button btn = new Button("Delete");
    String str = String.valueOf(cmbItemName.getSelectionModel().getSelectedItem());

    TOrderTM tm = new TOrderTM(
            str,
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
        System.out.println("-1 near retun");
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

class num{

}
