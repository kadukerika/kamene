package kamene.core;

import java.util.Random;

public class Field {
    public Tile[][] tiles;

    private final int rowCount;
    private final int columnCount;
    private GameState state = GameState.PLAYING;


    public Field(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        tiles = new Tile[rowCount][columnCount];

        generate();
        scramble();
    }

    private void generate() {

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (tiles[row][column] == null) {
                    tiles[row][column] = new Tile(valueOfTile(row, column));
                }
            }
        }
        tiles[getRowCount() - 1][getColumnCount() - 1] = null;
    }

    private void scramble() {
        for (int scramble = 0; scramble < 100; scramble++) {
            Random r = new Random();
            int n = r.nextInt(4);
            if (n == 0) {
                for (int row = 0; row < getRowCount(); row++) {
                    for (int column = 0; column < getColumnCount(); column++) {
                        if (getTile(row, column) == null && row != getRowCount() - 1) {
                            tiles[row][column] = tiles[row + 1][column];
                            tiles[row + 1][column] = null;
                        }
                    }
                }
            } else if (n == 1) {
                for (int row = 0; row < getRowCount(); row++) {
                    for (int column = 0; column < getColumnCount(); column++) {
                        if (getTile(row, column) == null && row != 0) {
                            tiles[row][column] = tiles[row - 1][column];
                            tiles[row - 1][column] = null;
                        }
                    }
                }
            } else if (n == 2) {
                for (int row = 0; row < getRowCount(); row++) {
                    for (int column = 0; column < getColumnCount(); column++) {
                        if (getTile(row, column) == null && column != getColumnCount() - 1) {
                            tiles[row][column] = tiles[row][column + 1];
                            tiles[row][column + 1] = null;
                        }
                    }
                }
            } else {
                for (int row = 0; row < getRowCount(); row++) {
                    for (int column = 0; column < getColumnCount(); column++) {
                        if (getTile(row, column) == null && column != 0) {
                            tiles[row][column] = tiles[row][column - 1];
                            tiles[row][column - 1] = null;
                        }
                    }
                }
            }
        }
    }

    private int valueOfTile(int row, int column) {
        int valueOfTile = row * 4 + column + 1;
        return valueOfTile;
    }


    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }
}
