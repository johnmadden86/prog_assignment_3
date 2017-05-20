package models;


public class BasicMember extends Member{

    public BasicMember(String email, String name, String address, String gender,
                       double height, double startingWeight, String chosenPackage){

        super(email, name, address, gender, height, startingWeight, chosenPackage);
        chosenPackage(chosenPackage);
    }

    @Override
    public void chosenPackage(String chosenPackage) {
        setChosenPackage(chosenPackage);
    }
}
