package lk.system.views.tm;

public class PItemTM {
    private String code;
    private String name;
    private String qty;
    private String brand;
    private String company;
    private String date;
    private String description;
    private String warenty;


    public PItemTM() {
    }

    public PItemTM(String code, String name,String qty, String brand, String company, String date, String description, String warenty) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.brand = brand;
        this.company = company;
        this.date = date;
        this.description = description;
        this.warenty = warenty;

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

    public String getWarenty() {
        return warenty;
    }

    public void setWarenty(String warenty) {
        this.warenty = warenty;
    }
    public String getQty(){
        return qty;
    }
    public void setQTY(String qty){
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "PItemTM{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ",qty'" + qty + '\''+
                ", brand='" + brand + '\'' +
                ", company='" + company + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", warenty='" + warenty + '\'' +

                '}';
    }
}
