package lk.system.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.system.DataBaseAccessCode;
import lk.system.dto.CustomerDTO;
import lk.system.dto.PersonalDetailsDTO;
import lk.system.dto.RoomDTO;
import lk.system.dto.ServicesDTO;
import sun.util.resources.cldr.ti.CalendarData_ti_ER;

import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BillGenerateFormController {
    public JFXComboBox cmbCustomerId;
    public TextField txtServiceId;
    public TextField txtRoomId;
    public TextArea txtDetails;
    long billDays;
     public void initialize(){
         loadCustomerIds();
         cmbCustomerId.valueProperty().addListener(new ChangeListener() {
             @Override
             public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                 System.out.println("kkkkk");
             }
         });
         //cmbCustomerId.addItemListner(new ItemListener())
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


        PersonalDetailsDTO pdto = new DataBaseAccessCode().getAllDetails(id);
        int numOfAdult = pdto.getNumOfAdult();
        int numOfKids = pdto.getNumOfKids();

        ServicesDTO sdto = new DataBaseAccessCode().getService(id);
        double packagePrice = sdto.getPackagePrice();

        RoomDTO rdto = new DataBaseAccessCode().getRoom("R001");
        double roomPrice = rdto.getPrice();
            System.out.println(roomPrice);

        double packageCost = (((packagePrice*numOfAdult)+(packagePrice*0.5*numOfKids))*billDays)+(billDays*roomPrice);//room qty

            System.out.println(packageCost);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void billPrintOnAction(ActionEvent actionEvent) {
    }

    public void BillOnAction(ActionEvent actionEvent) {
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
}
