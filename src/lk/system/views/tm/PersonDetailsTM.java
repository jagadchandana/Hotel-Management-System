package lk.system.views.tm;

public class PersonDetailsTM {
    private String id;
    private int numOfAdult;
    private int numOfKids;
    private String details;

    public PersonDetailsTM() {
    }

    public PersonDetailsTM(String id, int numOfAdult, int numOfKids, String details) {
        this.id = id;
        this.numOfAdult = numOfAdult;
        this.numOfKids = numOfKids;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumOfAdult() {
        return numOfAdult;
    }

    public void setNumOfAdult(int numOfAdult) {
        this.numOfAdult = numOfAdult;
    }

    public int getNumOfKids() {
        return numOfKids;
    }

    public void setNumOfKids(int numOfKids) {
        this.numOfKids = numOfKids;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "PersonDetailsTM{" +
                "id='" + id + '\'' +
                ", numOfAdult=" + numOfAdult +
                ", numOfKids=" + numOfKids +
                ", details='" + details + '\'' +
                '}';
    }
}
