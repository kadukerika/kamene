package kamene;

import java.util.*;

public class BestTimes implements Iterable<BestTimes.PlayerTime> {
    private static List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

    public Iterator<PlayerTime> iterator() {
        return playerTimes.iterator();
    }


    public static void addPlayerTime(String name, int time) {
        playerTimes.add(new PlayerTime(name, time));
        Collections.sort(playerTimes);
    }


    @Override
    public String toString() {
        Formatter f = new Formatter();
        playerTimes
                .stream()
                .forEach(pt -> f.format(pt.getName() + " " + pt.getTime() + "%n"));
        return f.toString();
    }

    public static class PlayerTime implements Comparable<PlayerTime> {
        private final String name;

        private static int time = (int) System.currentTimeMillis();

        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public static int getTime() {
            return time;
        }

        @Override
        public int compareTo(PlayerTime o) {
            return Integer.compare(this.getTime(), o.getTime());
        }
    }

}
