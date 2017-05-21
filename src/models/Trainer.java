package models;

public class Trainer extends Person{
    private String speciality;

    public Trainer(String email, String name, String address, String gender, String speciality) {
        super(email, name, address, gender);
        setSpeciality(speciality);
    }

    String getSpeciality() {
        return speciality;
    }

    private void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String toString() {
        return super.toString() +
                "\nSpeciality: " + getSpeciality();
    }
}
