package lk.system.views.tm;

public class ServicesTM {
    private String id;
    private String name;
    private String type;
    private Double food;
    private Double bar;
    private Double transport;
    private Double pool;
    private Double kidsPark;
    private Double beach;
    private Double packagePrice;

    public ServicesTM() {
    }

    public ServicesTM(String id, String name, String type, Double food, Double bar, Double transport, Double pool, Double kidsPark, Double beach, Double packagePrice) {
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

    public Double getFood() {
        return food;
    }

    public void setFood(Double food) {
        this.food = food;
    }

    public Double getBar() {
        return bar;
    }

    public void setBar(Double bar) {
        this.bar = bar;
    }

    public Double getTransport() {
        return transport;
    }

    public void setTransport(Double transport) {
        this.transport = transport;
    }

    public Double getPool() {
        return pool;
    }

    public void setPool(Double pool) {
        this.pool = pool;
    }

    public Double getKidsPark() {
        return kidsPark;
    }

    public void setKidsPark(Double kidsPark) {
        this.kidsPark = kidsPark;
    }

    public Double getBeach() {
        return beach;
    }

    public void setBeach(Double beach) {
        this.beach = beach;
    }

    public Double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(Double packagePrice) {
        this.packagePrice = packagePrice;
    }

    @Override
    public String toString() {
        return "ServicesTM{" +
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
