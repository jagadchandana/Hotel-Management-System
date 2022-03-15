package lk.system.controller;

import javafx.scene.web.WebView;

public class EmailFormController {
    public WebView webViewEmail;

    public void initialize(){
        webViewEmail.getEngine().load("https://www.google.com");
    }
}
