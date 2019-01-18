package fr.rochet;

import fr.rochet.days.Day10;

public class Main {

    public static void main(String[] args) {
        try {
            DayInterface day = new Day10();
            day.part1();
            day.part2();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
