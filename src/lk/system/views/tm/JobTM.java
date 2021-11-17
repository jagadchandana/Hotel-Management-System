package lk.system.views.tm;

import javafx.scene.control.Button;

public class JobTM {
    private String id;
    private String jobPosition;
    private double hourRate;
    private double monthlyRate;
    private String description;
    private Button btn;

    public JobTM() {
    }

    public JobTM(String id, String jobPosition, double hourRate, double monthlyRate, String description, Button btn) {
        this.id = id;
        this.jobPosition = jobPosition;
        this.hourRate = hourRate;
        this.monthlyRate = monthlyRate;
        this.description = description;
        this.btn = btn;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public double getHourRate() {
        return hourRate;
    }

    public void setHourRate(double hourRate) {
        this.hourRate = hourRate;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobTM{" +
                "id='" + id + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                ", hourRate=" + hourRate +
                ", monthlyRate=" + monthlyRate +
                ", description='" + description + '\'' +
                '}';
    }
}
