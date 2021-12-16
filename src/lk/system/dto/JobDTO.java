package lk.system.dto;

public class JobDTO {
    private String id;
    private String jobPosition;
    private double hRate;
    private double mRate;
    private String description;

    public JobDTO() {
    }

    public JobDTO(String id, String jobPosition, double hRate, double mRate, String description) {
        this.id = id;
        this.jobPosition = jobPosition;
        this.hRate = hRate;
        this.mRate = mRate;
        this.description = description;
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

    public double gethRate() {
        return hRate;
    }

    public void sethRate(double hRate) {
        this.hRate = hRate;
    }

    public double getmRate() {
        return mRate;
    }

    public void setmRate(double mRate) {
        this.mRate = mRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "id='" + id + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                ", hRate=" + hRate +
                ", mRate=" + mRate +
                ", description='" + description + '\'' +
                '}';
    }
}
