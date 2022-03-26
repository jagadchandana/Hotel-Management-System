package lk.system.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="room")
public class Room extends SuperEntity{
    @Id
    private String id;
    private String name;
    private String type;
    private double price;
    private int qty;
    private String description;

    public Room() {
    }

    public Room(String id, String name, String type, double price, int qty, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.qty = qty;
        this.description = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", description='" + description + '\'' +
                '}';
    }
}
