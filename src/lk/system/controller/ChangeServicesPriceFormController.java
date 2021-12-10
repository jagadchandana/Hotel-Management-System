package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class ChangeServicesPriceFormController {

    public JFXTextField txtFood;
    public JFXTextField txtBar;
    public JFXTextField txtTransport;
    public JFXTextField txtPool;
    public JFXTextField txtKindaPark;
    public JFXTextField txtBeach;
    public JFXTextField txtLuxury;
    public JFXTextField txtSemiLuxury;
    public JFXTextField txtClassA;
    public JFXTextField txtClassB;
    public JFXTextField txtClassC;
    public JFXTextField txtNormal;
    public JFXTextField txtPermanent;
    public JFXButton btnClose;

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void closeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
