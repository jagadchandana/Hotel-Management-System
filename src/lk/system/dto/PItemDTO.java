package lk.system.dto;

public class PItemDTO {
    private String code;
    private String name;
    private String brand;
    private String company;
    private String date;
    private String description;
    private String warrenty;
    private String qty;

    public PItemDTO() {
    }

    public PItemDTO(String code, String name,String qty, String brand, String company, String date, String description, String warrenty) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.company = company;
        this.date = date;
        this.description = description;
        this.warrenty = warrenty;
        this.qty = qty;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarrenty() {
        return warrenty;
    }

    public void setWarrenty(String warrenty) {
        this.warrenty = warrenty;
    }

    public String getQty(){return qty;}

    public void setQty(String qty){this.qty = qty;}

    @Override
    public String toString() {
        return "PItemDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ",qty='" + qty + '\''+
                ", brand='" + brand + '\'' +
                ", company='" + company + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", warrenty='" + warrenty + '\'' +
                '}';
    }
}
