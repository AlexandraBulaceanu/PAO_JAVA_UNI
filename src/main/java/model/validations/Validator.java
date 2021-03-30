package model.validations;

public class Validator {
    public static boolean validateName(String name){
        return name.matches("[A-Z][a-z]+");
    }

    public static boolean validateEmail(String email){
        return email.matches("^(.+)@(.+)$");
    }

    public static boolean validatePhoneNumber(String phoneNumber){
        return phoneNumber.matches("(0)[1-9][0-9]{8}");
    }


    public static boolean validateID(String ID){
        return ID.matches("[0-9]{12}");
    }



    public static boolean validateAccountID(String accountID){
        return accountID.matches("[a-z]{2}[0-9]{8}");
    }

    public static boolean valdiateCardId(String cardId){
        return cardId.matches("[a-z]{2}[0-9]{8}");
    }
}