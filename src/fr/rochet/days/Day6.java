package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day6 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(6);

        Map<Integer, List<Character>> codes = new HashMap<>();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < entries[0].length(); i++) {
            codes.put(i, new ArrayList<>());
            codeBuilder.append("_");
        }

        for (String message : entries) {
            for (int i = 0; i < message.length(); i++) {
                codes.get(i).add(message.charAt(i));
            }
        }

        for (Map.Entry<Integer, List<Character>> entry : codes.entrySet()) {
            codeBuilder.setCharAt(entry.getKey(),
                    Collections.max(
                            entry.getValue().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet(),
                            Map.Entry.comparingByValue()).getKey());
        }

        System.out.println("The message with most present letters is : " + codeBuilder.toString());
    }

    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(6);

        Map<Integer, List<Character>> codes = new HashMap<>();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < entries[0].length(); i++) {
            codes.put(i, new ArrayList<>());
            codeBuilder.append("_");
        }

        for (String message : entries) {
            for (int i = 0; i < message.length(); i++) {
                codes.get(i).add(message.charAt(i));
            }
        }

        for (Map.Entry<Integer, List<Character>> entry : codes.entrySet()) {
            codeBuilder.setCharAt(entry.getKey(),
                    Collections.min(
                            entry.getValue().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet(),
                            Map.Entry.comparingByValue()).getKey());
        }

        System.out.println("The message with least present letters is : " + codeBuilder.toString());
    }
}
