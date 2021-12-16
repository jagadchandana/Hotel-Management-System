package lk.system.views.tm;

import javafx.scene.control.Button;

public class CItemTM {
    private String code;
    private String name;
    private int qty;
    private String brand;
    private String description;
    private Button btn;

    public CItemTM() {
    }

    public CItemTM(String code, String name, int qty, String brand, String description, Button btn) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.brand = brand;
        this.description = description;
        this.btn = btn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CItemTM{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", btn=" + btn +
                '}';
    }
}
