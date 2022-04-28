package lk.system.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane LoginFormContext;
    public JFXTextField txtId;
    public JFXPasswordField txtPasword;
    public Label lblInvalid;
    public JFXProgressBar pBar;
    public ProgressIndicator pCircle;
    public JFXPasswordField txtPW;
    public Label lblFor;
    public ImageView btnClose;

    public void initialize(){

        lblFor.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) LoginFormContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ForgotPaswordForm.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        btnClose.setOnMouseClicked(event -> {
            Platform.exit();
        });


//ENTER key pressed -> focus on next item
      /*  fName.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
            case ENTER:lName.requestFocus();}});
        lName.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
            case ENTER:birthDay.requestFocus();}});
        birthDay.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
            case ENTER:address.requestFocus();}});
        address.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
            case ENTER:city.requestFocus();}});
        city.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
            case ENTER:telephoneNmbr.requestFocus();}});
        telephoneNmbr.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
            case ENTER:email.requestFocus();}});
        email.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
            case ENTER:okBtn.requestFocus();}});

        //ENTER key pressed on okBtn-> Save info
        okBtn.setOnKeyPressed((KeyEvent event) ->{ switch (event.getCode()){
            case ENTER:try {okDugmeKlik(); *//*method for saving data into database*//*} catch (IOException e) {e.printStackTrace();}}});
*/
    }

    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 1; -fx-");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 1; -fx-border-radius: 5;");
    /*public void initialize(){
        txtId.textProperty().addListener(event -> {
            System.out.println("Changed");
            txtId.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !textField.getText().isEmpty() &&
                            !textField.getText().matches("(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
            );
        });
    }*/








    public void LoginOnAction(ActionEvent actionEvent) throws IOException {



       /* if (txtId.getText().isEmpty() || txtPasword.getText().isEmpty()) {
            lblInvalid.setStyle(errorMessage);

// When the username and password are blank
            if (txtId.getText().isEmpty() || txtPasword.getText().isEmpty()) {
                lblInvalid.setText("The Login fields are required!");
                txtId.setStyle(errorStyle);
                txtPasword.setStyle(errorStyle);


            } else // When only the username is blank
                if (txtId.getText().isEmpty()) {
                    txtId.setStyle(errorStyle);
                    lblInvalid.setText("The Username or Email is required!");
                    txtPasword.setStyle(successStyle);


                } else // When only the password is blank
                    if (txtPasword.getText().isEmpty()) {
                        txtPasword.setStyle(errorStyle);
                        lblInvalid.setText("The Password is required!");
                        txtId.setStyle(successStyle);


                    }
        } else // Check if password is less than four characters, if so display error message
            if (txtPasword.getText().length() < 9) {
                lblInvalid.setText("The Password can't be less than 4 characters!");
                lblInvalid.setStyle(errorMessage);
                txtPasword.setStyle(errorStyle);


            }
// If all login details are entered as required then display success message
            else {
               *//* invalidDetails.setText("Login Successful!");
                invalidDetails.setStyle(successMessage);
                usernameTextField.setStyle(successStyle);
                userPassword.setStyle(successStyle);
*//*
*/



        Stage stage = (Stage) LoginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DashBoardForm.fxml"))));
            }


    public void PreSignUpOnAction(ActionEvent actionEvent) throws IOException {

    }
}
