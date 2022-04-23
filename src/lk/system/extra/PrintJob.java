package lk.system.extra;

import com.jfoenix.controls.JFXTextArea;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrintJob {
    public void print(String text) {
        DialogPane d = new DialogPane();
        Scene s = new Scene(d);

        PrinterJob job = PrinterJob.createPrinterJob();

        if (job == null) {
            System.out.println("Error");
            return;
        }

        boolean proseed = job.showPrintDialog(d.getScene().getWindow());

        JobSettings ss1 = job.getJobSettings();

        PageLayout pageLayout1 = ss1.getPageLayout();

        double pgW1 = pageLayout1.getPrintableWidth();
        double pgH1 = pageLayout1.getPrintableHeight();

        TextArea tempTxtArea = new TextArea(text);
        Image image = new Image("lk/system/assert/close.png");
        Node n = tempTxtArea;
        ImageView im = new ImageView(image);
        tempTxtArea.setPrefSize(250, 200);
        tempTxtArea.setWrapText(true);
        //tempTxtArea.setId("tempScroolBar");

        if (proseed) {
            /*boolean printed =*/ job.printPage(tempTxtArea);
            boolean printed = job.printPage(n);

            if (printed) {
                job.endJob();
            } else {
                System.out.println("Fail");
            }
        }
    }
}
