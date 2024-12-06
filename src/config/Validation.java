package config;

public class Validation {

    public static Boolean validate (String email){
        return email.matches(emailPattern());
    }

    public static String emailPattern(){
        return "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    }

    public static Boolean validatePas (String password){
        return password.matches(passwordPattern());
    }

    public static String passwordPattern(){
        return "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%_+=]).{8,}$";
    }
}
