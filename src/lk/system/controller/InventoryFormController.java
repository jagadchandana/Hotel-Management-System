package lk.system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InventoryFormController {
    public AnchorPane InventoryUiContext;
    public AnchorPane ParentInventoryUIContext;

    public void initialize() throws IOException {
       setUi("PermenentIForm");
    }

    private void setUi(String loacation) throws IOException {

        ParentInventoryUIContext.getChildren().clear();
        ParentInventoryUIContext.getChildren().add(FXMLLoader.load(getClass().getResource("../views/"+loacation+".fxml")));

    }


    public void permenentIOnAction(ActionEvent actionEvent) throws IOException {
        setUi("PermenentIForm");
    }

    public void CacheIOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CacheInventoryForm");
    }
}
