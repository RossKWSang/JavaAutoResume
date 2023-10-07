package model;

public class CareerDTO {
    private String workPeriod;
    private String workPlace;
    private String position;
    private String workYear;

    public CareerDTO() {
    }

    public CareerDTO(String workPeriod, String workPlace, String position, String workYear) {
        this.workPeriod = workPeriod;
        this.workPlace = workPlace;
        this.position = position;
        this.workYear = workYear;
    }

    public String getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(String workPeriod) {
        this.workPeriod = workPeriod;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    @Override
    public String toString() {
        return "CareerDTO{" +
                "workPeriod='" + workPeriod + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", position='" + position + '\'' +
                ", workYear='" + workYear + '\'' +
                '}';
    }
}
