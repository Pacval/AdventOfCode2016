package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.io.IOException;
import java.util.HashSet;

public class Day1 implements DayInterface {

    /*
     * DIRECTIONS : 0 NORD / 1 EST / 2 SUD / 4 OUEST
     */
    @Override
    public void part1() throws IOException {
        String[] entries = ExoUtils.getEntries(1);

        String[] movements = entries[0].split(", ");

        int north = 0, east = 0;
        int direction = 0;

        for (String move : movements) {
            if (move.charAt(0) == 'R') {
                direction = (direction + 1) % 4;
            } else {
                direction = (direction + 3) % 4; // revient à faire -1 tout en prenant en compte les valeurs négatives
            }

            int distance = Integer.parseInt(move.substring(1));
            switch (direction) {
                case 0:
                    north += distance;
                    break;
                case 1:
                    east += distance;
                    break;
                case 2:
                    north -= distance;
                    break;
                case 3:
                    east -= distance;
                    break;
            }
        }

        int travelDistance = Math.abs(north) + Math.abs(east);

        System.out.println("Easter Bunny HQ is " + travelDistance + " blocks away");
    }

    private class Position {
        int north;
        int east;

        Position() {
            north = 0;
            east = 0;
        }

        void moveNorth() {
            north++;
        }

        void moveSouth() {
            north--;
        }

        void moveEast() {
            east++;
        }

        void moveWest() {
            east--;
        }
    }

    private class Travel {
        Position position;
        int direction; // 0 NORD / 1 EST / 2 SUD / 4 OUEST
        HashSet<String> visitedPositions;

        Travel() {
            position = new Position();
            direction = 0;
            visitedPositions = new HashSet<>();
            visitedPositions.add(position.north + "." + position.east);
        }

        void turnRight() {
            direction = (direction + 1) % 4;
        }

        void turnLeft() {
            direction = (direction + 3) % 4;
        }

        /* retourne true si on passe sur une case déjà visitée */
        boolean move() {
            switch (direction) {
                case 0:
                    position.moveNorth();
                    break;
                case 1:
                    position.moveEast();
                    break;
                case 2:
                    position.moveSouth();
                    break;
                case 3:
                    position.moveWest();
                    break;
            }
            if (visitedPositions.contains(position.north + "." + position.east)) {
                return true;
            } else {
                visitedPositions.add(position.north + "." + position.east);
                return false;
            }
        }
    }

    @Override
    public void part2() throws IOException {
        String[] entries = ExoUtils.getEntries(1);

        String[] movements = entries[0].split(", ");

        Travel travel = new Travel();
        boolean foundHq = false;
        int i = 0;

        while (!foundHq) {
            if (movements[i].charAt(0) == 'R') {
                travel.turnRight();
            } else {
                travel.turnLeft();
            }

            int distance = Integer.parseInt(movements[i].substring(1));

            int j = 0;
            while (!foundHq && j < distance) {
                foundHq = travel.move();
                j++;
            }
            i++;
        }

        System.out.println("Easter Bunny HQ is at position " + travel.position.north + " north " + travel.position.east + " east");
        int travelDistance = Math.abs(travel.position.north) + Math.abs(travel.position.east);
        System.out.println("Which represent a distance of " + travelDistance + " blocks");
    }
}
