import java.util.HashSet;
import java.util.Set;

public class HangmanGame {

    private String randomItem;
    private Set<Character> necessaryLetters;
    private Set<Character> unNecessaryLetters;
    private int mistakes;
    private String result;
    private StringBuilder stringBuilder;

    static final String[][] HANGMAN_STAGES = {
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

    public HangmanGame(String word) {
        this.stringBuilder = new StringBuilder();

        this.necessaryLetters = new HashSet<>();
        this.unNecessaryLetters = new HashSet<>();

        this.result = "*";
        this.mistakes = 5;

        this.randomItem = word;
    }

    public String buildMask() {
        stringBuilder.setLength(0);
        for (Character character : randomItem.toCharArray()) {
            if (necessaryLetters.contains(character)) {
                stringBuilder.append(character);
            } else {
                stringBuilder.append("*");
            }
        }
        result = stringBuilder.toString();
        return result;
    }

    public String processLetter(String letter) {
        if (necessaryLetters.contains(letter.charAt(0))) {
            return "Вы уже вводили эту букву\n---------------------------";
        }

        // (2) Если буква, которую ввел пользователь, есть в загаданном слове и ее нет в necessaryLetter -> добавляем в HashSet
        else if (randomItem.contains(letter)) {
            necessaryLetters.add(letter.charAt(0));

        }

        // Проверка уже введенной буквы (неправильной)
        // (1) Если буква, которую ввел пользователь, уже была введена и она есть в unNecessaryLetters -> пишем, что эту букву уже вводили
        else if (unNecessaryLetters.contains(letter.charAt(0))) {
            return "Вы уже вводили эту букву\n---------------------------";
        }
        // (2) Если буквы, которую ввел пользователь, нет в загаданном слове и ее нет в unNecessaryLetters -> вычитаем балл + добавляем в HashSet
        else if (!randomItem.contains(letter)) {

            mistakes -= 1;
            unNecessaryLetters.add(letter.charAt(0));
            return "---------------------------\n Такой буквы нет в слове \n---------------------------\nОсталось ошибок: " + mistakes;

        }
        return "";
    }


    public String drawHangman() {
        return String.join("\n", HANGMAN_STAGES[mistakes]);
    }

    public boolean isWon() {
        return !result.contains("*");
    }

    public boolean isLost() {
        return mistakes == 0;
    }
}
