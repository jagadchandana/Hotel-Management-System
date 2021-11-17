package lk.system.views.tm;

public class CustomerTM {
    private String customerId;
    private String name;
    private String nic;
    private String address;
    private String contactNum;
    private String gender;
    private String roomId;
    private String serviceId;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String name, String nic, String address, String contactNum, String gender, String roomId, String serviceId) {
        this.customerId = customerId;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.contactNum = contactNum;
        this.gender = gender;
        this.roomId = roomId;
        this.serviceId = serviceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", gender='" + gender + '\'' +
                ", roomId='" + roomId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                '}';
    }
}
