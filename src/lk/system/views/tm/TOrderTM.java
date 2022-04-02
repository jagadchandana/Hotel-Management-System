package lk.system.views.tm;

import javafx.scene.control.Button;

public class TOrderTM {
    private String name;
    private String unitPrice;
    private String qty;
    private String price;
    private Button btn;

    public TOrderTM() {
    }

    public TOrderTM(String name, String unitPrice, String qty, String price, Button btn) {
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "TOrderTM{" +
                "name='" + name + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                ", btn=" + btn +
                '}';
    }
}
