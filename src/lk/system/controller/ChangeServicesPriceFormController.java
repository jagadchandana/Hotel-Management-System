package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.ClassPackageDTO;
import lk.system.dto.ServicesPackageDTO;

import java.sql.SQLException;

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

    public void initialize(){
        loadAllPrices();
    }
    public void updateOnAction(ActionEvent actionEvent) {
        Double luxury= Double.valueOf(txtLuxury.getText());
        Double semiLuxury= Double.valueOf(txtSemiLuxury.getText());
        Double classA= Double.valueOf(txtClassA.getText());
        Double classB= Double.valueOf(txtClassB.getText());
        Double classC= Double.valueOf(txtClassC.getText());
        Double normal= Double.valueOf(txtNormal.getText());
        Double permenent= Double.valueOf(txtPermanent.getText());
        Double food= Double.valueOf(txtFood.getText());
        Double bar= Double.valueOf(txtBar.getText());
        Double transport= Double.valueOf(txtTransport.getText());
        Double pool= Double.valueOf(txtPool.getText());
        Double kids= Double.valueOf(txtKindaPark.getText());
        Double beach= Double.valueOf(txtBeach.getText());
        ClassPackageDTO dto = new ClassPackageDTO(1,luxury,semiLuxury,classA,classB,classC,normal,permenent);
        ServicesPackageDTO sdto = new ServicesPackageDTO(1,food,bar,transport,pool,kids,beach);
        try {
            if ((new DataBaseAccessCode().updateCServices(dto)) && (new DataBaseAccessCode().updateSServices(sdto))){
                new Alert(Alert.AlertType.INFORMATION,"Price Changed", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Price Change Error",ButtonType.OK).show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadAllPrices(){
        try {
            ClassPackageDTO tempdto=new DataBaseAccessCode().getAllClassPrice(1);
            txtLuxury.setText(String.valueOf(tempdto.getLuxury()));
            txtSemiLuxury.setText(String.valueOf(tempdto.getSemiLuxury()));
            txtClassA.setText(String.valueOf(tempdto.getClassA()));
            txtClassB.setText(String.valueOf(tempdto.getClassB()));
            txtClassC.setText(String.valueOf(tempdto.getClassC()));
            txtNormal.setText(String.valueOf(tempdto.getNormal()));
            txtPermanent.setText(String.valueOf(tempdto.getPermenent()));

            ServicesPackageDTO dto = new DataBaseAccessCode().getAllServicesPrice(1);
            txtFood.setText(String.valueOf(dto.getFoodprice()));
            txtBar.setText(String.valueOf(dto.getBarPrice()));
            txtTransport.setText(String.valueOf(dto.getTransportPrice()));
            txtPool.setText(String.valueOf(dto.getPoolPrice()));
            txtKindaPark.setText(String.valueOf(dto.getKidsPrice()));
            txtBeach.setText(String.valueOf(dto.getBeachPrice()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public void closeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
