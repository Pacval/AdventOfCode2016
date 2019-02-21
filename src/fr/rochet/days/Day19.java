package fr.rochet.days;

import fr.rochet.DayInterface;

import java.util.*;

public class Day19 implements DayInterface {

    @Override
    public void part1() {
        int nbElves = 3014387;

        LinkedList<Integer> chain = new LinkedList<>();

        for (int i = 1; i <= nbElves; i++) {
            chain.add(i);
        }

        Iterator iterator = chain.iterator();
        while (chain.size() != 1) {
            // On passe sur l'elfe qui vole le cadeau
            if (iterator.hasNext()) {
                iterator.next();
            } else {
                iterator = chain.iterator();
                iterator.next();
            }

            // On passe sur l'elfe qui se fait voler
            if (iterator.hasNext()) {
                iterator.next();
            } else {
                iterator = chain.iterator();
                iterator.next();
            }

            // on le dégage
            iterator.remove();
        }

        System.out.println("First game, remaining elf is number : " + chain.get(0));
    }

    @Override
    public void part2() {
        int nbElves = 3014387;

        LinkedList<Integer> firstHalf = new LinkedList<>();
        LinkedList<Integer> secondHalf = new LinkedList<>();

        for (int i = 1; i <= nbElves / 2; i++) {
            firstHalf.addLast(i);
        }
        for (int i = nbElves / 2 + 1; i <= nbElves ; i++) {
            secondHalf.addLast(i);
        }

        while (firstHalf.size() + secondHalf.size() > 1) {
            int stealer = firstHalf.pollFirst();
            if (firstHalf.size() == secondHalf.size()) {
                firstHalf.pollLast();
            } else {
                secondHalf.pollFirst();
            }

            secondHalf.addLast(stealer);
            firstHalf.addLast(secondHalf.pollFirst());
        }

        System.out.println("Second game, remaining elf is number : " + firstHalf.get(0));
    }
}
