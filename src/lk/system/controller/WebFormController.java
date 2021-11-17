package lk.system.controller;

import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class WebFormController {
    public WebView webView;
    public AnchorPane webContextUi;

    public void initialize(){
        webView.getEngine().load("https//:www.google.com");


        /*public void initialize(){
        browser.getEngine().load("https//:www.google.com");
    }
*/
    }
}
