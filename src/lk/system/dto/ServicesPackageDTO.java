package lk.system.dto;

public class ServicesPackageDTO {
    private int id;
    private double foodprice;
    private double barPrice;
    private double transportPrice;
    private double poolPrice;
    private double kidsPrice;
    private double beachPrice;

    public ServicesPackageDTO() {
    }

    public ServicesPackageDTO(int id, double foodprice, double barPrice, double transportPrice, double poolPrice, double kidsPrice, double beachPrice) {
        this.id = id;
        this.foodprice = foodprice;
        this.barPrice = barPrice;
        this.transportPrice = transportPrice;
        this.poolPrice = poolPrice;
        this.kidsPrice = kidsPrice;
        this.beachPrice = beachPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(double foodprice) {
        this.foodprice = foodprice;
    }

    public double getBarPrice() {
        return barPrice;
    }

    public void setBarPrice(double barPrice) {
        this.barPrice = barPrice;
    }

    public double getTransportPrice() {
        return transportPrice;
    }

    public void setTransportPrice(double transportPrice) {
        this.transportPrice = transportPrice;
    }

    public double getPoolPrice() {
        return poolPrice;
    }

    public void setPoolPrice(double poolPrice) {
        this.poolPrice = poolPrice;
    }

    public double getKidsPrice() {
        return kidsPrice;
    }

    public void setKidsPrice(double kidsPrice) {
        this.kidsPrice = kidsPrice;
    }

    public double getBeachPrice() {
        return beachPrice;
    }

    public void setBeachPrice(double beachPrice) {
        this.beachPrice = beachPrice;
    }

    @Override
    public String toString() {
        return "ServicesPackageDTO{" +
                "id=" + id +
                ", foodprice=" + foodprice +
                ", barPrice=" + barPrice +
                ", transportPrice=" + transportPrice +
                ", poolPrice=" + poolPrice +
                ", kidsPrice=" + kidsPrice +
                ", beachPrice=" + beachPrice +
                '}';
    }
}
