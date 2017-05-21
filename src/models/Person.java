package models;


public abstract class Person {
    private String email, name, address, gender;

    Person(String email, String name, String address, String gender) {
        setEmail(email);
        setName(name);
        setAddress(address);
        setGender(gender);
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
        if (name.length() > 30) {
            name = name.substring(0, 30);
        }
        this.name = name;
    }

    String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        gender = gender.substring(0,1).toUpperCase();
        if (gender.equals("M") || gender.equals("F")) {
            this.gender = gender;
        } else {
            if (this.gender == null) {
                gender = "Unspecified";
            } else {
                gender = this.gender;
            }
            this.gender = gender;
        }
    }

    public String toString() {
        return "Name: " + getName() +
                "\nAddress: " + getAddress() +
                "\nEmail: " + getEmail() +
                "\nGender: " + getGender();
    }
}
