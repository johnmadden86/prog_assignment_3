package models;

import org.jetbrains.annotations.Contract;

public abstract class Person {
    private String email;
    private String name;
    private String address;
    private String gender;

    Person(String email, String name, String address, String gender) {
        this.email = email;


        setName(name);
        this.address = address;
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
        if (name.length() > 30) {//limit string length to 30 characters
            this.name = name.substring(0, 30);
        } else {
            this.name = name;
        }
    }

    @Contract(pure = true)
    private String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        gender = gender.toUpperCase();
        if (gender.equals("M") || gender.equals("F")){
            this.gender = gender;
        } else {//default to unspecified if other entry made
            this.gender = "Unspecified";
        }
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\naddress: " + getAddress() +
                "\nemail: " + getEmail() +
                "\ngender: " + getGender();
    }
}
