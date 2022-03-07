package lk.system.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane LoginFormContext;


    public void LoginOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) LoginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DashBoardForm.fxml"))));
       /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/DashBoardForm.fxml"));
        Parent root = null;
        root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Price Change");
        stage.setScene(new Scene(root));
        root.getStylesheets().add(String.valueOf(getClass().getResource("../assert/Stylesheet.css")));*/
        stage.show();


    }

    public void PreSignUpOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) LoginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/SignupForm.fxml"))));

    }
}
