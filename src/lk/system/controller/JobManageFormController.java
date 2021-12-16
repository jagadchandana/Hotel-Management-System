package lk.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.system.DataBaseAccessCode;
import lk.system.dto.JobDTO;
import lk.system.views.tm.JobTM;

import javax.swing.*;
import java.sql.*;
import java.util.Optional;

public class JobManageFormController {
    public JFXTextField txtJobId;
    public JFXTextField txtHourRate;
    public JFXTextField txtMonthlyRate;
    public TableView<JobTM> tblJob;
    public TableColumn colJobId;
    public JFXTextArea txtDescription;
    public ComboBox cmbJobPosition;
    public TableColumn colJobPosition;
    public TableColumn colHourRate;
    public TableColumn colMonthlyRate;
    public TableColumn colDescription;
    public TableColumn colOption;
    public JFXButton btnSave;
    public JFXTextField txtJobPosition;
    public JFXButton btnClose;

    public void initialize(){
       colJobId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJobPosition.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
        colHourRate.setCellValueFactory(new PropertyValueFactory<>("hourRate"));
        colMonthlyRate.setCellValueFactory(new PropertyValueFactory<>("monthlyRate"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllJobs("");
        tblJob.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setData(newValue);
            }
        });
    }
    public void setData(JobTM tm){
        btnSave.setText("Update Job");
        txtJobId.setText(tm.getId());
        txtJobPosition.setText(tm.getJobPosition());
        txtHourRate.setText(String.valueOf(tm.getHourRate()));
        txtMonthlyRate.setText(String.valueOf(tm.getMonthlyRate()));
        txtDescription.setText(tm.getDescription());

    }

    public void newJobOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Job");
        clearField();
    }
    public void clearField(){
        txtJobId.clear();
        txtJobPosition.clear();
        txtDescription.clear();
        txtHourRate.clear();
        txtMonthlyRate.clear();

    }

    public void saveJobOnAction(ActionEvent actionEvent) {
        JobDTO dto = new JobDTO(
                txtJobId.getText(),
                txtJobPosition.getText(),
                Double.valueOf(txtHourRate.getText()),
                Double.valueOf(txtMonthlyRate.getText()),
                txtDescription.getText());
        if (btnSave.getText().equalsIgnoreCase("Save Job")){

            try {
                if (new DataBaseAccessCode().saveJob(dto)){
                    new Alert(Alert.AlertType.INFORMATION,"Job saved",ButtonType.OK).show();
                    loadAllJobs("");
                }else{
                    new Alert(Alert.AlertType.ERROR,"Job Save Eror",ButtonType.OK).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            try {
                if (new DataBaseAccessCode().updateJob(dto)){
                    new Alert(Alert.AlertType.INFORMATION,"Job Updated").show();
                    loadAllJobs("");
                }else{
                    new Alert(Alert.AlertType.ERROR,"Job update Error").show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void loadAllJobs(String text){
        ObservableList<JobTM> observableList = FXCollections.observableArrayList();
        try {
            for (JobDTO temp:new DataBaseAccessCode().getAllJobs("%"+text+"%")
                 ) {
                Button btn  = new Button("Delete");
                JobTM jobTM = new JobTM(
                        temp.getId(),
                        temp.getJobPosition(),
                        temp.gethRate(),
                        temp.getmRate(),
                        temp.getDescription(),
                        btn
                );
                observableList.add(jobTM);
                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you Shure?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> confirmState = alert.showAndWait();
                    if (confirmState.get().equals(ButtonType.YES)){
                        try {
                            if (new DataBaseAccessCode().deleteJob(temp.getId())){
                                new Alert(Alert.AlertType.INFORMATION,"Job Deleted",ButtonType.OK).show();
                                loadAllJobs("");
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Job Delet Error").show();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblJob.setItems(observableList);
    }

    public void exiteOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"Please ReStart Empolyee Menu fo Apply Changes").show();
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
