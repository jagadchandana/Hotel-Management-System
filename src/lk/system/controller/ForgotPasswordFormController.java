package lk.system.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Base64;

import static javafx.scene.input.KeyCode.Z;

public class ForgotPasswordFormController {
    public AnchorPane SignupFormContext;
    public JFXTextField txtUserId;
    public JFXPasswordField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtRePassword;
    public JFXPasswordField txtJobPosition;
    public JFXTextField txtFullName;
    public JFXPasswordField txtCode1;
    public JFXPasswordField txtCode2;
    public Label lblRemember;
    public AnchorPane RememberFormContext;
    public ImageView imgClose;

    public void initialize() {
        lblRemember.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) RememberFormContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LoginForm.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        imgClose.setOnMouseClicked(event -> {
            Platform.exit();
        });
    }

    public void SignUpFormOnAction(ActionEvent actionEvent) throws IOException {

    }




}


