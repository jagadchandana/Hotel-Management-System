package lk.system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane dashBoardContextMenuUi;
    public WebView browser;
    public Label lblDate;
    public Label lblTime;



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
