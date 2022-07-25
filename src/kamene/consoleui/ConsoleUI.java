package kamene.consoleui;

import kamene.BestTimes;
import kamene.Kamene;
import kamene.UserInterface;
import kamene.core.Field;
import kamene.core.GameState;
import kamene.core.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.*;

public class ConsoleUI implements UserInterface {
    private Field field;

    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

//    private ConsoleUI consoleUI;

    @Override
    public void newGameStarted(Field field) {
        this.field = field;
        do {
            System.out.println("Your playing time: " + Kamene.getPlayingSeconds());
            update();
            try {
                processInput();
            } catch (WrongFormatException e) {
                throw new RuntimeException(e);
            }
        } while (field.getState() == GameState.PLAYING);

        if (field.getState() == GameState.SOLVED) {
            update();
            BestTimes.addPlayerTime(System.getProperty("user.dir"), Kamene.getPlayingSeconds());
            System.out.println("Congratulation");
            System.out.println("Your playing time: " + Kamene.getPlayingSeconds());
            System.out.println(Kamene.getInstance().getBestTimes());
            System.exit(0);
        }
    }

    private void processInput() throws WrongFormatException {
        System.out.println("Please enter your selection <X> EXIT, <N> NEW GAME, <W> UP, <S> DOWN, <A> LEFT, <D> RIGHT");
        String input = readLine();

        if (input.equalsIgnoreCase("X")) {
            System.out.println("Game end");
            System.exit(0);
        }

        Pattern p = Pattern.compile("W|S|A|D", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            if (input.equalsIgnoreCase("W")) {
                for (int row = 0; row < field.getRowCount(); row++) {
                    for (int column = 0; column < field.getColumnCount(); column++) {
                        if (field.getTile(row, column) == null && row != field.getRowCount() - 1) {
                            field.tiles[row][column] = field.tiles[row + 1][column];
                            field.tiles[row + 1][column] = null;
                        }
                    }
                }
            } else if (input.equalsIgnoreCase("S")) {
                for (int row = 0; row < field.getRowCount(); row++) {
                    for (int column = 0; column < field.getColumnCount(); column++) {
                        if (field.getTile(row, column) == null && row != 0) {
                            field.tiles[row][column] = field.tiles[row - 1][column];
                            field.tiles[row - 1][column] = null;
                        }
                    }
                }
            } else if (input.equalsIgnoreCase("A")) {
                for (int row = 0; row < field.getRowCount(); row++) {
                    for (int column = 0; column < field.getColumnCount(); column++) {
                        if (field.getTile(row, column) == null && column != field.getColumnCount() - 1) {
                            field.tiles[row][column] = field.tiles[row][column + 1];
                            field.tiles[row][column + 1] = null;
                        }
                    }
                }
            } else if (input.equalsIgnoreCase("D")) {
                for (int row = 0; row < field.getRowCount(); row++) {
                    for (int column = 0; column < field.getColumnCount(); column++) {
                        if (field.getTile(row, column) == null && column != 0) {
                            field.tiles[row][column] = field.tiles[row][column - 1];
                            field.tiles[row][column - 1] = null;
                        }
                    }
                }
            } else {
                throw new WrongFormatException("Incorrect input");
            }
        } else {
            System.out.println("Incorrect input, try again.");
        }
    }


    @Override
    public void update() {
        for (int i = 0; i < field.getColumnCount(); i++) {
            System.out.printf("%s%d", "\t\t", i);
        }
        System.out.println();
        for (int row = 0; row < field.getRowCount(); row++) {
            System.out.printf("%c%s", row, "\t\t");
            for (int column = 0; column < field.getColumnCount(); column++) {
                Tile tile = field.getTile(row, column);
                System.out.printf("%s%s", field.getTile(row, column), "\t\t");
            }
            System.out.print("\n");
        }
//        if (isSolved()) {
//            field.setState(GameState.SOLVED);
//        }
    }

//    private boolean isSolved() { /*snaha o ukoncenie hry vyhrou*/
//        for (int row = 0; row < field.getRowCount(); row++) {
//            for (int column = 0; column < field.getColumnCount(); column++) {
//               if() {}
//            }
//        }
//
//    }

}
