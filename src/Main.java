import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> words = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // Чтение файла и добавление его в список слов, из которого мы будем потом вытаскивать рандомно слова
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Спрашиваем у пользователя будет ли он играть
        while (true) {

            // Достаем рандомное слово
            int randomIndex = random.nextInt(words.size());
            String randomItem = words.get(randomIndex).toLowerCase();
            HangmanGame game = new HangmanGame(randomItem);

            System.out.println("[N]ew game or [E]xit ?");
            String userAnswer = input.nextLine();

            //Обработка ввода пользователя
            while (true) {
                // Проверка пустая ли строка
                if (userAnswer.isEmpty()) {
                    System.out.println("Введите n или e");
                    userAnswer = input.nextLine();
                }
                // Проверка, что длина строки == 1 && или это вообще буква
                else if ((userAnswer.length() != 1) || (!Character.isLetter(userAnswer.charAt(0)))) {
                    System.out.println("Введите n или e");
                    userAnswer = input.nextLine();
                } else {
                    break;
                }
            }

            // Если пользователь хочет играть
            if (userAnswer.equalsIgnoreCase("N")) {
                System.out.println("--------------");
                System.out.println("| НАЧАЛО ИГРЫ |");
                System.out.println("--------------");

                // Цикл выполняется до тех пор пока маска слова содержит *
                while (!game.isWon() && !game.isLost()) {



                    // Если маска слова содержт букву -> просим у пользователя чтобы он ввел букву
                    else {
                        System.out.print("Введите букву: ");
                        String letter = input.nextLine().toLowerCase();

                        //Обработка ввода пользователя
                        while (true) {
                            // Проверка пустая ли строка
                            if (letter.isEmpty()) {
                                System.out.println("Введите букву");
                                letter = input.nextLine().toLowerCase();
                            }
                            // Проверка, что длина строки == 1 && или это вообще буква
                            else if ((letter.length() != 1) || (!Character.isLetter(letter.charAt(0)))) {
                                System.out.println("Введите букву");
                                letter = input.nextLine().toLowerCase();
                            }
                            // Проверка, что введенная буква является русской
                            else if (!(letter.matches("[А-Яа-яЁё]")))
                            {
                                System.out.println("Введите русскую букву");
                                letter = input.nextLine().toLowerCase();
                            }
                            else {
                                break;
                            }
                        }


                }

                // Если слово НЕ содержит "*" -> победа


                // Если слово содержит "*"(т.е пользователь не отгадал слово) -> поражение
                else {
                    System.out.println("------------------------------------");
                    System.out.println("| Вы проиграли! Попробуйте еще раз |");
                    System.out.println("------------------------------------");
                    System.out.println("Загаданное слово было: " + randomItem);
                }

            }
            // Если пользователь изначально отказался от игры
            else if (userAnswer.equalsIgnoreCase("E")) {
                System.out.println("Конец игры :(");
                break;
            }
        }
    }
}
