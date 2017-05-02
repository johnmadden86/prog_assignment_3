package models;

/**
 * Created by John on 24/04/2017.
 */
public class StudentMember extends Member{
    private String studentId;
    private String collegeName;

    public StudentMember(String email, String name, String address, String gender, double height, double startingWeight, String chosenPackage, String studentId, String collegeName) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
        this.studentId = studentId;
        this.collegeName = collegeName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "StudentMember{" +
                "studentId='" + studentId + '\'' +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }

    public void chosenPackage(String packageChoice){

    }
}
