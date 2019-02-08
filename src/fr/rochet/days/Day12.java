package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.HashMap;

public class Day12 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(12);

        HashMap<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        int inst = 0;
        while (inst < entries.length) {
            switch (entries[inst].split(" ")[0]) {
                case "cpy":
                    String registerToPast = entries[inst].split(" ")[2];
                    try {
                        registers.replace(registerToPast, Integer.valueOf(entries[inst].split(" ")[1]));
                    } catch (NumberFormatException e) {
                        registers.replace(registerToPast, registers.get(entries[inst].split(" ")[1]));
                    }
                    inst++;
                    break;
                case "inc":
                    registers.replace(entries[inst].split(" ")[1], registers.get(entries[inst].split(" ")[1]) + 1);
                    inst++;
                    break;
                case "dec":
                    registers.replace(entries[inst].split(" ")[1], registers.get(entries[inst].split(" ")[1]) - 1);
                    inst++;
                    break;
                case "jnz":
                    int value;
                    try {
                        value = Integer.valueOf(entries[inst].split(" ")[1]);
                    } catch (NumberFormatException e) {
                        value = registers.get(entries[inst].split(" ")[1]);
                    }

                    if (value != 0) {
                        inst += Integer.valueOf(entries[inst].split(" ")[2]);
                    } else {
                        inst++;
                    }
                    break;
            }
        }

        System.out.println("Value of register 'a' at the end : " + registers.get("a"));
    }

    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(12);

        HashMap<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 1);
        registers.put("d", 0);

        int inst = 0;
        while (inst < entries.length) {
            switch (entries[inst].split(" ")[0]) {
                case "cpy":
                    String registerToPast = entries[inst].split(" ")[2];
                    try {
                        registers.replace(registerToPast, Integer.valueOf(entries[inst].split(" ")[1]));
                    } catch (NumberFormatException e) {
                        registers.replace(registerToPast, registers.get(entries[inst].split(" ")[1]));
                    }
                    inst++;
                    break;
                case "inc":
                    registers.replace(entries[inst].split(" ")[1], registers.get(entries[inst].split(" ")[1]) + 1);
                    inst++;
                    break;
                case "dec":
                    registers.replace(entries[inst].split(" ")[1], registers.get(entries[inst].split(" ")[1]) - 1);
                    inst++;
                    break;
                case "jnz":
                    int value;
                    try {
                        value = Integer.valueOf(entries[inst].split(" ")[1]);
                    } catch (NumberFormatException e) {
                        value = registers.get(entries[inst].split(" ")[1]);
                    }

                    if (value != 0) {
                        inst += Integer.valueOf(entries[inst].split(" ")[2]);
                    } else {
                        inst++;
                    }
                    break;
            }
        }

        System.out.println("Value of register 'a' at the end (with register 'c' initialized at 1) : " + registers.get("a"));
    }
}
