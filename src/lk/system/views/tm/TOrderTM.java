package lk.system.views.tm;

import javafx.scene.control.Button;

public class TOrderTM {
    private String name;
    private double unitPrice;
    private int qty;
    private double price;
    private Button btn;

    public TOrderTM() {
    }

    public TOrderTM(String name, double unitPrice, int qty, double price, Button btn) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.price = price;
        this.btn = btn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
