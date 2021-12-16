package lk.system.dto;

public class CitemDTO {
    private String code;
    private String name;
    private int quantity;
    private String brand;
    private String description;

    public CitemDTO() {
    }

    public CitemDTO(String code, String name, int quantity, String brand, String description) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.brand = brand;
        this.description = description;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "CitemDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
