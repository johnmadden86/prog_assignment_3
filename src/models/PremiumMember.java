package models;


public class PremiumMember extends Member{

    public PremiumMember(String email, String name, String address, String gender,
                         double height, double startingWeight, String chosenPackage){

        super(email, name, address, gender, height, startingWeight, chosenPackage);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void chosenPackage(String packageChoice){
        chosenPackage = packageChoice;
    }

}
