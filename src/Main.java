import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> words = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();


        final String[][] HANGMAN_STAGES = {
                {
                        " ___   ",
                        "|   |   ",
                        "|   O   ",
                        "|  (|)   ",
                        "|  //    ",
                        "======== ",
                },
                {
                        " ____   ",
                        "|    |   ",
                        "|    O   ",
                        "|   (|)    ",
                        "|       ",
                        "======== ",
                },
                {
                        " ____   ",
                        "|    |   ",
                        "|    O   ",
                        "|    |   ",
                        "|       ",
                        "======== ",
                },
                {
                        " ___   ",
                        "|   |   ",
                        "|   O   ",
                        "|      ",
                        "|       ",
                        "======== ",
                },
                {
                        " ___   ",
                        "|      ",
                        "|      ",
                        "|       ",
                        "|       ",
                        "======== ",
                },
                {
                        "|      ",
                        "|      ",
                        "|       ",
                        "|       ",
                        " ======= ",
                },

        };





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

            Set<Character> necessaryLetters = new HashSet<>();
            Set<Character> unNecessaryLetters = new HashSet<>();

            // Достаем рандомное слово
            int randomIndex = random.nextInt(words.size());
            String randomItem = words.get(randomIndex);

            String result = "*";
            int mistakes = 5;

            System.out.println("[N]ew game or [E]xit ?");


            String userAnswer = input.nextLine();

            // Если пользователь хочет играть
            if (userAnswer.equalsIgnoreCase("N")) {
                System.out.println("--------------");
                System.out.println("| НАЧАЛО ИГРЫ |");
                System.out.println("--------------");

                // Цикл выполняется до тех пор пока маска слова содержит *
                while (result.contains("*")) {

                    // Это чтобы не добавлялась *
                    stringBuilder.setLength(0);

                    // Делаем маску слова
                    for (Character character : randomItem.toCharArray()) {
                        if  (necessaryLetters.contains(character)) {
                            stringBuilder.append(character);
                        } else {
                            stringBuilder.append("*");
                        }
                    }

                    // Печатаем маску слова
                    result = stringBuilder.toString();
                    System.out.println("Слово: " + result);


                    // Тут же печатаем наш рисунок, который зависит от количества ошибок
                    for (String line : HANGMAN_STAGES[mistakes]) {
                        System.out.println(line);
                    }

                    // Если маска слова НЕ содежит * то мы заканчиваем игру -> пользователь выиграл
                    if (!result.contains("*")) {
                        break;
                    }

                    // Если у пользователя не осталось доступных ошибок -> прерываем цикл (заканчиваем игру)
                    if (mistakes == 0) {
                        break;
                    }

                    // Если маска слова содержт букву -> просим у пользователя чтобы он ввел букву
                    else {
                        System.out.print("Введите букву: ");
                        String letter = input.nextLine();

                        // Проверка уже введенной буквы (правильной)
                        // (1) Если буква, которую ввел пользователь, уже была введена и она есть в necessaryLetters -> пишем, что эту букву уже вводили
                        if (necessaryLetters.contains(letter.charAt(0))) {
                            System.out.println("Вы уже вводили эту букву");
                            System.out.println("---------------------------");
                        }

                        // (2) Если буква, которую ввел пользователь, есть в загаданном слове и ее нет в necessaryLetter -> добавляем в HashSet
                        else if (randomItem.contains(letter)) {
                            necessaryLetters.add(letter.charAt(0));
                        }



                        // Проверка уже введенной буквы (неправильной)
                        // (1) Если буква, которую ввел пользователь, уже была введена и она есть в unNecessaryLetters -> пишем, что эту букву уже вводили
                        else if (unNecessaryLetters.contains(letter.charAt(0))) {
                            System.out.println("Вы уже вводили эту букву");
                            System.out.println("---------------------------");
                        }
                        // (2) Если буквы, которую ввел пользователь, нет в загаданном слове и ее нет в unNecessaryLetters -> вычитаем балл + добавляем в HashSet
                        else if (!randomItem.contains(letter)) {

                            System.out.println("---------------------------");
                            System.out.println("| Такой буквы нет в слове |");
                            System.out.println("---------------------------");
                            mistakes -= 1;
                            unNecessaryLetters.add(letter.charAt(0));

                            // Пишем сколько ошибок у пользователя осталось
                            System.out.println("Осталось ошибок: " + mistakes);
                        }
                    }
                }

                // Если слово НЕ содержит "*" -> победа
                if (!result.contains("*")) {
                    System.out.println("-----------------------------------");
                    System.out.println("| Вы отгадали слово! Вы выиграли! |");
                    System.out.println("-----------------------------------");
                }

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
