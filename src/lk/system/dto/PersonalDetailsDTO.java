package lk.system.dto;

public class PersonalDetailsDTO {
    private String id;
    private int numOfAdult;
    private int numOfKids;
    private Integer roomQty;
    private String details;

    public PersonalDetailsDTO() {
    }

    public PersonalDetailsDTO(String id, int numOfAdult, int numOfKids, int roomQty, String details) {
        this.id = id;
        this.numOfAdult = numOfAdult;
        this.numOfKids = numOfKids;
        this.roomQty = roomQty;
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

    public int getRoomQty(){ return roomQty; }

    public void setRoomQty(int roomQty){this.roomQty = roomQty;}

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "PersonalDetailsDTO{" +
                "id='" + id + '\'' +
                ", numOfAdult=" + numOfAdult +
                ", numOfKids=" + numOfKids +
                ", roomQty=" + roomQty +
                ", details='" + details + '\'' +
                '}';
    }
}
