package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

public class Day9 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(9);
        StringBuilder file = new StringBuilder(entries[0]);

        int sizeDoc = 0;
        while (file.length() > 0) {
            if (file.charAt(0) == '(') {
                int posX = file.indexOf("x");
                int posParenthesis = file.indexOf(")");

                int jump = Integer.parseInt(file.substring(1, posX));
                int time = Integer.parseInt(file.substring(posX + 1, posParenthesis));

                sizeDoc += jump * time;
                file.delete(0, posParenthesis + jump + 1);

            } else {
                sizeDoc++;
                file.deleteCharAt(0);
            }
        }

        System.out.println("The decompressed file has a size of " + sizeDoc);
    }

    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(9);

        long uncompressedLength = countLength(entries[0]);

        System.out.println("The fully decompressed file has a size of " + uncompressedLength);
    }

    private long countLength(String compressedString) {
        StringBuilder compressedBuilder = new StringBuilder(compressedString);

        long uncompressedSize = 0;
        while (compressedBuilder.length() > 0) {
            if (compressedBuilder.charAt(0) == '(') {
                int posX = compressedBuilder.indexOf("x");
                int posParenthesis = compressedBuilder.indexOf(")");

                int jump = Integer.parseInt(compressedBuilder.substring(1, posX));
                long time = Integer.parseInt(compressedBuilder.substring(posX + 1, posParenthesis));

                uncompressedSize += time * countLength(compressedBuilder.substring(posParenthesis + 1, posParenthesis + 1 + jump));

                compressedBuilder.delete(0, posParenthesis + jump + 1);

            } else {
                uncompressedSize++;
                compressedBuilder.deleteCharAt(0);
            }
        }
        return uncompressedSize;
    }
}
