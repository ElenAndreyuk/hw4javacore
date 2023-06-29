package one;

import java.util.Scanner;

public class Logic {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Login: ");
            String login = scanner.next();
            System.out.println("Password: ");
            String password = scanner.next();
            System.out.println("Confirm password: ");
            String confirmPassword = scanner.next();
            LoginPasswordChecker.checker(login, password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println(e.getMessage());
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }

}
