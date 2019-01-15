package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 implements DayInterface {

    @Override
    public void part1() throws IOException {
        String[] entries = ExoUtils.getEntries(3);

        List<List<Integer>> triangles = Arrays.stream(entries)
                .map(e -> Arrays.stream(e.split(" "))
                        .filter(sub -> !sub.equals(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        int possibleTriangles = 0;
        for (List<Integer> triangle : triangles) {

            Integer longestSide = triangle.stream().mapToInt(e -> e).max().getAsInt();
            triangle.remove(longestSide);
            if (longestSide < triangle.stream().mapToInt(e -> e).sum()) {
                possibleTriangles++;
            }
        }

        System.out.println("Number of possible triangles : " + possibleTriangles);
    }

    @Override
    public void part2() throws IOException {
        String[] entries = ExoUtils.getEntries(3);

        List<List<Integer>> triangles = Arrays.stream(entries)
                .map(e -> Arrays.stream(e.split(" "))
                        .filter(sub -> !sub.equals(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        List<List<Integer>> finalTriangles = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < triangles.size(); i += 3) {
                List<Integer> newTriangle = new ArrayList<>();
                newTriangle.add(triangles.get(i).get(j));
                newTriangle.add(triangles.get(i + 1).get(j));
                newTriangle.add(triangles.get(i + 2).get(j));

                finalTriangles.add(newTriangle);
            }
        }

        int possibleTriangles = 0;
        for (List<Integer> triangle : finalTriangles) {

            Integer longestSide = triangle.stream().mapToInt(e -> e).max().getAsInt();
            triangle.remove(longestSide);
            if (longestSide < triangle.stream().mapToInt(e -> e).sum()) {
                possibleTriangles++;
            }
        }

        System.out.println("New number of possible triangles : " + possibleTriangles);
    }
}
