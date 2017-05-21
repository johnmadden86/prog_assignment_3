package models;

/**
 * Class for the object premium member
 */
public class PremiumMember extends Member{

    /**
     * Constructor of object type premium member
     * @param email email address
     * @param name name
     * @param address address
     * @param gender gender
     * @param height height
     * @param startingWeight member's starting weight
     * @param chosenPackage the member's chosen gym package
     */
    public PremiumMember(String email, String name, String address, String gender,
                         double height, double startingWeight, String chosenPackage){

        super(email, name, address, gender, height, startingWeight, chosenPackage);
        chosenPackage(chosenPackage);
    }

    /**
     * setter for chosen package
     * @param packageChoice chosen package
     */
    private void chosenPackage(String packageChoice){
        setChosenPackage(packageChoice);
    }
}
