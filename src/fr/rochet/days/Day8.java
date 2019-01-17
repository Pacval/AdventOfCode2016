package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.Arrays;

public class Day8 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(8);

        int[][] screen = new int[6][50];
        for (int[] aScreen1 : screen) {
            Arrays.fill(aScreen1, 0);
        }

        for (String command : entries) {
            if (command.split(" ")[0].equals("rect")) {

                int x = Integer.parseInt(command.split(" ")[1].split("x")[0]);
                int y = Integer.parseInt(command.split(" ")[1].split("x")[1]);

                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        screen[i][j] = 1;
                    }
                }

            } else if (command.split(" ")[0].equals("rotate")) {
                int rowCol = Integer.parseInt(command.split(" ")[2].split("=")[1]);
                int dist = Integer.parseInt(command.split(" ")[4]);

                if (command.split(" ")[1].equals("row")) {
                    for (int i = 0; i < dist; i++) {
                        int temp = screen[rowCol][screen[0].length - 1];
                        System.arraycopy(screen[rowCol], 0, screen[rowCol], 1, screen[0].length - 1);
                        screen[rowCol][0] = temp;
                    }

                } else if (command.split(" ")[1].equals("column")) {
                    for (int i = 0; i < dist; i++) {
                        int temp = screen[screen.length -1][rowCol];
                        for (int j = screen.length - 1; j > 0; j--) {
                            screen[j][rowCol] = screen[j - 1][rowCol];
                        }
                        screen[0][rowCol] = temp;
                    }
                }
            }
        }

        System.out.println("Final screen :");
        for (int[] aScreen : screen) {
            for (int pixel : aScreen) {
                System.out.print(pixel == 1 ? '#' : ' ');
            }
            System.out.println();
        }

        long pixelsOn = Arrays.stream(screen).flatMapToInt(Arrays::stream).filter(e -> e == 1).count();
        System.out.println("\nNumber of pixels on : " + pixelsOn);
    }

    @Override
    public void part2() throws Exception {
        this.part1();
    }
}
