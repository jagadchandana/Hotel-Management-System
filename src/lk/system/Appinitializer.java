package lk.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.system.controller.LoginFormController;

import java.io.IOException;

public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("views/LoginForm.fxml"))));
        primaryStage.setX(80);
        primaryStage.setY(50);
        primaryStage.setTitle("Hotel Management");
        primaryStage.show();


    }
}
