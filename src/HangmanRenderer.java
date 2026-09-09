public class HangmanRenderer {
    private static final String[][] HANGMAN_STAGES = {
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

    public static String drawHangman(int mistakes) {
        return String.join("\n", HANGMAN_STAGES[mistakes]);
    }
}
