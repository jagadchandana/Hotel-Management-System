package lk.system.controller;

import com.sun.org.omg.CORBA.Initializer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DashBoardFormController {
    public AnchorPane dashBoardContextMenuUi;
    public WebView browser;
    public Label lblDate;
    public Label lblTime;

   public void initialize(){


       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        lblDate.setText(dateFormat.format(date));
        DateFormat timeformat = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        lblTime.setText(timeformat.format(time));

    }



    private void setUi(String location) throws IOException {
        dashBoardContextMenuUi.getChildren().clear();
        dashBoardContextMenuUi.getChildren().add(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml")));

    }

    public void btnMainBordOnAction(ActionEvent actionEvent) throws IOException {
        setUi("WebForm");
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerForm");
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("EmployeeForm");
    }

    public void btnSalaryOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SaleryManageForm");
    }

    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RoomForm");
    }

    public void btnServiceOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ServicesForm");
    }

    public void btnInventoryOnAction(ActionEvent actionEvent) throws IOException {
        setUi("InventoryForm");
    }
}
