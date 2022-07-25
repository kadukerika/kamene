package kamene;

import kamene.consoleui.ConsoleUI;
import kamene.core.Field;

public class Kamene {
    private ConsoleUI userInterface;
    private static long startMillis = System.currentTimeMillis();

    private static Kamene instance;

    public static Kamene getInstance() {
        if (instance == null) {
            new Kamene();
        }
        return instance;
    }

    private BestTimes bestTimes = new BestTimes();

    private Kamene() {
        instance = this;
        userInterface = new ConsoleUI();
        Field field = new Field(4, 4);
        userInterface.newGameStarted(field);
    }

    public static void main(String[] args) {
        Kamene.getInstance();
    }

    public static int getPlayingSeconds() {
        return (int) ((System.currentTimeMillis() - startMillis) / 1000);
    }

    public BestTimes getBestTimes() {
        return bestTimes;
    }
}
