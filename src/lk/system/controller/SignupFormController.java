package lk.system.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SignupFormController {
    public AnchorPane SignupFormContext;
    public JFXTextField txtUserId;
    public JFXPasswordField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtRePassword;
    public JFXPasswordField txtJobPosition;

    public void SignUpFormOnAction(ActionEvent actionEvent) throws IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
            String SQL = "INSERT INTO user VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,txtUserId.getText());
            statement.setObject(2,txtUserName.getText());
            statement.setObject(3,txtJobPosition.getText());
            statement.setObject(4,txtPassword.getText());

            boolean isSaved = statement.executeUpdate()>0;
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Was Saved", ButtonType.OK).show();
                Stage stage = (Stage) SignupFormContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LoginForm.fxml"))));
            } else new Alert(Alert.AlertType.WARNING,"User not saved",ButtonType.CANCEL).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
