package fr.rochet.days;

import fr.rochet.DayInterface;
import org.apache.commons.lang3.StringUtils;

public class Day16 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String entry = "11101000110010100";
        int length = 272;

        while (entry.length() < length) {
            String reverse = StringUtils.reverse(entry);
            String inverse = reverse.replace('0', '2').replace('1', '0').replace('2', '1');
            entry = entry + 0 + inverse;
        }

        String checksum = entry.substring(0, length);

        do {
            StringBuilder newChecksum = new StringBuilder();

            for (int i = 0; i < checksum.length(); i+=2) {
                if (checksum.charAt(i) == checksum.charAt(i + 1)) {
                    newChecksum.append(1);
                } else {
                    newChecksum.append(0);
                }
            }

            checksum = newChecksum.toString();

        } while (checksum.length() % 2 == 0);

        System.out.println("Checksum is : " + checksum);
    }

    @Override
    public void part2() throws Exception {
        String entry = "11101000110010100";
        int length = 35651584;

        while (entry.length() < length) {
            String reverse = StringUtils.reverse(entry);
            String inverse = reverse.replace('0', '2').replace('1', '0').replace('2', '1');
            entry = entry + 0 + inverse;
        }

        String checksum = entry.substring(0, length);

        do {
            StringBuilder newChecksum = new StringBuilder();

            for (int i = 0; i < checksum.length(); i+=2) {
                if (checksum.charAt(i) == checksum.charAt(i + 1)) {
                    newChecksum.append(1);
                } else {
                    newChecksum.append(0);
                }
            }

            checksum = newChecksum.toString();

        } while (checksum.length() % 2 == 0);

        System.out.println("Checksum is : " + checksum);
    }
}
