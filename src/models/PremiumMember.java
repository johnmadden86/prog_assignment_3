package models;

/**
 * Created by John on 24/04/2017.
 */
public class PremiumMember extends Member{
    public PremiumMember(String email, String name, String address, String gender, double height, double startingWeight, String chosenPackage) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
    }

    public void chosenPackage(String packageChoice){

    }
}
