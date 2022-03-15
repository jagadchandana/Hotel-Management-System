package lk.system.controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {

    public AnchorPane DashBoardContextMenu;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane topBarUi;

    @FXML
        private ImageView btnClose;

        @FXML
        private Label lblMenu;

        @FXML
        private Label lblMenuBack;

        @FXML
        private AnchorPane slider;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setTopUi("TakeAwayForm");
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnClose.setOnMouseClicked(event -> {
            Alert confirmatio =new Alert(Alert.AlertType.CONFIRMATION,"Are You Shure", ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> Confirmation = confirmatio.showAndWait();
            if (Confirmation.get().equals(ButtonType.YES)){
                Platform.exit();
            }
        });
        slider.setTranslateX(-176);
        lblMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)->{
                lblMenu.setVisible(false);
                lblMenuBack.setVisible(true);
            });
        });

        lblMenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(-0);

            slide.setOnFinished((ActionEvent e)->{
                lblMenu.setVisible(true);
                lblMenuBack.setVisible(false);
            });
        });


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        lblDate.setText(dateFormat.format(date));
        DateFormat timeformat = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        lblTime.setText(timeformat.format(time));


    }

    private void menuBack(){

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(-0);

            lblMenu.setVisible(true);


    }


    private void setUi(String location) throws IOException {
        topBarUi.getChildren().clear();
        DashBoardContextMenu.getChildren().clear();
        DashBoardContextMenu.getChildren().add(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml")));

    }
    private void setTopUi(String location) throws IOException {
        topBarUi.getChildren().clear();
        DashBoardContextMenu.getChildren().clear();
        topBarUi.getChildren().add(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml")));
        menuBack();
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerForm");
    }

    public void EmployeeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("EmployeeForm");
    }

    public void salaryOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SalaryForm");
    }

    public void roomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RoomForm");
    }

    public void servicesOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ServicesForm");
    }

    public void inventoryOnAction(ActionEvent actionEvent) throws IOException {
        setUi("InventoryForm");
    }

    public void takeAwayOnAction(ActionEvent actionEvent) throws IOException {
        setTopUi("TakeAwayForm");
    }

    public void emailOnAction(ActionEvent actionEvent) throws IOException {
        setTopUi("EmailForm");
    }

    public void detailsOnAction(ActionEvent actionEvent) throws IOException {
        setTopUi("DetailsForm");
    }
}












