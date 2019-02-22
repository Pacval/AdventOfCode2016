package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.Arrays;

public class Day21 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(21);

        StringBuilder password = new StringBuilder("abcdefgh");

        for (String inst : entries) {
            switch (inst.split(" ")[0]) {
                case "swap":
                    if (inst.split(" ")[1].equals("position")) {

                        String i = String.valueOf(password.charAt(Integer.parseInt(inst.split(" ")[2])));
                        String j = String.valueOf(password.charAt(Integer.parseInt(inst.split(" ")[5])));

                        password = new StringBuilder(password.toString().replaceAll(i, "#").replaceAll(j, i).replaceAll("#", j));

                    } else if (inst.split(" ")[1].equals("letter")) {

                        String i = inst.split(" ")[2];
                        String j = inst.split(" ")[5];

                        password = new StringBuilder(password.toString().replaceAll(i, "#").replaceAll(j, i).replaceAll("#", j));
                    }
                    break;

                case "rotate":
                    if (inst.split(" ")[1].equals("based")) {
                        int times = (password.indexOf(inst.split(" ")[6]) >= 4 ? password.indexOf(inst.split(" ")[6]) + 2 : password.indexOf(inst.split(" ")[6]) + 1) % password.length();

                        String tmp = password.substring(password.length() - times, password.length());
                        password.delete(password.length() - times, password.length());
                        password.insert(0, tmp);

                    } else {
                        int times = Integer.parseInt(inst.split(" ")[2]) % password.length();

                        if (inst.split(" ")[1].equals("right")) {
                            String tmp = password.substring(password.length() - times, password.length());
                            password.delete(password.length() - times, password.length());
                            password.insert(0, tmp);
                        } else {
                            String tmp = password.substring(0, times);
                            password.delete(0, times);
                            password.insert(password.length(), tmp);
                        }
                    }
                    break;

                case "reverse": {

                    int i = Integer.parseInt(inst.split(" ")[2]);
                    int j = Integer.parseInt(inst.split(" ")[4]);

                    password = new StringBuilder(password.substring(0, i) + new StringBuilder(password.substring(i, j + 1)).reverse() + password.substring(j + 1));
                    break;
                }

                case "move": {

                    int i = Integer.parseInt(inst.split(" ")[2]);
                    int j = Integer.parseInt(inst.split(" ")[5]);

                    char tmp = password.charAt(i);
                    password.deleteCharAt(i);
                    password.insert(j, tmp);
                    break;
                }
            }
        }

        System.out.println("Final password is : " + password.toString());

    }

    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(21);

        StringBuilder password = new StringBuilder("fbgdceah");

        for (int line = entries.length - 1; line >= 0; line--) {
            String inst = entries[line];
            switch (inst.split(" ")[0]) {
                case "swap":
                    if (inst.split(" ")[1].equals("position")) {

                        String i = String.valueOf(password.charAt(Integer.parseInt(inst.split(" ")[2])));
                        String j = String.valueOf(password.charAt(Integer.parseInt(inst.split(" ")[5])));

                        password = new StringBuilder(password.toString().replaceAll(i, "#").replaceAll(j, i).replaceAll("#", j));

                    } else if (inst.split(" ")[1].equals("letter")) {

                        String i = inst.split(" ")[2];
                        String j = inst.split(" ")[5];

                        password = new StringBuilder(password.toString().replaceAll(i, "#").replaceAll(j, i).replaceAll("#", j));
                    }
                    break;

                case "rotate":
                    if (inst.split(" ")[1].equals("based")) {

                        String letter = inst.split(" ")[6];
                        // on rotate de 1 a gauche
                        int i = 0;
                        while(i != (password.indexOf(letter) >= 4 ? password.indexOf(letter) + 2 : password.indexOf(letter) + 1)) {
                            password.append(password.charAt(0));
                            password.deleteCharAt(0);
                            i++;
                        }

                    } else {
                        int times = Integer.parseInt(inst.split(" ")[2]) % password.length();

                        // On inverse droite / gauche
                        if (!inst.split(" ")[1].equals("right")) {
                            String tmp = password.substring(password.length() - times, password.length());
                            password.delete(password.length() - times, password.length());
                            password.insert(0, tmp);
                        } else {
                            String tmp = password.substring(0, times);
                            password.delete(0, times);
                            password.insert(password.length(), tmp);
                        }
                    }
                    break;

                case "reverse": {

                    int i = Integer.parseInt(inst.split(" ")[2]);
                    int j = Integer.parseInt(inst.split(" ")[4]);

                    password = new StringBuilder(password.substring(0, i) + new StringBuilder(password.substring(i, j + 1)).reverse() + password.substring(j + 1));
                    break;
                }

                case "move": {

                    int i = Integer.parseInt(inst.split(" ")[2]);
                    int j = Integer.parseInt(inst.split(" ")[5]);

                    // On inverse la suppression et l'insertion
                    char tmp = password.charAt(j);
                    password.deleteCharAt(j);
                    password.insert(i, tmp);
                    break;
                }
            }
        }

        System.out.println("Original password is : " + password.toString());

    }
}
