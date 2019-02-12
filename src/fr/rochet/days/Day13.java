package fr.rochet.days;

import fr.rochet.DayInterface;

import java.util.*;

/**
 * On va utiliser l'algorigthme A*
 */
public class Day13 implements DayInterface {

    @Override
    public void part1() {
        int mazeSize = 50;
        int input = 1364;

        HashMap<Integer, Boolean> maze = generateMaze(mazeSize, input);

        int steps = solveMaze(maze, mazeSize);
        System.out.println("It takes " + steps + " steps to go to the end");
    }

    @Override
    public void part2() {

        int mazeSize = 50;
        int input = 1364;
        int steps = 50;

        HashMap<Integer, Boolean> maze = generateMaze(mazeSize, input);
        // printMaze(maze, mazeSize);

        Set<Integer> visitedPositions = new HashSet<>();

        Set<Integer> currentPositions = new HashSet<>();
        currentPositions.add(mazeSize + 1); // 1;1 -> 1 * mazSize + 1

        for (int i = 0; i < steps; i++) {
            Set<Integer> nextPositions = new HashSet<>();
            for (Integer pos : currentPositions) {
                for (Integer adj : getAdjacentPoints(pos, mazeSize)) {
                    if (maze.containsKey(adj) && maze.get(adj)) {
                        visitedPositions.add(adj);
                        nextPositions.add(adj);
                    }
                }
            }
            currentPositions = new HashSet<>(nextPositions);
            // printMaze(maze, mazeSize, currentPositions);
        }

        System.out.println("Numbers of possible positions in " + steps + " steps : " + visitedPositions.size());
    }

    /**
     * ------------ CLASS POINT ------------
     */

    private class Point {
        int x;
        int y;
        int distFromStart;
        int distFromEnd;

        Point(int x, int y, int distFromStart, int distFromEnd) {
            this.x = x;
            this.y = y;
            this.distFromStart = distFromStart;
            this.distFromEnd = distFromEnd;
        }

        int getTotalDistance() {
            return distFromStart + distFromEnd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /**
     * ------------ PRIVATE METHODS ------------
     */

    private HashMap<Integer, Boolean> generateMaze(int mazeSize, int input) {
        // true = chemin , false = mur
        HashMap<Integer, Boolean> maze = new HashMap<>(mazeSize * mazeSize);

        for (int x = 0; x < mazeSize; x++) {
            for (int y = 0; y < mazeSize; y++) {
                maze.put(x * mazeSize + y, Integer.toBinaryString((x * x + 3 * x + 2 * x * y + y + y * y) + input).chars().filter(ch -> ch == '1').count() % 2 == 0);
            }
        }

        return maze;
    }

    private void printMaze(HashMap<Integer, Boolean> maze, int mazeSize) {
        for (int x = 0; x < mazeSize; x++) {
            for (int y = 0; y < mazeSize; y++) {
                System.out.print(maze.get(x * mazeSize + y) ? "." : "#");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printMaze(HashMap<Integer, Boolean> maze, int mazeSize, Point currentPoint, Point endPoint) {
        for (int x = 0; x < mazeSize; x++) {
            for (int y = 0; y < mazeSize; y++) {
                if (currentPoint.x == x && currentPoint.y == y) {
                    System.out.print("O");
                } else if (endPoint.x == x && currentPoint.y == y) {
                    System.out.print("E");
                } else {
                    System.out.print(maze.get(x * mazeSize + y) ? "." : "#");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printMaze(HashMap<Integer, Boolean> maze, int mazeSize, Set<Integer> currentPos) {
        for (int x = 0; x < mazeSize; x++) {
            for (int y = 0; y < mazeSize; y++) {
                if (currentPos.contains(x * mazeSize + y)) {
                    System.out.print("O");
                } else {
                    System.out.print(maze.get(x * mazeSize + y) ? "." : "#");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private int solveMaze(HashMap<Integer, Boolean> maze, int mazeSize) {

        // on cherche à atteindre 31,39
        Point endPoint = new Point(31, 39, 0, 0);

        Set<Point> possiblePath = new HashSet<>();
        Set<Point> testedPath = new HashSet<>();

        // on part de la position 1, 1
        Point currentPos = new Point(1, 1, 0, Math.abs(1 - endPoint.x) + Math.abs(1 - endPoint.y));
        possiblePath.add(currentPos);

        while (!possiblePath.isEmpty()) {
            // on récupère le point de chemin possible le plus proche de l'arrivée (distance de Manhattan)
            currentPos = possiblePath.stream().min(Comparator.comparing(point -> point.distFromStart + point.distFromEnd)).get();
            // on le vire de la liste
            possiblePath.remove(currentPos);

            if (currentPos.equals(endPoint)) {
                return currentPos.distFromStart;
            }

            for (Point adjacentPoint : getAdjacentPoints(currentPos, endPoint)) {
                // Si on est dans le labyrinthe généré, que la position n'est pas un mur
                if (maze.containsKey(adjacentPoint.x * mazeSize + adjacentPoint.y)
                        && maze.get(adjacentPoint.x * mazeSize + adjacentPoint.y)) {

                    Optional<Point> possiblePoint = possiblePath.stream().filter(x -> x.x == adjacentPoint.x && x.y == adjacentPoint.y).findFirst();
                    Optional<Point> testedPoint = testedPath.stream().filter(x -> x.x == adjacentPoint.x && x.y == adjacentPoint.y).findFirst();

                    if (possiblePoint.isPresent()) {
                        // si dans points possibles
                        if (possiblePoint.get().getTotalDistance() > adjacentPoint.getTotalDistance()) {
                            // chemin plus court, on remplace; sinon rien
                            possiblePath.remove(possiblePoint.get());
                            possiblePath.add(adjacentPoint);
                        }
                    } else if (testedPoint.isPresent()) {
                        // Si dans points testés
                        if (testedPoint.get().getTotalDistance() > adjacentPoint.getTotalDistance()) {
                            // chemin plus courte, on sort et on ajoute aux points possibles; sinon rien
                            testedPath.remove(testedPoint.get());
                            possiblePath.add(adjacentPoint);
                        }
                    } else {
                        // sinon on ajoute aux chemins possibles
                        possiblePath.add(adjacentPoint);
                    }
                }
            }

            // On ajoute le point à la liste des testés
            testedPath.add(currentPos);

        }
        return -1;
    }

    private List<Point> getAdjacentPoints(Point position, Point endPoint) {
        List<Point> adj = new ArrayList<>();
        adj.add(new Point(position.x + 1, position.y, position.distFromStart + 1, Math.abs(position.x + 1 - endPoint.x) + Math.abs(position.y - endPoint.y))); // droite
        adj.add(new Point(position.x - 1, position.y, position.distFromStart + 1, Math.abs(position.x - 1 - endPoint.x) + Math.abs(position.y - endPoint.y))); // gauche
        adj.add(new Point(position.x, position.y + 1, position.distFromStart + 1, Math.abs(position.x - endPoint.x) + Math.abs(position.y + 1 - endPoint.y))); // bas
        adj.add(new Point(position.x, position.y - 1, position.distFromStart + 1, Math.abs(position.x - endPoint.x) + Math.abs(position.y - 1 - endPoint.y))); // haut

        return adj;
    }

    private List<Integer> getAdjacentPoints(Integer position, int mazeSize) {
        List<Integer> adj = new ArrayList<>();
        adj.add(position + mazeSize); // bas
        adj.add(position - mazeSize); // haut
        adj.add(position + 1); // droite
        adj.add(position - 1); // gauche

        return adj;
    }
}
