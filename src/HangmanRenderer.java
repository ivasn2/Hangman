public class HangmanRenderer {
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

    public String drawHangman(int mistakes) {
        return String.join("\n", HANGMAN_STAGES[mistakes]);
    }
}
