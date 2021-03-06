package lk.system.views.tm;

public class CustomerTM {
    private String customerId;
    private String state;
    private String name;
    private String nic;
    private String address;
    private String contactNum;
    private String roomId;
    private String serviceId;
    private String onDate;
    private String offDate;
    private String onTime;
    private String offTime;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String state, String name, String nic, String address, String contactNum, String roomId, String serviceId, String onDate, String offDate, String onTime, String offTime) {
        this.customerId = customerId;
        this.state = state;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.contactNum = contactNum;
        this.roomId = roomId;
        this.serviceId = serviceId;
        this.onDate = onDate;
        this.offDate = offDate;
        this.onTime = onTime;
        this.offTime = offTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public String getOffDate() {
        return offDate;
    }

    public void setOffDate(String offDate) {
        this.offDate = offDate;
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerId='" + customerId + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", roomId='" + roomId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", onDate='" + onDate + '\'' +
                ", offDate='" + offDate + '\'' +
                ", onTime='" + onTime + '\'' +
                ", offTime='" + offTime + '\'' +
                '}';
    }
}
