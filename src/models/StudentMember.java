package models;

/**
 * Class for object student member
 */
public class StudentMember extends Member{
    private String studentId;
    private String collegeName;

    /**
     * Constructor of object type student member
     * @param email email address
     * @param name name
     * @param address address
     * @param gender gender
     * @param height height
     * @param startingWeight member's starting weight
     * @param chosenPackage the member's chosen gym package
     * @param studentId student id
     * @param collegeName college name
     */
    public StudentMember(String email, String name, String address, String gender,
                         double height, double startingWeight, String chosenPackage,
                         String studentId, String collegeName){

        super(email, name, address, gender, height, startingWeight, chosenPackage);
        setStudentId(studentId);
        setCollegeName(collegeName);
        chosenPackage();
    }

    /**
     * getter for student id
     * @return student id
     */
    String getStudentId() {
        return studentId;
    }

    /**
     * setter for student id
     * @param studentId student id
     */
    void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * get for college name
     * @return collge name
     */
    String getCollegeName() {
        return collegeName;
    }

    /**
     * setter for college name
     * @param collegeName college name
     */
    private void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * setter for package choice (WIT, or package 3 for other colleges)
     */
    private void chosenPackage(){
        if (getCollegeName().toUpperCase().equals("WIT")) {
            setChosenPackage("WIT");
        }
        else
            setChosenPackage("Package 3");
    }

    /**
     * Lists a member's attributes as a string
     * @return member's attributes as a string
     */
    public String toString() {
        return super.toString() +
                "\nStudent ID: " + getStudentId() +
                "\nCollege: " + getCollegeName();
    }
}
