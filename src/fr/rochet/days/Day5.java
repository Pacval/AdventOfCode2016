package fr.rochet.days;

import fr.rochet.DayInterface;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Day5 implements DayInterface {

    @Override
    public void part1() throws NoSuchAlgorithmException {
        String input = "ffykfhsq";

        StringBuilder passwordBuilder = new StringBuilder();
        int index = 0;

        while (passwordBuilder.length() < 8) {

            String stringToHash = input + index;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(stringToHash.getBytes());
            byte[] digest = messageDigest.digest();
            String hashedOutput = DatatypeConverter.printHexBinary(digest);

            if (hashedOutput.substring(0, 5).equals("00000")) {
                passwordBuilder.append(hashedOutput.charAt(5));
            }

            index++;
        }

        System.out.println("The first password is : " + passwordBuilder.toString());

    }

    @Override
    public void part2() throws NoSuchAlgorithmException {
        String input = "ffykfhsq";

        System.out.println("\n\nSearching for the second password :");

        StringBuilder passwordBuilder = new StringBuilder("________");
        int index = 0;

        while (passwordBuilder.indexOf("_") >= 0) {

            String stringToHash = input + index;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(stringToHash.getBytes());
            byte[] digest = messageDigest.digest();
            String hashedOutput = DatatypeConverter.printHexBinary(digest);

            if (hashedOutput.substring(0, 5).equals("00000")) {
                try {
                    int pos = Integer.parseInt(String.valueOf(hashedOutput.charAt(5)));
                    if (pos >= 0 && pos < 8 && passwordBuilder.charAt(pos) == '_') {
                        passwordBuilder.setCharAt(pos, hashedOutput.charAt(6));
                        System.out.println(passwordBuilder.toString());
                    }
                } catch (NumberFormatException ignored) {
                }
            }
            index++;
        }

        System.out.println("The second password is : " + passwordBuilder.toString());
    }
}