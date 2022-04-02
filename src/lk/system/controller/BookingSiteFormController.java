package lk.system.controller;

import javafx.scene.web.WebView;

public class BookingSiteFormController {
    public WebView webViewBooking;

        public void initialize(){
            webViewBooking.getEngine().load("https://www.booking.com/");
        }

}
