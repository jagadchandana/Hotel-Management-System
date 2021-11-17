package lk.system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane LoginFormContext;


    public void LoginOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) LoginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DashBoardForm.fxml"))));
    }

    public void PreSignUpOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) LoginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/SignupForm.fxml"))));

    }
}
