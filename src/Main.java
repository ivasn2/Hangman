import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

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


    // Чтение файла и добавление его в список слов - метод
    public static ArrayList<String> wordLoader(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        ArrayList<String> words = wordLoader("words.txt");

        while (true) {

            int randomIndex = random.nextInt(words.size());
            String randomItem = words.get(randomIndex).toLowerCase();
            HangmanGame game = new HangmanGame(randomItem);

            System.out.println("[N]ew game or [E]xit ?");
            String userAnswer = validateInput(input, "Введите n или e", false);

            if (userAnswer.equalsIgnoreCase("N")) {
                System.out.println("--------------");
                System.out.println("| НАЧАЛО ИГРЫ |");
                System.out.println("--------------");

                while (!game.isWon() && !game.isLost()) {

                    System.out.println("Слово: " + game.buildMask());
                    System.out.println(game.drawHangman());

                    System.out.print("Введите букву: ");
                    String letter = validateInput(input, "Введите букву", true);

                    System.out.println(game.processLetter(letter));
                    game.buildMask();
                }

                if (game.isWon()) {
                    System.out.println("Слово: " + game.buildMask());
                    System.out.println("-----------------------------------");
                    System.out.println("| Вы отгадали слово! Вы выиграли! |");
                    System.out.println("-----------------------------------");
                } else {
                    System.out.println(game.drawHangman());
                    System.out.println("------------------------------------");
                    System.out.println("| Вы проиграли! Попробуйте еще раз |");
                    System.out.println("------------------------------------");
                    System.out.println("Загаданное слово было: " + randomItem);
                }

            } else if (userAnswer.equalsIgnoreCase("E")) {
                System.out.println("Конец игры :(");
                break;
            }
        }
    }


}