package lk.system.dto;

public class TitemDTO {
    private String id;
    private String name;
    private double price;

    public TitemDTO() {
    }

    public TitemDTO(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return "TitemDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}