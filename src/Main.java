import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        ArrayList<String> words = WordLoader.wordLoader("words.txt");

        while (true) {

            int randomIndex = random.nextInt(words.size());
            String randomItem = words.get(randomIndex).toLowerCase();

            System.out.println("[N]ew game or [E]xit ?");
            String userAnswer = InputValidator.validateInput(input, "Введите n или e", false);

            if (userAnswer.equalsIgnoreCase("N")) {
                HangmanGame game = new HangmanGame(randomItem);
                System.out.println("--------------");
                System.out.println("| НАЧАЛО ИГРЫ |");
                System.out.println("--------------");

                while (!game.isWon() && !game.isLost()) {

                    System.out.println("Слово: " + game.buildMask());
                    System.out.println(HangmanRenderer.drawHangman(game.getMistakes()));

                    System.out.print("Введите букву: ");
                    String letter = InputValidator.validateInput(input, "Введите букву", true);

                    System.out.println(game.processLetter(letter));
                    game.buildMask();
                }

                if (game.isWon()) {
                    System.out.println("Слово: " + game.buildMask());
                    System.out.println("-----------------------------------");
                    System.out.println("| Вы отгадали слово! Вы выиграли! |");
                    System.out.println("-----------------------------------");
                } else {
                    System.out.println(HangmanRenderer.drawHangman(game.getMistakes()));
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