package lk.system.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="services")
public class Services extends SuperEntity {
    @Id
    private String id;
    private String name;
    private String type;
    private double food;
    private double bar;
    private double transport;
    private double pool;
    private double kidsPark;
    private double beach;

    public Services() {
    }

    public Services(String id, String name, String type, double food, double bar, double transport, double pool, double kidsPark, double beach, double packagePrice) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.food = food;
        this.bar = bar;
        this.transport = transport;
        this.pool = pool;
        this.kidsPark = kidsPark;
        this.beach = beach;
        this.packagePrice = packagePrice;
    }

    private double packagePrice;

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

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getBar() {
        return bar;
    }

    public void setBar(double bar) {
        this.bar = bar;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public double getPool() {
        return pool;
    }

    public void setPool(double pool) {
        this.pool = pool;
    }

    public double getKidsPark() {
        return kidsPark;
    }

    public void setKidsPark(double kidsPark) {
        this.kidsPark = kidsPark;
    }

    public double getBeach() {
        return beach;
    }

    public void setBeach(double beach) {
        this.beach = beach;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    @Override
    public String toString() {
        return "Services{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", food=" + food +
                ", bar=" + bar +
                ", transport=" + transport +
                ", pool=" + pool +
                ", kidsPark=" + kidsPark +
                ", beach=" + beach +
                ", packagePrice=" + packagePrice +
                '}';
    }
}
