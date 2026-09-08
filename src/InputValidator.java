import java.util.Scanner;

public class InputValidator {
    public static String validateInput(Scanner input, String errorMessage, boolean requireRussian) {
        String value = input.nextLine().toLowerCase();
        while (true) {
            if (value.isEmpty()) {
                System.out.println(errorMessage);
                value = input.nextLine().toLowerCase();
            } else if (value.length() != 1 || !Character.isLetter(value.charAt(0))) {
                System.out.println(errorMessage);
                value = input.nextLine().toLowerCase();
            } else if (requireRussian && !value.matches("[А-Яа-яЁё]")) {
                System.out.println("Введите русскую букву");
                value = input.nextLine().toLowerCase();
            } else {
                break;
            }
        }
        return value;
    }
}
