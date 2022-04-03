package lk.system.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="employee")
public class Employee extends SuperEntity{
    @Id
    private String id;
    private String state;
    private String name;
    private String nic;
    private String contact;
    private String address;
    private String dob;
    private String sattleDate;
    private String jobType;

    public Employee() {
    }

    public Employee(String id, String state, String name, String nic, String contact, String address, String dob, String sattleDate, String jobType) {
        this.id = id;
        this.state = state;
        this.name = name;
        this.nic = nic;
        this.contact = contact;
        this.address = address;
        this.dob = dob;
        this.sattleDate = sattleDate;
        this.jobType = jobType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSattleDate() {
        return sattleDate;
    }

    public void setSattleDate(String sattleDate) {
        this.sattleDate = sattleDate;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() {
        return "employee{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", sattleDate='" + sattleDate + '\'' +
                ", jobType='" + jobType + '\'' +
                '}';
    }
}
