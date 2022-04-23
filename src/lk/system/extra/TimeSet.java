package lk.system.extra;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeSet {
    public static String getDate(){
       return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
    }
    public static String getTime() {
       return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }




}
