package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Then, a new tile is a trap only in one of the following situations:
 * - Its left and center tiles are traps, but its right tile is not.
 * - Its center and right tiles are traps, but its left tile is not.
 * - Only its left tile is a trap.
 * - Only its right tile is a trap.
 */
public class Day18 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(18);
        int nbLines = 40;

        String line = entries[0];

        List<String> trapSituations = new ArrayList<>();
        trapSituations.add("^^.");
        trapSituations.add(".^^");
        trapSituations.add("^..");
        trapSituations.add("..^");

        long nbSafeTiles = line.chars().filter(c -> c == '.').count();

        for(int i = 1; i < nbLines; i++) {
            line = generateLine(line, trapSituations);
            nbSafeTiles += line.chars().filter(c -> c == '.').count();
        }

        System.out.println("Numbers of safe tiles for " + nbLines + " lines : " + nbSafeTiles);
    }

    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(18);
        int nbLines = 400000;

        String line = entries[0];

        List<String> trapSituations = new ArrayList<>();
        trapSituations.add("^^.");
        trapSituations.add(".^^");
        trapSituations.add("^..");
        trapSituations.add("..^");

        long nbSafeTiles = line.chars().filter(c -> c == '.').count();

        int i = 1;
        while(i < nbLines) {
            String nextLine = generateLine(line, trapSituations);
            if (nextLine.equals(line)) {
                // boucle
                nbSafeTiles += (nbLines - i) * nextLine.chars().filter(c -> c == '.').count();
                i = nbLines;
            } else {
                nbSafeTiles += nextLine.chars().filter(c -> c == '.').count();
                line = nextLine;
                i++;
            }
        }

        System.out.println("Numbers of safe tiles for " + nbLines + " lines : " + nbSafeTiles);

    }

    private String generateLine(String previousLine, List<String> trapSituations) {
        String extendedLine = "." + previousLine + "."; // cases vides pour comparaison

        StringBuilder nextLineBuilder = new StringBuilder();
        for (int i = 1; i < extendedLine.length() - 1; i++) {
            if (trapSituations.contains(extendedLine.substring(i - 1, i + 2))) {
                nextLineBuilder.append("^");
            } else {
                nextLineBuilder.append(".");
            }
        }
        return nextLineBuilder.toString();
    }
}
