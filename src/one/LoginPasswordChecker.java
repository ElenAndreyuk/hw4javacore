package one;

public class LoginPasswordChecker {
    private static String login;
    private static String password;
    private static String confirmPassword;


    public static boolean checker(String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException {
        if (login.length() > 5){
           throw new WrongLoginException("Login length is more then 20 symbols");
        }
        if (password.length() > 5 ){
            throw new WrongPasswordException("Password length is more then 20 symbols");
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Password and confirm password are not equal ");
        }
        return true;
    }
}
