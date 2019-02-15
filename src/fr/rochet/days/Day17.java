package fr.rochet.days;

import fr.rochet.DayInterface;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 0  1  2  3
 * 4  5  6  7
 * 8  9  10 11
 * 12 13 14 15
 */
public class Day17 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String input = "pslxynzg";

        HashMap<String, Integer> paths = new HashMap<>();
        paths.put(input, getPosition(0, 0));

        MessageDigest md = MessageDigest.getInstance("MD5");

        while (!paths.isEmpty() && paths.values().stream().noneMatch(x -> x == 15)) {
            HashMap<String, Integer> nextPaths = new HashMap<>();

            for (Map.Entry<String, Integer> path : paths.entrySet()) {
                // MD5
                md.update(String.valueOf(path.getKey()).getBytes());
                byte[] digest = md.digest();
                String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

                // gestion des portes
                if (isOpenDoor(hash.charAt(0)) && path.getValue() > 3) { // UP
                    nextPaths.put(path.getKey() + "U", path.getValue() - 4);
                }
                if (isOpenDoor(hash.charAt(1)) && path.getValue() < 12) { // DOWN
                    nextPaths.put(path.getKey() + "D", path.getValue() + 4);
                }
                if (isOpenDoor(hash.charAt(2)) && path.getValue() % 4 != 0) { // LEFT
                    nextPaths.put(path.getKey() + "L", path.getValue() - 1);
                }
                if (isOpenDoor(hash.charAt(3)) && path.getValue() % 4 != 3) { // RIGHT
                    nextPaths.put(path.getKey() + "R", path.getValue() + 1);
                }
            }

            paths.clear();
            paths = new HashMap<>(nextPaths);
            nextPaths.clear();
        }

        String shortestPath = paths.entrySet().stream().filter(x -> x.getValue() == 15).findFirst().get().getKey().substring(input.length());
        System.out.println("Shortest path is : " + shortestPath);
    }

    @Override
    public void part2() throws Exception {
        String input = "pslxynzg";

        HashMap<String, Integer> paths = new HashMap<>();
        paths.put(input, getPosition(0, 0));

        MessageDigest md = MessageDigest.getInstance("MD5");

        int longestPath = 0;

        while (!paths.isEmpty()) {
            HashMap<String, Integer> nextPaths = new HashMap<>();

            for (Map.Entry<String, Integer> path : paths.entrySet()) {

                if (path.getValue() == 15) {
                    // On est arrivé : on enregistre la longueur
                    longestPath = path.getKey().length() - input.length();
                } else {
                    // Sinon on avance
                    md.update(String.valueOf(path.getKey()).getBytes());
                    byte[] digest = md.digest();
                    String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

                    if (isOpenDoor(hash.charAt(0)) && path.getValue() > 3) { // UP
                        nextPaths.put(path.getKey() + "U", path.getValue() - 4);
                    }
                    if (isOpenDoor(hash.charAt(1)) && path.getValue() < 12) { // DOWN
                        nextPaths.put(path.getKey() + "D", path.getValue() + 4);
                    }
                    if (isOpenDoor(hash.charAt(2)) && path.getValue() % 4 != 0) { // LEFT
                        nextPaths.put(path.getKey() + "L", path.getValue() - 1);
                    }
                    if (isOpenDoor(hash.charAt(3)) && path.getValue() % 4 != 3) { // RIGHT
                        nextPaths.put(path.getKey() + "R", path.getValue() + 1);
                    }
                }
            }

            paths.clear();
            paths = new HashMap<>(nextPaths);
            nextPaths.clear();
        }

        System.out.println("Longest path is : " + longestPath);
    }

    private int getPosition(int x, int y) {
        return 3 * x + y;
    }

    private boolean isOpenDoor(char c) {
        return c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f';
    }
}
