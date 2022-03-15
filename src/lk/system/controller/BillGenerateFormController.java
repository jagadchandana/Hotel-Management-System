package lk.system.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.CustomerDTO;
import lk.system.dto.PersonalDetailsDTO;
import lk.system.dto.RoomDTO;
import lk.system.dto.ServicesDTO;
import sun.util.resources.cldr.ti.CalendarData_ti_ER;

import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.OutputStream;
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
    public Button btnClose;
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


        packageCost = (((packagePrice*numOfAdult)+(packagePrice*0.25*numOfKids))*billDays)+((billDays*roomPrice)*roomQty);//room qty

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void billPrintOnAction(ActionEvent actionEvent) {
       /* String portName = "/dev/ttyS4";
        Integer baudrate = 57600;
        Integer timeout = 1000;

        SerialPort serialPort = (SerialPort)CommPortIdentifier.getPortIdentifier(portName).open(POSPrintDemo.class.getName(), 1000);
        serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        serialPort.enableReceiveTimeout(timeout);

        try(OutputStream os = serialPort.getOutputStream()) {
            // select double width and height font
            try {
                os.write(new byte[] {0x1b, 0x21, 0x31});
            } catch (IOException e) {
                e.printStackTrace();
            }

            os.write("       AROMA CAFE\n".getBytes());
            os.write("   1211 Green Street\n".getBytes());
            os.write("      New York, NY\n".getBytes());

            // select normal font
            os.write(new byte[] {0x1b, 0x21, 0x01});

            os.write("03-12-2016       1:11PM\n".getBytes());
            os.write("TBL 1            HOST ALISON\n".getBytes());
            os.write("VISA ######8281\n".getBytes());
            os.write("\n".getBytes());
            os.write("QTY  DESC                              AMT\n".getBytes());
            os.write("----------------------------------------------\n".getBytes());
            os.write("1   GINGER CARROT SOUP                   $6.79\n".getBytes());
            os.write("1   HOUSE SALAD                          $7.69\n".getBytes());
            os.write("1   SURF AND RUTF - 1 PERS              $48.79\n".getBytes());
            os.write("1   WINE - GLASS - FIXE                 $11.50\n".getBytes());
            os.write("1   CHOC CAKE                            $6.75\n".getBytes());
            os.write("\n".getBytes());

            // select double width and height font
            os.write(new byte[] {0x1b, 0x21, 0x31});
            os.write("    AMOUNT    $90.52\n".getBytes());

            os.write(new byte[] {0x1b, 0x21, 0x01});
            os.write("\n".getBytes());
            os.write("        SUB-TOTAL           $81.52\n".getBytes());
            os.write("        TAX                  $9.00\n".getBytes());
            os.write("        BALANCE             $90.52\n".getBytes());
            os.write("\n".getBytes());
            os.write("\n".getBytes());
            os.write("\n".getBytes());

            // center text
            os.write(new byte[] {0x1b, 0x61, 0x31});

            // set barcode height to 80px
            os.write(new byte[] {0x1d, 0x68, 0x50});

            // print CODE39 with text TEST
            os.write(new byte[] {0x1d, 0x6b, 0x45, 0x04, 'T', 'E', 'S', 'T'});
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void BillOnAction(ActionEvent actionEvent) {

         txtDetails.setText("================\n"+"Service Id-"+txtServiceId.getText()+"\n"+"Services Price-"+packagePrice+"\n"
            +"Room Id-"+txtRoomId.getText()+"\n"+"Room Price-"+roomPrice+"\n"+"Rented Days-"+billDays+"\n"+"-----------\n"
            +"Total Price-"+packageCost+"\n----------");
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
