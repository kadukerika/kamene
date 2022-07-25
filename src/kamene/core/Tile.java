package kamene.core;

public class Tile {
    private int value;

    public Tile(int value) {
        this.value = value;
    }

    /**
     * Value of the clue.
     */


    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
