package lk.system.views.tm;

import javafx.scene.control.Button;

public class RoomTM {
    private String id;
    private String name;
    private String type;
    private Double price;
    private int qty;
    private String description;
    private Button btnDelete;

    public RoomTM() {
    }

    public RoomTM(String id, String name, String type, Double price, int qty, String description, Button btnDelete) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.qty = qty;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty(){ return qty;}

    public void setQty(){this.qty = qty;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "RoomTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", description='" + description + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
