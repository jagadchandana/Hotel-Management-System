package lk.system.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.system.views.tm.JobTM;

import javax.swing.*;
import java.sql.*;
import java.util.Optional;

public class JobManageFormCobtroller {
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

    public void initialize(){
       colJobId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJobPosition.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
        colHourRate.setCellValueFactory(new PropertyValueFactory<>("hourRate"));
        colMonthlyRate.setCellValueFactory(new PropertyValueFactory<>("monthlyRate"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllCustomer(" ");
        loadComboBox();
    }

    public void newJobOnAction(ActionEvent actionEvent) {
    }

    public void saveJobOnAction(ActionEvent actionEvent) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
            String SQL = "INSERT INTO Job VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,txtJobId.getText());
            statement.setObject(2,cmbJobPosition.getItems());
            statement.setObject(3,txtHourRate.getText());
            statement.setObject(4,txtMonthlyRate.getText());
            statement.setObject(5,txtDescription.getText());
            boolean isSaved = statement.executeUpdate() > 0;
            if (isSaved)new Alert(Alert.AlertType.CONFIRMATION,"New Job is Saved", ButtonType.OK).show();
            else new Alert(Alert.AlertType.WARNING,"Job Save Error",ButtonType.CLOSE).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadAllCustomer(String searchText){
        ObservableList<JobTM> observableList = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
            String SQL = "SELECT * FROM job WHERE Job_Id LIKE ? OR Job_Position LIKE ? OR Description LIKE ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
           statement.setObject(1,"%"+searchText+"%");
           statement.setObject(2,"%"+searchText+"%");
           statement.setObject(3,"%"+searchText+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Button btn = new Button("Delete");
                observableList.add(new JobTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        btn

                ));

                btn.setOnAction(e -> {

                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you shure?", ButtonType.YES, ButtonType.CLOSE);
                    Optional<ButtonType> comfermationState = confirmation.showAndWait();
                    try {
                        if (comfermationState.get().equals(ButtonType.YES)) {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
                            String SQL1 = "DELETE FROM job WHERE ?";
                            PreparedStatement statement1 = connection1.prepareStatement(SQL1);
                            statement1.setObject(1, resultSet.getString(1));
                            boolean isDeleted = statement1.executeUpdate() > 0;
                            if (isDeleted) new Alert(Alert.AlertType.CONFIRMATION, "Job Deleted", ButtonType.OK).show();
                            new Alert(Alert.AlertType.WARNING, "Delete Error", ButtonType.OK).show();
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                });
            }
            tblJob.setItems(observableList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadComboBox(){
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/hotel_db", "root", "mysql");
        String SQL = "SELECT Job_Position FROM job";
        PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                FXCollections.observableArrayList().add(set.getString(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        cmbJobPosition.setItems((FXCollections.observableArrayList()));
    }
}
