package models;

public class StudentMember extends Member{
    private String studentId;
    private String collegeName;

    public StudentMember(String email, String name, String address, String gender,
                         double height, double startingWeight, String chosenPackage,
                         String studentId, String collegeName){

        super(email, name, address, gender, height, startingWeight, chosenPackage);
        setStudentId(studentId);
        setCollegeName(collegeName);
        chosenPackage();
    }

    String getStudentId() {
        return studentId;
    }

    void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    String getCollegeName() {
        return collegeName;
    }

    private void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    private void chosenPackage(){
        if (getCollegeName().toUpperCase().equals("WIT")) {
            setChosenPackage("WIT");
        }
        else
            setChosenPackage("Package 3");
    }

    public String toString() {
        return super.toString() +
                "\nStudent ID: " + getStudentId() +
                "\nCollege: " + getCollegeName();
    }
}
