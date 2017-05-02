package models;

/**
 * Created by John on 24/04/2017.
 */
public abstract class Person {
    private String email;
    private String name;
    private String address;
    private String gender;

    public Person(String email, String name, String address, String gender) {
        this.email = email;
        if (name.length() > 30) {//limit string length to 30 characters
            this.name = name.substring(0, 30);
        } else {
            this.name = name;
        }
        this.address = address;
        if (gender.equals("M") || gender.equals("m") || gender.equals("F") || gender.equals("f")) {//return capital letter only for valid entry
            gender = gender.toUpperCase();
            this.gender = gender;
        } else {//default to unspecified if other entry made
            this.gender = "Unspecified";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
