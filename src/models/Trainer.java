package models;

/**
 * class for the ovject of type trainer
 */
public class Trainer extends Person{
    private String speciality;

    /**
     * Constructor of object type trainer
     * @param email email address
     * @param name name
     * @param address address
     * @param gender gender
     * @param speciality the trainer's speciality
     */
    public Trainer(String email, String name, String address, String gender, String speciality) {
        super(email, name, address, gender);
        setSpeciality(speciality);
    }

    /**
     * getter for speciality
     * @return speciality
     */
    String getSpeciality() {
        return speciality;
    }

    /**
     * setter for speciality
     * @param speciality speciality
     */
    private void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * Method to list the trainer attributes as a string
     * @return the trainer attributes as a string
     */
    public String toString() {
        return super.toString() +
                "\nSpeciality: " + getSpeciality();
    }
}
