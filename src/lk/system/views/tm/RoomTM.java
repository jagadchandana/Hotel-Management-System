package lk.system.views.tm;

import javafx.scene.control.Button;

public class RoomTM {
    private String id;
    private String name;
    private String type;
    private String description;
    private Button btnDelete;

    public RoomTM() {
    }

    public RoomTM(String id, String name, String type, String description, Button btnDelete) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.btnDelete = btnDelete;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "RoomTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
