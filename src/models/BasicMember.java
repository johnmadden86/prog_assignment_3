package models;

/**
 * Created by John on 24/04/2017.
 */
public class BasicMember extends Member{

    public BasicMember(String email, String name, String address, String gender,
                       double height, double startingWeight, String chosenPackage){

        super(email, name, address, gender, height, startingWeight, chosenPackage);
        this.chosenPackage("Basic");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void chosenPackage(String packageChoice){
        chosenPackage = packageChoice;
    }


}
