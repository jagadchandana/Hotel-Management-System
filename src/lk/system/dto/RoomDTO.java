package lk.system.dto;

public class RoomDTO {
    private String id;
    private String name;
    private String type;
    private Double price;
    private Integer qty;
    private String description;

    public RoomDTO() {
    }

    public RoomDTO(String id, String name, String type, Double price, Integer qty, String description) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty(){ return qty; }

    public void setQty(int qty){ this.qty = qty; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", qty=" + qty + '\''+
                ", description='" + description + '\'' +
                '}';
    }
}
