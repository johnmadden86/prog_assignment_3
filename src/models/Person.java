package models;

/**
 * Abstract class to hold attributes associated with the object type person
 */
public abstract class Person {
    private String email, name, address, gender;

    /**
     * Constructor of object type person
     * @param email email address
     * @param name name
     * @param address address
     * @param gender gender
     */
    Person(String email, String name, String address, String gender) {
        setEmail(email);
        setName(name);
        setAddress(address);
        setGender(gender);
    }

    /**
     * Getter for email
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     * @param email email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name (max 30 characters)
     * @param name name
     */
    public void setName(String name) {
        if (name.length() > 30) {
            name = name.substring(0, 30);
        }
        this.name = name;
    }

    /**
     * getter for address
     * @return address
     */
    String getAddress() {
        return address;
    }

    /**
     * setter for address
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter for gender
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * setter for gender (M, F, or Unspecified)
     * @param gender
     */
    public void setGender(String gender) {
        if (gender.length() > 0) {
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
        } else {
            this.gender = "Unspecified";
        }
    }

    /**
     * String listing person attributes
     * @return attributes as a string
     */
    public String toString() {
        return "Name: " + getName() +
                "\nAddress: " + getAddress() +
                "\nEmail: " + getEmail() +
                "\nGender: " + getGender();
    }
}
