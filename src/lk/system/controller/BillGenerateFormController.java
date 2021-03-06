package lk.system.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.CustomerDTO;
import lk.system.dto.PersonalDetailsDTO;
import lk.system.dto.RoomDTO;
import lk.system.dto.ServicesDTO;
import lk.system.extra.PrintJob;
import lk.system.extra.TimeSet;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BillGenerateFormController {
    public JFXComboBox cmbCustomerId;
    public TextField txtServiceId;
    public TextField txtRoomId;
    public TextArea txtDetails;
    public Button btnClose;
    public ImageView imgClose;
    public TextField txtCName;
    long billDays;
    int numOfAdult;
    int numOfKids;
    double packagePrice;
    double roomPrice;
    int roomQty;
    double packageCost;
     public void initialize(){
         loadCustomerIds();
         cmbCustomerId.valueProperty().addListener(new ChangeListener() {
             @Override
             public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                 priceCount(String.valueOf(cmbCustomerId.getSelectionModel().getSelectedItem()));
             }
         });
         imgClose.setOnMouseClicked(event -> {
             Stage stage = (Stage) btnClose.getScene().getWindow();
             stage.close();
         });

     }

    private void priceCount(String id){
        try {
        CustomerDTO dto = new DataBaseAccessCode().getCustomer(id);
        System.out.println(dto.getOnDate());
            SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
            Date onDate = null;
            try {
                onDate = dtf.parse(dto.getOnDate());
                Date offDate = dtf.parse(dto.getOffDate());
                long days = offDate.getTime() - onDate.getTime();
                billDays = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
            } catch (ParseException e) {
                e.printStackTrace();
                }
        txtCName.setText(dto.getName());
        txtRoomId.setText(dto.getRoomId());
        txtServiceId.setText(dto.getServiceId());

        PersonalDetailsDTO pdto = new DataBaseAccessCode().getAllDetails(id);
            numOfAdult = pdto.getNumOfAdult();
            numOfKids = pdto.getNumOfKids();

        ServicesDTO sdto = new DataBaseAccessCode().getService(txtServiceId.getText());
            packagePrice = sdto.getPackagePrice();


        RoomDTO rdto = new DataBaseAccessCode().getRoom(txtRoomId.getText());
            roomPrice = rdto.getPrice();
            roomQty = rdto.getQty();

        packageCost = (((packagePrice*numOfAdult)+((packagePrice*0.25)*numOfKids))*billDays)+((billDays*roomPrice)*roomQty);//room qty

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void billPrintOnAction(ActionEvent actionEvent) {
        String s = txtDetails.getText();
        PrintJob p = new PrintJob();
        p.print(s);
    }

    public void BillOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO dto = new DataBaseAccessCode().getCustomer(String.valueOf(cmbCustomerId.getSelectionModel().getSelectedItem()));


         txtDetails.clear();
        txtDetails.setText("\t-----------------------------------\n"+"\t---Moana Hotel - Take Away---\n"+"-------------------------------"+"\n");
        txtDetails.setText(txtDetails.getText()+"Date:"+ TimeSet.getDate()+"\n"+"Time:"+ TimeSet.getTime()+"\n\n");
         txtDetails.setText(txtDetails.getText()+"================\n"+" \n"+"Service Id-"+txtServiceId.getText()+"\n"+"Services Price-"+packagePrice+"\n"
            +"Room Id-"+txtRoomId.getText()+"\n"+"Room Price-"+roomPrice+"\n"+"Rented Date: \n"+dto.getOnDate()+"\tTime: "+dto.getOnTime()+"\n"+"Rent Out Date:\n"+dto.getOffDate()+"\tTime: "+dto.getOffTime()+"\nRented Days-"+billDays+"\n"+"-----------\n"
            +"Total Price-"+packageCost+"\n----------");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void undoOnAction(ActionEvent actionEvent) {
    }
    private void loadCustomerIds(){
        try {
            for (String tempid:new DataBaseAccessCode().getAllCustomerIds()
            ) {
                cmbCustomerId.getItems().addAll(tempid);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadDetails(){

    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
