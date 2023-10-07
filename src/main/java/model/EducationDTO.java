package model;

public class EducationDTO {
    private String graduationDate;
    private String schoolName;
    private String major;
    private String status;

    public EducationDTO() {
    }

    public EducationDTO(String graduationDate, String schoolName, String major, String status) {
        this.graduationDate = graduationDate;
        this.schoolName = schoolName;
        this.major = major;
        this.status = status;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EducationDTO{" +
                "graduationDate='" + graduationDate + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", major='" + major + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
