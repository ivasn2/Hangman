import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

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
            String userAnswer = input.nextLine();

            while (true) {
                if (userAnswer.isEmpty()) {
                    System.out.println("Введите n или e");
                    userAnswer = input.nextLine();
                } else if ((userAnswer.length() != 1) || (!Character.isLetter(userAnswer.charAt(0)))) {
                    System.out.println("Введите n или e");
                    userAnswer = input.nextLine();
                } else {
                    break;
                }
            }

            if (userAnswer.equalsIgnoreCase("N")) {
                System.out.println("--------------");
                System.out.println("| НАЧАЛО ИГРЫ |");
                System.out.println("--------------");

                while (!game.isWon() && !game.isLost()) {

                    System.out.println("Слово: " + game.buildMask());
                    System.out.println(game.drawHangman());

                    System.out.print("Введите букву: ");
                    String letter = input.nextLine().toLowerCase();

                    while (true) {
                        if (letter.isEmpty()) {
                            System.out.println("Введите букву");
                            letter = input.nextLine().toLowerCase();
                        } else if ((letter.length() != 1) || (!Character.isLetter(letter.charAt(0)))) {
                            System.out.println("Введите букву");
                            letter = input.nextLine().toLowerCase();
                        } else if (!(letter.matches("[А-Яа-яЁё]"))) {
                            System.out.println("Введите русскую букву");
                            letter = input.nextLine().toLowerCase();
                        } else {
                            break;
                        }
                    }

                    System.out.println(game.processLetter(letter));
                }

                if (game.isWon()) {
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