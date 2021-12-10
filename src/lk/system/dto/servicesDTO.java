package lk.system.dto;

public class servicesDTO {
    private String id;
    private String name;
    private String type;
    private Double food;
    private Double bar;
    private Double transport;
    private Double pool;
    private Double kidsPark;
    private Double beach;

    public servicesDTO() {
    }

    public servicesDTO(String id, String name, String type, Double food, Double bar, Double transport, Double pool, Double kidsPark, Double beach) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.food = food;
        this.bar = bar;
        this.transport = transport;
        this.pool = pool;
        this.kidsPark = kidsPark;
        this.beach = beach;
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

    @Override
    public String toString() {
        return "servicesDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", food=" + food +
                ", bar=" + bar +
                ", transport=" + transport +
                ", pool=" + pool +
                ", kidsPark=" + kidsPark +
                ", beach=" + beach +
                '}';
    }
}
