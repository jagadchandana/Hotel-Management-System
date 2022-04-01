package lk.system.views.tm;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;



public class TItemTM {
    private String id;
    private String name;
    private double price;
    private Button btnDelete;

    public TItemTM() {
    }

    public TItemTM(String id, String name, double price, Button btnDelete) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.btnDelete = btnDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "TItemTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
